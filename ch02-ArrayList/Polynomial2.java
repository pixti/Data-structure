/**
 * 다항식 클래스: [표현 2] 계수가 0이 아닌 항만 (계수, 지수) 쌍으로 저장
 * 설명: Term의 배열인 termArray를 이용해 0이 아닌 항들만 빈틈없이 관리함
 */
public class Polynomial2 {
    private Term[] termArray; // 0이 아닌 항의 배열 (객체 배열)
    private int termCnt;      // 0이 아닌 항의 수 (실제 데이터 개수)

    // 생성자: 초기 배열 크기를 정해서 생성함
    public Polynomial2() {
        this.termArray = new Term[10]; // 처음에는 10칸짜리 배열을 확보함
        this.termCnt = 0;              // 현재 들어있는 항의 개수는 0개
    }

    /**
     * 새로운 항을 배열 끝에 추가함 (push)
     * 로직: 배열이 꽉 차면 크기를 2배로 늘리고 데이터를 복사함
     */
    public void push(double coef, int exp) {
        // 배열의 실제 길이와 현재 저장된 개수가 같으면 용량이 부족한 상태
        if (termCnt == termArray.length) {
            // 기존보다 2배 큰 새로운 배열 생성 (Dynamic Resizing)
            Term[] newArray = new Term[termArray.length * 2];
            // 기존 데이터를 새 배열로 안전하게 복사
            System.arraycopy(termArray, 0, newArray, 0, termCnt);
            // termArray 변수가 새롭게 확장된 배열을 가리키도록 업데이트
            termArray = newArray;
        }
        // 새로운 항 객체를 생성하여 배열의 마지막 위치에 넣고 개수(termCnt) 증가
        termArray[termCnt++] = new Term(coef, exp);
    }

    // 특정 위치(i)에 있는 항을 가져오는 메서드
    public Term getIthTerm(int i) {
        return termArray[i];
    }

    /**
     * for 문을 이용한 다항식 덧셈 로직 (Add)
     * 로직: 두 다항식의 항을 지수별로 비교하여 병합(Merge)함
     */
    public Polynomial2 add(Polynomial2 pn) {
        Polynomial2 resultPoly = new Polynomial2(); // 결과를 담을 새 객체 생성
        int thisIdx, pnIdx;

        // 지수 비교를 통해 두 다항식을 큰 차수부터 병합
        // 증감식을 비워두어 조건에 맞는 포인터만 선택적으로 이동시킴
        for (thisIdx = 0, pnIdx = 0; thisIdx < this.termCnt && pnIdx < pn.termCnt; ) {
            Term thisTerm = this.getIthTerm(thisIdx);
            Term pnTerm = pn.getIthTerm(pnIdx);
            int compare = thisTerm.compareWithExp(pnTerm);

            if (compare > 0) {
                // 내 다항식의 지수가 더 크면 내 항을 먼저 결과에 추가
                resultPoly.push(thisTerm.getCoef(), thisTerm.getExp());
                thisIdx++;
            } else if (compare < 0) {
                // 상대 다항식의 지수가 더 크면 상대 항을 먼저 결과에 추가
                resultPoly.push(pnTerm.getCoef(), pnTerm.getExp());
                pnIdx++;
            } else {
                // 두 지수가 같은 경우 계수를 합산
                double sumCoef = thisTerm.getCoef() + pnTerm.getCoef();
                // 계수의 합이 0이 아닐 때만 결과 다항식에 포함
                if (sumCoef != 0) {
                    resultPoly.push(sumCoef, thisTerm.getExp());
                }
                // 지수가 같았으므로 양쪽 인덱스 모두 이동
                thisIdx++;
                pnIdx++;
            }
        }

        // 1차 비교 후 내 다항식에 남아있는 항들을 순차적으로 추가
        for (; thisIdx < this.termCnt; thisIdx++) {
            Term t = this.getIthTerm(thisIdx);
            resultPoly.push(t.getCoef(), t.getExp());
        }
        // 1차 비교 후 상대 다항식에 남아있는 항들을 순차적으로 추가
        for (; pnIdx < pn.termCnt; pnIdx++) {
            Term t = pn.getIthTerm(pnIdx);
            resultPoly.push(t.getCoef(), t.getExp());
        }

        return resultPoly; // 완성된 덧셈 결과 반환
    }
}