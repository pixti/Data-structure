/**
 * 싱글 연결 리스트
 */
public class SinglyLinkedList2<E extends Comparable<E>> {

    //---------------- 중첩된(nested) Node 클래스 ----------------
    private static class Node<E> {
        private E item;
        private Node<E> next;

        // 생성자 1: 단일 항목만 있을 때
        public Node(E newItem) {
            this.item = newItem;
            this.next = null;
        }

        // 생성자 2: 항목과 다음 노드 레퍼런스를 동시에 지정할 때
        public Node(E newItem, Node<E> nextNode) {
            this.item = newItem;
            this.next = nextNode;
        }

        public Node<E> getNext() { return next; }
        public E getItem() { return item; }
        public void setItem(E newItem) { this.item = newItem; }
        public void setNext(Node<E> newNext) { this.next = newNext; }
    } //----------- Node 클래스 끝 -----------

    private Node<E> head;      // 더미 헤드(dummy head) 노드
    private Node<E> tail;      // 테일(tail) 노드
    private int numItems;      // 리스트 내 원소 개수

    // 리스트 초기화
    public SinglyLinkedList2() {
        numItems = 0;
        // 더미 헤드 노드 생성 (item과 차후 연결될 next 모두 null)
        head = new Node<>(null, null);
        tail = head;
    }

    // --- 시간 복잡도 관련 메소드 ---

    // 탐색 (Search): 특정 항목 x의 "이전 노드"를 찾는 핵심 메소드
    protected Node<E> findPrevNode(E x) {
        Node<E> prevNode = head;
        Node<E> currNode = head.getNext();

        // 순차 탐색 (Dereferencing 반복)
        while (currNode != null && currNode.getItem().compareTo(x) != 0) {
            prevNode = currNode;
            currNode = currNode.getNext();
        }

        // 찾지 못했다면 null 반환 (또는 구현에 따라 prevNode 반환)
        return (currNode != null) ? prevNode : null;
    }

    // --- 삽입 (Update) 메소드들 ---

    // 맨 앞에 삽입
    public void addFirst(E x) {
        // 더미 헤드 방식에서는 항상 head(더미) 뒤에 삽입하는 것으로 통일
        addAfter(head, x);
    }

    // 맨 뒤에 삽입 (tail이 없으면 O(N)이지만 있으면 O(1))
    public void addLast(E x) {
        Node<E> newest = new Node<>(x, null);
        tail.setNext(newest);
        tail = newest;
        numItems++;
    }

    // 특정 노드 뒤에 삽입
    public void addAfter(Node<E> prevNode, E x) {
        // (1) 새 노드 생성 및 뒤를 먼저 연결 -> (2) 앞 노드가 새 노드를 가리킴
        Node<E> newNode = new Node<>(x, prevNode.getNext());
        prevNode.setNext(newNode);

        // 만약 마지막 노드 뒤에 넣었다면 tail 갱신
        if (prevNode == tail) {
            tail = newNode;
        }
        numItems++;
    }

    // --- 삭제 (Update) 메소드들 ---

    // 맨 앞 원소 삭제
    public E removeFirst() {
        // 더미 헤드 방식에서는 항상 removeAfter(head) 호출로 일관성 유지
        return removeAfter(head);
    }

    // 특정 원소 삭제
    public E removeItem(E x) {
        Node<E> prevNode = findPrevNode(x);
        if (prevNode != null) {
            return removeAfter(prevNode);
        }
        return null;
    }

    // 특정 노드 다음 노드 삭제
    public E removeAfter(Node<E> prevNode) {
        if (prevNode.getNext() == null) return null; // 삭제할 대상이 없음

        Node<E> targetNode = prevNode.getNext();      // (1) 삭제 대상 특정
        prevNode.setNext(targetNode.getNext());      // (2) 건너뛰기 연결 (Bypass)

        if (targetNode == tail) {                    // 끝 노드를 지웠다면 tail 갱신
            tail = prevNode;
        }

        targetNode.setNext(null);                    // (3) 삭제 대상 고립 (Isolate)
        numItems--;
        return targetNode.getItem();
    }

    // --- [접근 및 상태] 메소드들 ---

    public int size() { return numItems; }

    public boolean isEmpty() { return numItems == 0; }

    public void clear() {
        numItems = 0;
        head = new Node<>(null, null);
        tail = head;
    }

    // 두 리스트의 결합 (Concatenation)
    public void concatenate(SinglyLinkedList2<E> targetList) {
        if (!targetList.isEmpty()) {
            // 내 tail 뒤에 상대 리스트의 "진짜 첫 노드(더미 다음)" 연결
            this.tail.setNext(targetList.head.getNext());
            this.tail = targetList.tail;
            this.numItems += targetList.size();

            // 상대방 리스트 초기화 (더미 구조 준수)
            targetList.clear();
        }
    }

    // 리스트 역순으로 바꾸기 (Reverse)
    public void reverse() {
        if (numItems <= 1) return;

        Node<E> prevNode = null;
        Node<E> currNode = head.getNext(); // 더미 헤드 다음 노드부터 시작
        Node<E> nextNode = null;

        // 뒤집히면 원래의 첫 노드가 tail이 됨
        tail = currNode;

        while (currNode != null) {
            nextNode = currNode.getNext();     // (1) 다음 칸 미리 확보
            currNode.setNext(prevNode);        // (2) 현재 화살표를 뒤(prev)로 돌림
            prevNode = currNode;               // (3) prev를 현재로 전진
            currNode = nextNode;               // (4) 현재를 다음으로 전진
        }

        // 마지막에 도달한 prevNode가 새로운 진짜 첫 노드가 됨
        head.setNext(prevNode);
    }

    // 테스트용 출력 메소드
    public void printAll() {
        for (Node<E> curr = head.getNext(); curr != null; curr = curr.getNext()) {
            System.out.print(curr.getItem() + " -> ");
        }
        System.out.println("null");
    }
}