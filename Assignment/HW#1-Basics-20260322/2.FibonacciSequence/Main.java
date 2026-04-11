import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("n = ");

        if (sc.hasNextInt()) {
            int n = sc.nextInt();

            long loopStartTime = System.nanoTime();
            long loopResult = fiboLoop(n);
            long loopEndTime = System.nanoTime();
            long loopDuration = loopEndTime - loopStartTime;

            System.out.println("[반복] 결과: " + loopResult);
            System.out.println("[반복] 소요 시간: " + loopDuration + " ns");

            long recurStartTime = System.nanoTime();
            long recurResult = fiboRecur(n);
            long recurEndTime = System.nanoTime();
            long recurDuration = recurEndTime - recurStartTime;

            System.out.println("[재귀] 결과: " + recurResult);
            System.out.println("[재귀] 소요 시간: " + recurDuration + " ns");
        }
        sc.close();
    }

    public static long fiboLoop(int n) {
        if (n <= 1) {
            return n;
        }

        long num1 = 0;
        long num2 = 1;
        long sum = 0;

        for (int i = 2; i <= n; i++) {
            sum = num1 + num2;
            num1 = num2;
            num2 = sum;
        }
        return sum;
    }

    public static long fiboRecur(int n) {
        if (n <= 1) {
            return n;
        }
        return fiboRecur(n - 1) + fiboRecur(n - 2);
    }
}

