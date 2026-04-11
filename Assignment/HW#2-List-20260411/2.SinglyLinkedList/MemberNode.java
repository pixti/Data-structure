public class MemberNode {
    private final Member data;
    private MemberNode next;

    public MemberNode(Member data, MemberNode next) {
        this.data = data;
        this.next = next;
    }

    public Member getData() { return data; }
    public MemberNode getNext() { return next; }
    public void setNext(MemberNode next) { this.next = next; }
}