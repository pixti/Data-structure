import java.util.Scanner;

public class MemberReader {

    public void readAll(CircularDoublyLinkedList list, Scanner sc) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            parseLineAndAdd(list, line);
        }
    }

    public void parseLineAndAdd(CircularDoublyLinkedList list, String line) {
        int firstSpaceIndex = line.indexOf(" ");
        if (firstSpaceIndex == -1) return;

        String name = line.substring(0, firstSpaceIndex);
        String phone = line.substring(firstSpaceIndex + 1);

        list.addMemberSorted(name, phone);
    }
}