/**
 * 연결 리스트를 위한 노드 클래스
 */
public class Node<E> {
    public E item;         // 데이터 항목
    public Node<E> next;   // 다음 노드에 대한 참조

    public Node(E newItem) { 
        item = newItem;
        next = null;
    }

    public Node(E newItem, Node<E> nextNode) { 
        item = newItem;
        next = nextNode;
    }
}
