public class MyDeque<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void push_front(E item) {
        Node<E> newNode = new Node<>(item);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    public void push_back(E item) {
        Node<E> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public E pop_front() {
        if (isEmpty()) {
            return null;
        }

        E data = head.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    public E pop_back() {
        if (isEmpty()) {
            return null;
        }

        E data = tail.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    public int getSize() {
        return size;
    }

    public int empty() {
        if (isEmpty()) {
            return 1;
        }
        return 0;
    }

    public E front() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    public E back() {
        if (isEmpty()) {
            return null;
        }
        return tail.data;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}