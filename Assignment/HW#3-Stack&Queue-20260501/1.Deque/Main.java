import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyDeque<Integer> deque = new MyDeque<>();

        if (!sc.hasNextInt()) {
            return;
        }

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String command = sc.next();
            Integer result;

            switch (command) {
                case "push_front":
                    int itemFront = sc.nextInt();
                    deque.push_front(itemFront);
                    break;

                case "push_back":
                    int itemBack = sc.nextInt();
                    deque.push_back(itemBack);
                    break;

                case "pop_front":
                    result = deque.pop_front();
                    if (result == null) {
                        System.out.println("-1");
                    } else {
                        System.out.println(result);
                    }
                    break;

                case "pop_back":
                    result = deque.pop_back();
                    if (result == null) {
                        System.out.println("-1");
                    } else {
                        System.out.println(result);
                    }
                    break;

                case "size":
                    System.out.println(deque.getSize());
                    break;

                case "empty":
                    System.out.println(deque.empty());
                    break;

                case "front":
                    result = deque.front();
                    if (result == null) {
                        System.out.println("-1");
                    } else {
                        System.out.println(result);
                    }
                    break;

                case "back":
                    result = deque.back();
                    if (result == null) {
                        System.out.println("-1");
                    } else {
                        System.out.println(result);
                    }
                    break;
            }
        }
        sc.close();
    }
}