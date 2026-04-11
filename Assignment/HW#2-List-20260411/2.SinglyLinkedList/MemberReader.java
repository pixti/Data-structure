import java.util.Scanner;

public class MemberReader {

    public void readAll(SinglyLinkedList list, Scanner sc) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            parseLineAndAdd(list, line);
        }
    }

    public void parseLineAndAdd(SinglyLinkedList list, String line) {
        int firstSpaceIndex = line.indexOf(" ");
        if (firstSpaceIndex == -1) {
            return;
        }

        String name = line.substring(0, firstSpaceIndex);
        int secondSpaceIndex = line.indexOf(" ", firstSpaceIndex + 1);

        String phone;
        String remainingBooks = "";

        if (secondSpaceIndex == -1) {
            phone = line.substring(firstSpaceIndex + 1);
        } else {
            phone = line.substring(firstSpaceIndex + 1, secondSpaceIndex);
            remainingBooks = line.substring(secondSpaceIndex + 1);
        }

        list.addMemberSorted(name, phone);

        if (!remainingBooks.isEmpty()) {
            addMultiBook(list, name, remainingBooks);
        }
    }

    public void addMultiBook(SinglyLinkedList list, String name, String booksData) {
        String remainingData = booksData;

        while (!remainingData.isEmpty()) {
            int plusIndex = remainingData.indexOf("+");
            String oneBookInfo;

            if (plusIndex == -1) {
                oneBookInfo = remainingData;
                remainingData = "";
            } else {
                oneBookInfo = remainingData.substring(0, plusIndex);
                remainingData = remainingData.substring(plusIndex + 1);
            }

            addSingleBook(list, name, oneBookInfo.trim());
        }
    }

    public void addSingleBook(SinglyLinkedList list, String name, String bookInfo) {
        int spaceIndex = bookInfo.indexOf(" ");
        if (spaceIndex != -1) {
            String date = bookInfo.substring(0, spaceIndex);
            String title = bookInfo.substring(spaceIndex + 1);
            list.addBook(name, date, title);
        }
    }
}