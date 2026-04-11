import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr1, arr2, result;

        arr1 = readArrayList(sc, 5);
        arr2 = readArrayList(sc, 5);
        result = mergeArrayList(arr1, arr2, 10);
        printResult(result, 10);
        sc.close();
    }

    public static ArrayList<Integer> readArrayList(Scanner sc, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        boolean isValid = false;

        while (!isValid) {
            list.clear();
            System.out.print("Input " + size + " integers in ascending order : ");

            boolean isSorted = true;
            int lastValue = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                if (!sc.hasNextInt()) {
                    sc.next();
                    isSorted = false;
                    continue;
                }

                int current = sc.nextInt();
                if (current < lastValue) {
                    isSorted = false;
                }
                list.add(current);
                lastValue = current;
            }

            sc.nextLine();

            if (isSorted) {
                isValid = true;
            } else {
                System.out.println("No ascending order. Try again.");
            }
        }
        return list;
    }

    public static ArrayList<Integer> mergeArrayList(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int totalSize) {
        ArrayList<Integer> merged = new ArrayList<>(totalSize);
        int i = 0;
        int j = 0;

        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                merged.add(arr1.get(i++));
            } else {
                merged.add(arr2.get(j++));
            }
        }

        while (i < arr1.size()) merged.add(arr1.get(i++));
        while (j < arr2.size()) merged.add(arr2.get(j++));

        return merged;
    }

    public static void printResult(ArrayList<Integer> result, int totalSize) {
        System.out.print("Merged array : ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}