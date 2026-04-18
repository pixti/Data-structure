/**
 * 항을 정의하는 클래스
 */
public class Term {
    private double coef; // 항의 계수 (Coef)
    private int exp;     // 항의 지수 (Exp)

    public Term(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public double getCoef() { return coef; }
    public int getExp() { return exp; }

    // 두 항의 지수를 비교하여 현재 항이 더 크면 양수, 작으면 음수 반환
    public int compareWithExp(Term other) {
        return this.exp - other.exp;
    }
}