public class MemberNode {
    private final Member data;
    private MemberNode next;
    private MemberNode prev;

    public MemberNode(Member data, MemberNode prev, MemberNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public Member getData() { return data; }
    public MemberNode getNext() { return next; }
    public void setNext(MemberNode next) { this.next = next; }
    public MemberNode getPrev() { return prev; }
    public void setPrev(MemberNode prev) { this.prev = prev; }
}