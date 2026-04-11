import java.io.PrintWriter;

public class MemberPrinter {
    public static void printAll(CircularDoublyLinkedList list) {
        MemberNode currentMemberNode = list.getDummy().getNext();
        while (currentMemberNode != list.getDummy()) {
            System.out.println(formatMember(currentMemberNode.getData()));
            currentMemberNode = currentMemberNode.getNext();
        }
    }

    public static void printAllDescending(CircularDoublyLinkedList list) {
        MemberNode currentMemberNode = list.getDummy().getPrev();
        while (currentMemberNode != list.getDummy()) {
            System.out.println(formatMember(currentMemberNode.getData()));
            currentMemberNode = currentMemberNode.getPrev();
        }
    }

    public static void printFromTarget(CircularDoublyLinkedList list, String targetName) {
        MemberNode startNode = list.findMember(targetName);
        if (startNode == null) return;

        System.out.println(formatMember(startNode.getData()));
        MemberNode currentMemberNode = startNode.getNext();

        while (currentMemberNode != startNode) {
            if (currentMemberNode != list.getDummy()) {
                System.out.println(formatMember(currentMemberNode.getData()));
            }
            currentMemberNode = currentMemberNode.getNext();
        }
    }

    public static void saveToFile(CircularDoublyLinkedList list, PrintWriter writer) {
        MemberNode currentMemberNode = list.getDummy().getNext();
        while (currentMemberNode != list.getDummy()) {
            writer.println(formatMember(currentMemberNode.getData()));
            currentMemberNode = currentMemberNode.getNext();
        }
    }

    private static String formatMember(Member member) {
        return member.getName() + " " + member.getPhone();
    }
}