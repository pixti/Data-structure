/**
 * 직접 연결 리스트 노드를 관리하는 스택 (LinkedStack)
 */
public class LinkedStack<E> implements Stack<E> {
    private Node<E> topNode; // 최상단 노드 참조

    // 생성자
    public LinkedStack() {
        topNode = null;
    }

    // 항목 추가 (Push)
    public void push(E newItem) {
        // 새 노드를 생성하여 기존 topNode 앞에 연결
        topNode = new Node<>(newItem, topNode);
    }

    // 최상단 항목 제거 및 반환 (Pop)
    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            Node<E> temp = topNode;
            topNode = topNode.next; // top을 다음 노드로 이동
            return temp.item;
        }
    }

    // 최상단 항목 확인 (Top)
    public E top() {
        if (isEmpty()) {
            return null;
        } else {
            return topNode.item;
        }
    }

    // 비어있는지 확인
    public boolean isEmpty() {
        return (topNode == null);
    }

    // 전체 제거
    public void popAll() {
        topNode = null; // 참조를 끊어 GC가 수거하게 함
    }
}
