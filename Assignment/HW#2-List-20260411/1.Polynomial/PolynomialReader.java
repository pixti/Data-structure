import java.util.Scanner;

public class PolynomialReader {
    private final Scanner sc;

    public PolynomialReader(Scanner sc) {
        this.sc = sc;
    }

    public Polynomial readPoly() {
        Polynomial newPolynomial = new Polynomial();
        boolean isReading = true;

        while (isReading) {
            double coef = sc.nextDouble();
            int exp = sc.nextInt();

            newPolynomial.push(coef, exp);

            if (exp == 0) {
                isReading = false;
            }
        }
        return newPolynomial;
    }
}

