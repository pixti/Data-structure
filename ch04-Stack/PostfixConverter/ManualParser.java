/**
 * 정규표현식이나 StringTokenizer 없이 
 * 문자열을 토큰(연산자, 피연산자) 단위로 분리하는 클래스
 */
public class ManualParser {
    private String input;
    private int pos; // 현재 읽고 있는 문자열의 위치

    public ManualParser(String input) {
        this.input = input;
        this.pos = 0;
    }

    /**
     * 다음 토큰을 반환합니다.
     * 더 이상 읽을 토큰이 없으면 종료 기호 '#'을 반환합니다.
     */
    public String nextToken() {
        // 1. 공백 제거 (Space, Tab 등 건너뛰기)
        while (pos < input.length() && input.charAt(pos) <= ' ') {
            pos++;
        }

        // 2. 입력 끝 처리 (End of Stream)
        if (pos >= input.length()) {
            return "#";
        }

        char c = input.charAt(pos);

        // 3. 피연산자 처리 (알파벳이나 숫자일 경우 연속해서 읽음)
        if (isAlphaNumeric(c)) {
            int start = pos;
            while (pos < input.length() && isAlphaNumeric(input.charAt(pos))) {
                pos++;
            }
            return input.substring(start, pos);
        }

        // 4. 두 글자 연산자 처리 (==, !=, <=, >=, &&, ||)
        if (pos + 1 < input.length()) {
            String twoChar = input.substring(pos, pos + 2);
            if (twoChar.equals("==") || twoChar.equals("!=") || 
                twoChar.equals("<=") || twoChar.equals(">=") || 
                twoChar.equals("&&") || twoChar.equals("||")) {
                pos += 2;
                return twoChar;
            }
        }

        // 5. 단일 문자 연산자 및 괄호 처리 (+, -, *, /, (, ) 등)
        pos++;
        return String.valueOf(c);
    }

    /**
     * 문자가 피연산자(문자 또는 숫자)인지 확인합니다.
     */
    private boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z') || 
               (c >= 'A' && c <= 'Z') || 
               (c >= '0' && c <= '9');
    }
}
