public class Term {
    private final double coef;
    private final int exp;

    public Term(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public double getCoef() {
        return coef;
    }

    public int getExp() {
        return exp;
    }

    public int compareExp(Term other) {
        return Integer.compare(this.exp, other.getExp());
    }
}


