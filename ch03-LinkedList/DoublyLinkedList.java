/** 기본 이중 연결 리스트(Doubly Linked List) 구현. */
public class DoublyLinkedList<E> {

    //------------------ 중첩된 Node 클래스 (nested Node class) ------------------
    private static class Node<E> {
        private E element;       // 이 노드에 저장된 요소(element)에 대한 reference
        private Node<E> prev;    // 리스트의 이전(previous) 노드에 대한 reference
        private Node<E> next;    // 리스트의 다음(subsequent) 노드에 대한 reference

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() { return element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    } //----------- Node 클래스 끝 -----------

    // 인스턴스 변수들
    private Node<E> header;      // 헤더 센티넬 (header sentinel)
    private Node<E> trailer;     // 트레일러 센티넬 (trailer sentinel)
    private int size = 0;        // 리스트 내 요소의 개수

    /** 비어있는 새로운 리스트를 생성합니다. (Constructor) */
    public DoublyLinkedList() {
        /* * header와 trailer의 초기화:
         * - 이 두 노드들은 실제 데이터 항목을 저장하지 않는 Dummy 노드입니다.
         * - 빈 노드들을 의도적으로 앞뒤에 남겨두어 리스트가 완전히 비어있는 경우(null)를
         * 방지하고 경계 조건 처리를 단순화합니다.
         * - (참고: 구현에 따라 header와 trailer를 하나로 통합하여 원형 리스트처럼 관리도 가능합니다.)
         */
        header = new Node<>(null, null, null);      // header 생성
        trailer = new Node<>(null, header, null);   // trailer 생성, 이전 노드는 header
        header.setNext(trailer);                    // header 다음은 trailer
    }

    // Accessor 메소드들
    /** 리스트에 저장된 요소의 개수를 반환합니다. */
    public int size() { return size; }

    /** 리스트가 비어있는지 확인합니다. */
    public boolean isEmpty() { return size == 0; }

    /** 리스트의 첫 번째 요소를 반환합니다(제거하지 않음). */
    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement();   // 첫 번째 실제 요소는 header 다음에 있음
    }

    /** 리스트의 마지막 요소를 반환합니다(제거하지 않음). */
    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();  // 마지막 실제 요소는 trailer 이전에 있음
    }

    // public 업데이트 메소드들
    /* * 아래의 update 메소드들은 private 메소드인 addBetween()과 remove()를
     * 재사용(reuse)하여 효율적으로 구현되었습니다.
     */

    /** 리스트의 제일 앞에 요소 e를 추가합니다. */
    public void addFirst(E e) {
        addBetween(e, header, header.getNext()); // header와 그 다음 노드 사이에 추가
    }

    /** 리스트의 제일 뒤에 요소 e를 추가합니다. */
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer); // trailer 이전 노드와 trailer 사이에 추가
    }

    /** 리스트의 첫 번째 요소를 삭제하고 반환합니다. */
    public E removeFirst() {
        if (isEmpty()) return null;              // 삭제할 항목이 없음
        return remove(header.getNext());         // header 다음 노드를 삭제
    }

    /** 리스트의 마지막 요소를 삭제하고 반환합니다. */
    public E removeLast() {
        if (isEmpty()) return null;              // 삭제할 항목이 없음
        return remove(trailer.getPrev());        // trailer 이전 노드를 삭제
    }

    // private 업데이트 메소드들
    /** * 주어진 두 노드 사이에 새로운 요소 e를 추가합니다. 
     * (추천: 이 방식 대신 앞노드만 인자로 가지는 addNext(Node predecessor)로 설계하는 것도 추천됩니다.)
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        // 새로운 노드를 생성하고 연결함
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /** 주어진 노드를 리스트에서 삭제하고 그 요소를 반환합니다. */
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor); // 앞 노드의 다음을 뒷 노드로 연결
        successor.setPrev(predecessor); // 뒷 노드의 이전을 앞 노드로 연결
        size--;
        return node.getElement();
    }
} //----------- DoublyLinkedList 클래스 끝 -----------