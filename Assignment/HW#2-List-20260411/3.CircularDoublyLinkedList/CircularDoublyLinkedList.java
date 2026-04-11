public class CircularDoublyLinkedList {
    private final MemberNode dummy;

    public CircularDoublyLinkedList() {
        this.dummy = new MemberNode(null, null, null);
        this.dummy.setNext(dummy);
        this.dummy.setPrev(dummy);
    }

    public MemberNode getDummy() { return dummy; }

    public void addMemberSorted(String name, String phone) {
        Member createdMember = new Member(name, phone);
        MemberNode currentMemberNode = dummy.getNext();

        while (currentMemberNode != dummy &&
                currentMemberNode.getData().getName().compareTo(name) < 0) {
            currentMemberNode = currentMemberNode.getNext();
        }

        MemberNode newNode = new MemberNode(createdMember, currentMemberNode.getPrev(), currentMemberNode);
        currentMemberNode.getPrev().setNext(newNode);
        currentMemberNode.setPrev(newNode);
    }

    public void removeMember(String targetName) {
        MemberNode targetNode = findMember(targetName);
        if (targetNode != null) {
            targetNode.getPrev().setNext(targetNode.getNext());
            targetNode.getNext().setPrev(targetNode.getPrev());
        }
    }

    public MemberNode findMember(String targetName) {
        MemberNode currentMemberNode = dummy.getNext();
        while (currentMemberNode != dummy) {
            if (currentMemberNode.getData().getName().equals(targetName)) return currentMemberNode;
            currentMemberNode = currentMemberNode.getNext();
        }
        return null;
    }
}