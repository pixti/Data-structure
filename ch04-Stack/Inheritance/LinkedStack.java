/**
 * 상속(is-A)을 이용한 LinkedStack
 */
public class LinkedStack<E> extends SinglyLinkedList<E> implements Stack<E> {

    public LinkedStack() {
        super();
    }

    // 항목 추가 (Push)
    public void push(E newItem) {
        addFirst(newItem); // 부모의 메소드 직접 호출
    }

    // 최상단 항목 제거 및 반환 (Pop)
    public E pop() {
        return removeFirst(); // 부모의 메소드 직접 호출
    }

    // 최상단 항목 확인 (Top)
    public E top() {
        return first(); // 부모의 메소드 직접 호출
    }

    // 비어있는지 확인
    public boolean isEmpty() {
        return super.isEmpty();
    }

    // 전체 제거 (popAll)
    public void popAll() {
        clear(); // 부모의 메소드 직접 호출
    }
}
