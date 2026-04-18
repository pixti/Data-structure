/**
 * 외부 라이브러리(Stack) 없이 구현한 스택 클래스
 */
public class MyStack {
    private String[] stack; // 데이터를 저장할 배열
    private int topIndex;   // 스택의 최상단 인덱스 (데이터가 없으면 -1)
    private int capacity;   // 현재 스택의 전체 용량

    // 초기 생성자
    public MyStack() {
        this.capacity = 10;
        this.stack = new String[capacity];
        this.topIndex = -1;
    }

    /**
     * 스택의 맨 위에 데이터를 넣습니다.
     */
    public void push(String item) {
        if (topIndex == capacity - 1) {
            resize();
        }
        stack[++topIndex] = item;
    }

    /**
     * 스택의 맨 위 데이터를 꺼내고 반환합니다.
     */
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[topIndex--];
    }

    /**
     * 스택의 맨 위 데이터를 삭제하지 않고 반환만 합니다. (알고리즘의 stack.top() 역할)
     */
    public String top() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[topIndex];
    }

    /**
     * 스택이 비어있는지 확인합니다.
     */
    public boolean isEmpty() {
        return topIndex == -1;
    }

    /**
     * 스택의 크기를 확인합니다.
     */
    public int size() {
        return topIndex + 1;
    }

    /**
     * 스택 공간이 부족할 때 2배로 확장합니다.
     */
    private void resize() {
        capacity *= 2;
        String[] newStack = new String[capacity];
        for (int i = 0; i <= topIndex; i++) {
            newStack[i] = stack[i];
        }
        this.stack = newStack;
    }
}
