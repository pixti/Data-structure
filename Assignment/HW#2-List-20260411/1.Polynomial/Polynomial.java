public class Polynomial {
    private Term[] termArray;
    private int termCount;

    public Polynomial() {
        this.termArray = new Term[4];
        this.termCount = 0;
    }

    public int getTermCount() {
        return this.termCount;
    }

    private void resize() {
        Term[] newTerms = new Term[termArray.length * 2];
        for (int index = 0; index < termCount; index++) {
            newTerms[index] = termArray[index];
        }
        this.termArray = newTerms;
    }

    public void push(double coef, int exp) {
        if (coef == 0) return;
        if (termCount >= termArray.length) resize();
        this.termArray[termCount++] = new Term(coef, exp);
    }

    public Term getTerm(int index) {
        if (index < termCount) {
            return termArray[index];
        }
        else {
            return null;
        }
    }

    public Polynomial add(Polynomial other) {
        Polynomial resultPoly = new Polynomial();
        int thisIndex = 0, otherIndex = 0;

        while (thisIndex < this.termCount && otherIndex < other.getTermCount()) {
            Term thisTerm = this.getTerm(thisIndex);
            Term otherTerm = other.getTerm(otherIndex);
            int compare = thisTerm.compareExp(otherTerm);

            if (compare > 0) {
                resultPoly.push(thisTerm.getCoef(), thisTerm.getExp());
                thisIndex++;
            } else if (compare < 0) {
                resultPoly.push(otherTerm.getCoef(), otherTerm.getExp());
                otherIndex++;
            } else {
                double sumCoef = thisTerm.getCoef() + otherTerm.getCoef();
                if (sumCoef != 0) resultPoly.push(sumCoef, thisTerm.getExp());
                thisIndex++;
                otherIndex++;
            }
        }
        pushRemain(resultPoly, thisIndex, otherIndex, other);
        return resultPoly;
    }

    private void pushRemain(Polynomial resultPoly, int thisIndex, int otherIndex, Polynomial other) {
        while (thisIndex < this.termCount) {
            Term currentTerm = this.getTerm(thisIndex++);
            resultPoly.push(currentTerm.getCoef(), currentTerm.getExp());
        }
        while (otherIndex < other.getTermCount()) {
            Term currentTerm = other.getTerm(otherIndex++);
            resultPoly.push(currentTerm.getCoef(), currentTerm.getExp());
        }
    }

    public static Polynomial multiply(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial totalResult = new Polynomial();

        for (int firstIndex = 0; firstIndex < firstPoly.getTermCount(); firstIndex++) {
            Polynomial temp = new Polynomial();
            Term firstTerm = firstPoly.getTerm(firstIndex);

            for (int secondIndex = 0; secondIndex < secondPoly.getTermCount(); secondIndex++) {
                Term secondTerm = secondPoly.getTerm(secondIndex);

                temp.push(firstTerm.getCoef() * secondTerm.getCoef(),
                        firstTerm.getExp() + secondTerm.getExp());
            }
            totalResult = totalResult.add(temp);
        }
        return totalResult;
    }
}