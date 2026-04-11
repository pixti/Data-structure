import java.io.PrintWriter;

public class MemberPrinter {
    public static void printAll(SinglyLinkedList list) {
        if (list.getMemberCount() == 0) {
            System.out.println("No members in the list.");
            return;
        }

        MemberNode currentMemberNode = list.getHead();
        while (currentMemberNode != null) {
            System.out.println(formatMember(currentMemberNode.getData(), "/"));
            currentMemberNode = currentMemberNode.getNext();
        }
    }

    public static void saveToFile(SinglyLinkedList list, PrintWriter writer) {
        MemberNode currentMemberNode = list.getHead();
        while (currentMemberNode != null) {
            writer.println(formatMember(currentMemberNode.getData(), "+"));
            currentMemberNode = currentMemberNode.getNext();
        }
    }

    private static String formatMember(Member member, String separator) {
        String memberInfo = member.getName() + " " + member.getPhone();

        BookNode currentBookNode = member.getBookHead();
        while (currentBookNode != null) {
            memberInfo += " " + currentBookNode.getData().getDate() + " " + currentBookNode.getData().getTitle();

            if (currentBookNode.getNext() != null) {
                memberInfo += separator;
            }
            currentBookNode = currentBookNode.getNext();
        }
        return memberInfo;
    }
}