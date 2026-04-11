import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class CircularDoublyLinkedListApp {
    private final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
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
                MemberPrinter.printAllDescending(this.list);
            } else if (menu == 3) {
                insertMember(sc);
            } else if (menu == 4) {
                removeMember(sc);
            } else if (menu == 5) {
                printFromTargetMember(sc);
            } else if (menu == 6) {
                saveData(fileName);
                System.out.println("[END]");
                isRunning = false;
            }
        }
    }

    private void printMenu() {
        System.out.println("============================================");
        System.out.println("(1) Print all in ascending order");
        System.out.println("(2) Print all in descending order");
        System.out.println("(3) Insert new member");
        System.out.println("(4) Delete member");
        System.out.println("(5) Print all from targeted member");
        System.out.println("(6) Save to file and Exit");
        System.out.println("============================================");
        System.out.print("Select menu: ");
    }

    private void insertMember(Scanner sc) {
        System.out.print("Input member_name, phone_number: ");
        String inputLine = sc.nextLine();
        reader.parseLineAndAdd(list, inputLine);
    }

    private void removeMember(Scanner sc) {
        System.out.print("Input member_name: ");
        String targetName = sc.nextLine();
        list.removeMember(targetName);
    }

    private void printFromTargetMember(Scanner sc) {
        System.out.print("Input member_name: ");
        String targetName = sc.nextLine();
        MemberPrinter.printFromTarget(this.list, targetName);
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