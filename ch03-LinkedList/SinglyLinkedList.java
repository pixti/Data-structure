public class SinglyLinkedList<E> {
    //---------------- 중첩된(nested) Node 클래스 ----------------
    private static class Node<E> {
        private E element;       // 이 노드에 저장된 원소(element)에 대한 참조
        private Node<E> next;    // 리스트의 다음 노드에 대한 참조

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n; }
    } //----------- 중첩된 Node 클래스의 끝 -----------

    // SinglyLinkedList의 인스턴스 변수들
    private Node<E> head = null;    // 리스트의 헤드(head) 노드 (비어있으면 null)
    private Node<E> tail = null;    // 리스트의 테일(tail) 노드 (비어있으면 null)
    private int size = 0;           // 리스트 내 노드의 개수(size)

    public SinglyLinkedList() { }   // 처음에 비어있는 리스트를 생성(construct)함

    // 접근(access) 메소드들
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public E first() {              // 첫 번째 원소를 반환함 (삭제하지는 않음)
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {               // 마지막 원소를 반환함 (삭제하지는 않음)
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // 업데이트(update) 메소드들
    public void addFirst(E e) {     // 리스트의 맨 앞에 원소 e를 추가함
        head = new Node<>(e, head); // 새 노드를 생성하고 기존 head와 연결(link)함
        if (size == 0)
            tail = head;            // 특별한 경우(special case): 새 노드가 tail도 됨
        size++;
    }

    public void addLast(E e) {      // 리스트의 맨 뒤에 원소 e를 추가함
        Node<E> newest = new Node<>(e, null); // 이 노드는 결국 tail이 될 것임
        if (isEmpty())
            head = newest;          // 특별한 경우(special case): 이전에 비어있던 리스트
        else
            tail.setNext(newest);   // 기존 tail 뒤에 새 노드를 연결함
        tail = newest;              // 새 노드가 새로운 tail이 됨
        size++;
    }

    public E removeFirst() {        // 첫 번째 원소를 삭제하고 반환함
        if (isEmpty()) return null; // 삭제할 것이 없음
        E answer = head.getElement();
        head = head.getNext();      // 리스트에 노드가 하나뿐이었다면 head는 null이 됨
        size--;
        if (size == 0)
            tail = null;            // 특별한 경우(special case): 이제 리스트가 비어있음
        return answer;
    }
}