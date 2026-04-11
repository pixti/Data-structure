public class SinglyLinkedList {
    private MemberNode head = null;
    private int memberCount = 0;

    public int getMemberCount() { return memberCount; }
    public MemberNode getHead() { return head; }

    public void addMemberSorted(String name, String phone) {
        Member createdMember = new Member(name, phone);
        MemberNode newNode = new MemberNode(createdMember, null);

        if (head == null || head.getData().getName().compareTo(name) > 0) {
            newNode.setNext(head);
            head = newNode;
        }
        else {
            MemberNode currentMemberNode = head;

            while (currentMemberNode.getNext() != null &&
                    currentMemberNode.getNext().getData().getName().compareTo(name) < 0) {
                currentMemberNode = currentMemberNode.getNext();
            }

            newNode.setNext(currentMemberNode.getNext());
            currentMemberNode.setNext(newNode);
        }
        memberCount++;
    }

    public void addBook(String targetName, String date, String title) {
        MemberNode currentMemberNode = findMember(targetName);
        if (currentMemberNode != null) {
            Book newBook = new Book(date, title);
            currentMemberNode.getData().setBookHead(new BookNode(newBook, currentMemberNode.getData().getBookHead()));
        }
    }

    public void removeMember(String targetName) {
        if (head == null) return;
        if (head.getData().getName().equals(targetName)) {
            head = head.getNext();
            memberCount--;
            return;
        }
        MemberNode previous = head;
        while (previous.getNext() != null && !previous.getNext().getData().getName().equals(targetName)) {
            previous = previous.getNext();
        }
        if (previous.getNext() != null) {
            previous.setNext(previous.getNext().getNext());
            memberCount--;
        }
    }

    public MemberNode findMember(String targetName) {
        MemberNode currentMemberNode = head;
        while (currentMemberNode != null) {
            if (currentMemberNode.getData().getName().equals(targetName)) return currentMemberNode;
            currentMemberNode = currentMemberNode.getNext();
        }
        return null;
    }
}