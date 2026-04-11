import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class SinglyLinkedListApp {
    private final SinglyLinkedList list = new SinglyLinkedList();
    private final MemberReader reader = new MemberReader();

    public void run(String fileName) {
        Scanner sc = new Scanner(System.in);
        loadData(fileName);
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            if (!sc.hasNextInt()) break;
            int menu = sc.nextInt();
            sc.nextLine();

            if (menu == 1) {
                MemberPrinter.printAll(this.list);
            } else if (menu == 2) {
                insertMember(sc);
            } else if (menu == 3) {
                addBook(sc);
            } else if (menu == 4) {
                removeMember(sc);
            } else if (menu == 5) {
                saveData(fileName);
                System.out.println("[END]");
                isRunning = false;
            }
        }
    }

    private void printMenu() {
        System.out.println("----------------------------");
        System.out.println("(1) Print all in ascending order");
        System.out.println("(2) Insert new member");
        System.out.println("(3) Add new book");
        System.out.println("(4) Remove member");
        System.out.println("(5) Save to file and Exit");
        System.out.println("----------------------------");
        System.out.print("Select menu: ");
    }

    private void insertMember(Scanner sc) {
        System.out.print("Input member_name, phone_number, date and book: ");
        String inputLine = sc.nextLine();
        reader.parseLineAndAdd(list, inputLine);
    }

    private void addBook(Scanner sc) {
        System.out.print("Input a member_name, date and book: ");
        String inputLine = sc.nextLine();
        int spacePosition = inputLine.indexOf(" ");
        if (spacePosition != -1) {
            String name = inputLine.substring(0, spacePosition);
            String bookInfo = inputLine.substring(spacePosition + 1);
            reader.addSingleBook(list, name, bookInfo);
        }
    }

    private void removeMember(Scanner sc) {
        System.out.print("Input member_name: ");
        String targetName = sc.nextLine();
        list.removeMember(targetName);
    }

    private void loadData(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return;
        try (Scanner fileScanner = new Scanner(file)) {
            reader.readAll(list, fileScanner);
        } catch (Exception e) {
            System.out.println("File load error!");
        }
    }

    private void saveData(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            MemberPrinter.saveToFile(this.list, writer);
        } catch (Exception e) {
            System.out.println("File save error!");
        }
    }
}