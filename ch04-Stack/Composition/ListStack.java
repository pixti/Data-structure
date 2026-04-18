/**
 * SinglyLinkedList를 포함(hasA)하여 구현한 스택
 */
public class ListStack<E> implements Stack<E> {
    // 연결 리스트 객체를 멤버 변수로 가짐 (Composition)
    private SinglyLinkedList<E> list;

    public ListStack() {
        list = new SinglyLinkedList<>(); 
    }

    // 항목 추가 (Push): 리스트의 맨 앞에 추가
    public void push(E newItem) {
        list.addFirst(newItem);
    }

    // 최상단 항목 제거 및 반환 (Pop): 리스트의 맨 앞 항목 제거
    public E pop() {
        return list.removeFirst();
    }

    // 최상단 항목 확인 (Top): 리스트의 맨 앞 항목 참조
    public E top() {
        return list.first();
    }

    // 비어있는지 확인: 리스트에게 물어봄 (Delegation)
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 전체 제거: 리스트의 clear 호출
    public void popAll() {
        list.clear();
    }
}
