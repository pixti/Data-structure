public class BookNode {
    private final Book data;
    private final BookNode next;

    public BookNode(Book data, BookNode next) {
        this.data = data;
        this.next = next;
    }

    public Book getData() { return data; }
    public BookNode getNext() { return next; }
}