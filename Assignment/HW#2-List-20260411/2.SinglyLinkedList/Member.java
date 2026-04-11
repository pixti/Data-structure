public class Member {
    private final String name;
    private final String phone;
    private BookNode bookHead;

    public Member(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.bookHead = null;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public BookNode getBookHead() { return bookHead; }
    public void setBookHead(BookNode bookHead) { this.bookHead = bookHead; }
}