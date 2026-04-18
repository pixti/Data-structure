/**
 * 단일 연결 리스트(Singly Linked List)를 구현한 클래스입니다.
 * 각 노드는 원소(element)와 다음 노드에 대한 참조(reference)를 가집니다.
 *
 * @param <E> 리스트에 저장될 원소의 타입
 */
public class SinglyLinkedList<E> {

    //---------------- 중첩된(nested) Node 클래스 ----------------
    /**
     * 리스트의 각 데이터를 담는 노드(Node) 유닛입니다.
     */
    private static class Node<E> {
        private E element;       // 이 노드에 저장된 원소(element)에 대한 참조
        private Node<E> next;    // 리스트의 다음 노드(next node)에 대한 참조

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n; }
    } //----------- 중첩된 Node 클래스의 끝 -----------

    // SinglyLinkedList의 인스턴스 변수들 (Instance variables)
    private Node<E> head = null;    // 리스트의 헤드(head) 노드 (비어있으면 null)
    private Node<E> tail = null;    // 리스트의 테일(tail) 노드 (비어있으면 null)
    private int size = 0;           // 리스트 내 노드의 개수(number of elements)

    /** 처음에 비어있는 리스트를 생성(construct)합니다. */
    public SinglyLinkedList() { }

    // 접근(access) 메소드들
    /**
     * 리스트의 원소 개수를 반환합니다.
     * @return 리스트 내 원소의 수 (size)
     */
    public int size() { return size; }

    /**
     * 리스트가 비어있는지 확인합니다.
     * @return 리스트가 비어있으면 true, 그렇지 않으면 false
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * 리스트의 첫 번째 원소를 반환합니다(삭제하지 않음).
     * @return 첫 번째 원소 (비어있으면 null)
     */
    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    /**
     * 리스트의 마지막 원소를 반환합니다(삭제하지 않음).
     * @return 마지막 원소 (비어있으면 null)
     */
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // 업데이트(update) 메소드들
    /**
     * 리스트의 맨 앞에 새로운 원소 e를 추가합니다.
     * @param e 삽입할 새로운 원소
     */
    public void addFirst(E e) {
        head = new Node<>(e, head); // 새 노드를 생성하고 기존 head와 연결(link)함
        if (size == 0)
            tail = head;            // 특별한 경우(special case): 새 노드가 tail도 됨
        size++;
    }

    /**
     * 리스트의 맨 뒤에 새로운 원소 e를 추가합니다.
     * @param e 삽입할 새로운 원소
     */
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null); // 이 노드는 결국 tail이 될 것임
        if (isEmpty())
            head = newest;          // 특별한 경우(special case): 이전에 비어있던 리스트
        else
            tail.setNext(newest);   // 기존 tail 뒤에 새 노드를 연결함
        tail = newest;              // 새 노드가 새로운 tail이 됨
        size++;
    }

    /**
     * 리스트의 첫 번째 원소를 삭제하고 반환합니다.
     * @return 삭제된 첫 번째 원소 (비어있으면 null)
     */
    public E removeFirst() {
        if (isEmpty()) return null; // 삭제할 것이 없음 (nothing to remove)
        E answer = head.getElement();
        head = head.getNext();      // 리스트에 노드가 하나뿐이었다면 head는 null이 됨
        size--;
        if (size == 0)
            tail = null;            // 특별한 경우(special case): 이제 리스트가 비어있음
        return answer;
    }

    /**
     * 리스트의 모든 원소를 삭제하여 초기화합니다.
     * 가비지 컬렉션(GC)을 돕기 위해 참조를 모두 해제합니다.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
