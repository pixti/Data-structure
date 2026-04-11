import java.util.Scanner;

public class PolynomialApp {
    public void run() {
        Scanner sc = new Scanner(System.in);
        PolynomialReader reader = new PolynomialReader(sc);

        Polynomial polyA = reader.readPoly();
        Polynomial polyB = reader.readPoly();

        Polynomial result = Polynomial.multiply(polyA, polyB);

        System.out.print("Res: ");
        PolynomialPrinter.print(result);

        sc.close();
    }
}

