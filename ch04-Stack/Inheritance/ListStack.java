/**
 * SinglyLinkedList를 상속(is-A)받아 구현한 ListStack
 */
public class ListStack<E> extends SinglyLinkedList<E> implements Stack<E> {

    // 생성자: 부모 클래스인 SinglyLinkedList의 생성자를 호출합니다.
    public ListStack() {
        super();
    }

    // 항목 추가 (Push)
    public void push(E newItem) {
        // 부모의 addFirst()를 사용하여 스택의 push를 구현합니다.
        addFirst(newItem);
    }

    // 최상단 항목 제거 및 반환 (Pop)
    public E pop() {
        // 부모의 removeFirst()를 사용하여 스택의 pop을 구현합니다.
        return removeFirst();
    }

    // 최상단 항목 확인 (Top)
    public E top() {
        // 부모의 first()를 사용하여 현재 맨 앞 항목을 확인합니다.
        return first();
    }

    // 비어있는지 확인
    public boolean isEmpty() {
        // 부모 클래스에 정의된 isEmpty()를 재사용합니다.
        return super.isEmpty();
    }

    // 전체 제거 (popAll)
    public void popAll() {
        // 부모의 clear()를 호출하여 리스트를 초기화합니다.
        clear();
    }
}
