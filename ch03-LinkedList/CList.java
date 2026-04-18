import java.util.NoSuchElementException;

public class CList<E> {
    //---------------- 중첩된 Node 클래스 ----------------
    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E newItem, Node<E> nextNode) {
            this.item = newItem;
            this.next = nextNode;
        }

        public E getItem() { return item; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n; }
    }
    //--------------------------------------------------

    private Node<E> last; // 리스트의 마지막 노드를 가리킴
    private int size;     // 리스트의 노드 수

    public CList() {
        last = null;
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    /**
     * 삽입 연산
     * 원형 리스트에서는 last.getNext()가 항상 '첫 번째 노드'를 가리킵니다.
     * 따라서 last 다음에 삽입한다는 것은 리스트의 '맨 앞'에 넣는 것과 같습니다.
     */
    public void insert(E newItem) {
        Node<E> newNode = new Node<>(newItem, null);

        if (last == null) { // 리스트가 empty일 때
            newNode.setNext(newNode); // 자기 자신을 가리켜 원형 형성
            last = newNode;
        } else {
            // newNode의 다음이 기존의 첫 번째 노드를 가리키게 함
            newNode.setNext(last.getNext());
            // last의 다음이 newNode를 가리키게 함
            last.setNext(newNode);
        }
        size++;
    }

    /**
     *  삭제 연산
     * last.getNext() 즉, 리스트의 '첫 번째 노드'를 제거합니다.
     */
    public Node<E> delete() {
        if (isEmpty()) throw new NoSuchElementException();

        Node<E> x = last.getNext(); // x가 리스트의 첫 노드를 가리킴

        if (x == last) { // 리스트에 1개의 노드만 있는 경우
            last = null;
        } else {
            // last의 다음 노드가 x의 다음 노드(두 번째 노드)를 가리키게 함
            last.setNext(x.getNext());
            x.setNext(null); // 삭제될 노드 고립
        }
        size--;
        return x;
    }

    // 리스트 전체를 한 바퀴 돌며 출력하는 메소드
    public void print() {
        if (isEmpty()) {
            System.out.println("리스트가 비어있습니다.");
            return;
        }

        Node<E> curr = last.getNext(); // 첫 번째 노드부터 시작
        for (int i = 0; i < size; i++) {
            System.out.print(curr.getItem() + " -> ");
            curr = curr.getNext();
        }
        System.out.println("(다시 " + curr.getItem() + "로 연결됨)");
    }
}