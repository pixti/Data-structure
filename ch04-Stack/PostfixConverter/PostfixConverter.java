/**
 * 중위 표기법(Infix)을 후위 표기법(Postfix)으로 변환하는 클래스
 */
public class PostfixConverter {

    /**
     * ISP (In-Stack Priority): 스택 내부에 있을 때의 우선순위
     * 숫자가 작을수록 우선순위가 높습니다.
     */
    private static int inStackPriority(String op) {
        switch (op) {
            case "!": return 1;
            case "*": case "/": case "%": return 2;
            case "+": case "-": return 3;
            case "<": case ">": case "<=": case ">=": return 4;
            case "==": case "!=": return 5;
            case "&&": return 6;
            case "||": return 7;
            case "(":  return 8; // 스택 안에서는 가장 낮은 순위 (다른 연산자에 의해 pop되지 않음)
            case "#":  return 9; // 스택 바닥 기호
            default:   return 10;
        }
    }

    /**
     * ICP (In-Coming Priority): 스택에 들어가기 전의 우선순위
     */
    private static int incomingPriority(String op) {
        switch (op) {
            case "(":  return 0; // 들어올 때는 가장 높은 순위 (무조건 push)
            case "!":  return 1;
            case "*": case "/": case "%": return 2;
            case "+": case "-": return 3;
            case "<": case ">": case "<=": case ">=": return 4;
            case "==": case "!=": return 5;
            case "&&": return 6;
            case "||": return 7;
            case "#":  return 8; // 입력의 끝
            default:   return 10;
        }
    }

    /**
     * 연산자가 왼쪽 결합성(Left Association)을 가지는지 확인
     * (대부분의 이항 연산자는 true, 단항 연산자나 대입은 false)
     */
    private static boolean isLeftAssociation(String op) {
        // 단항 연산자 '!'를 제외하면 본 예제의 연산자들은 왼쪽 결합임
        return !op.equals("!");
    }

    /**
     * 토큰이 피연산자(숫자나 변수)인지 확인
     */
    private static boolean isOperand(String token) {
        if (token == null || token.equals("#")) return false;
        char c = token.charAt(0);
        // 첫 글자가 알파벳이나 숫자이면 피연산자로 간주
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    /**
     * Infix 수식을 Postfix 리스트로 변환하는 핵심 함수
     */
    public MyArrayList getPostfix(String expression) {
        MyStack stack = new MyStack();
        MyArrayList resultList = new MyArrayList();
        ManualParser parser = new ManualParser(expression);

        // 1. 초기 설정: 스택 바닥에 '#'을 푸시하여 빈 스택 체크를 대신함
        stack.push("#");

        // 2. 수식의 끝('#')을 만날 때까지 토큰을 하나씩 읽음
        for (String curToken = parser.nextToken(); !curToken.equals("#"); curToken = parser.nextToken()) {
            
            // A. 피연산자라면 즉시 결과 리스트에 추가
            if (isOperand(curToken)) {
                resultList.addLast(curToken);
            }
            // B. 닫는 괄호 ')'라면 여는 괄호 '('를 만날 때까지 pop하여 결과에 추가
            else if (curToken.equals(")")) {
                while (!stack.top().equals("(")) {
                    resultList.addLast(stack.pop());
                }
                stack.pop(); // '(' 자체를 스택에서 제거 (결과에는 넣지 않음)
            }
            // C. 연산자(또는 여는 괄호)라면 우선순위에 따라 처리
            else {
                /*
                 * [핵심 로직]
                 * 스택 top에 있는 연산자의 우선순위가 현재 토큰보다 높거나(값이 작음),
                 * 우선순위가 같으면서 현재 토큰이 왼쪽 결합성을 가진다면 계속 pop하여 결과에 추가합니다.
                 */
                while (!stack.top().equals("#") && 
                      (inStackPriority(stack.top()) < incomingPriority(curToken) || 
                      (inStackPriority(stack.top()) == incomingPriority(curToken) && isLeftAssociation(curToken)))) {
                    
                    resultList.addLast(stack.pop());
                }
                // 현재 연산자를 스택에 쌓음
                stack.push(curToken);
            }
        }

        // 3. 입력을 모두 읽었으므로 스택에 남은 모든 연산자를 결과에 추가
        while (!stack.top().equals("#")) {
            resultList.addLast(stack.pop());
        }

        return resultList;
    }

    /**
     * 실행 테스트를 위한 메인 함수
     */
    public static void main(String[] args) {
        PostfixConverter converter = new PostfixConverter();
        
        // 테스트 케이스 1: 괄호와 우선순위 혼합
        String infix1 = "a + b * c / ( d - e )";
        // 테스트 케이스 2: 논리 및 비교 연산자 포함
        String infix2 = "a > b && c == d || !e";

        printResult(infix1, converter.getPostfix(infix1));
        printResult(infix2, converter.getPostfix(infix2));
    }

    private static void printResult(String infix, MyArrayList postfix) {
        System.out.println("Infix  : " + infix);
        System.out.print("Postfix: ");
        for (int i = 0; i < postfix.size(); i++) {
            System.out.print(postfix.get(i) + " ");
        }
        System.out.println("\n----------------------------");
    }
}
