public class PolynomialPrinter {
    public static void print(Polynomial poly) {

        int termCount = poly.getTermCount();

        if (termCount == 0) {
            System.out.println("0");
            return;
        }

        for (int index = 0; index < termCount; index++) {
            Term currentTerm = poly.getTerm(index);

            System.out.print((int)currentTerm.getCoef() + "x^" + currentTerm.getExp());

            if (index < termCount - 1) {
                System.out.print(" + ");
            }
        }
        System.out.println();
    }
}

