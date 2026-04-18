/**
 * 희소 행렬의 저장 및 연산(Fast Transpose, Multiplication) 정의
 */

// 1. 개별 항을 저장하는 클래스 (패키지 내에서 사용)
class MatrixTerm {
    int row;
    int col;
    int value;

    public MatrixTerm() {}
    public MatrixTerm(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
}

// 2. 희소 행렬 연산 클래스
public class SparseMatrix {
    private final int rows;       // 전체 행 크기
    private final int cols;       // 전체 열 크기
    private int termCnt;          // 0이 아닌 항의 개수
    private final MatrixTerm[] array;

    public SparseMatrix(int rows, int cols, int capacity) {
        this.rows = rows;
        this.cols = cols;
        this.termCnt = 0;
        // Sentinel(가짜 원소) 공간 확보를 위해 capacity + 1
        this.array = new MatrixTerm[capacity + 1];
    }

    // 데이터 삽입을 위한 메서드
    public void addTerm(int r, int c, int v) {
        array[termCnt++] = new MatrixTerm(r, c, v);
    }

    /**
     * 빠른 전치 (Fast Transpose)
     * 시간 복잡도: O(cols + termCnt)
     */
    public SparseMatrix fastTranspose() {
        SparseMatrix b = new SparseMatrix(cols, rows, termCnt);
        b.termCnt = this.termCnt;

        if (termCnt > 0) { // nonzero 행렬에 대해서
            int[] rowSize = new int[cols];
            int[] rowStart = new int[cols];

            // rowSize에 원본 행렬의 각 열의 원소 또는 전치행렬의 각 행의 원소 개수 계산하여 저장
            for (int i = 0; i < termCnt; i++) {
                rowSize[array[i].col]++;
            }

            // rowStart에 전치행렬의 각 열의 시작 위치 계산하여 저장
            rowStart[0] = 0;
            for (int i = 1; i < cols; i++) {
                rowStart[i] = rowStart[i - 1] + rowSize[i - 1];
            }

            // 원본 행렬에서 결과 전치 행렬 b에 각 원소를 저장
            for (int i = 0; i < termCnt; i++) {
                int j = rowStart[array[i].col];
                b.array[j] = new MatrixTerm(array[i].col, array[i].row, array[i].value);
                rowStart[array[i].col]++;
            }
        }
        return b;
    }

    /**
     * 희소 행렬 곱셈 (Multiplication)
     * 시간 복잡도: O(b.cols * a.termCnt + a.rows * b.termCnt)
     */
    public SparseMatrix multiply(SparseMatrix b) {
        if (this.cols != b.rows) throw new IllegalArgumentException("행렬 크기 부적합");

        // 1. b를 전치하여 행 단위로 접근 가능하게 만듦
        SparseMatrix bXpose = b.fastTranspose();
        SparseMatrix d = new SparseMatrix(this.rows, b.cols, this.rows * b.cols);

        int currRowIndex = 0;
        int currRowBegin = 0;
        int currRowA = array[0].row;

        // 프로그램 작성을 용이하게 하기 위해, 가짜 원소를 하나 만들고 “가짜 원소” 하나 추가
        // array에는 여유 공간이 있을 것으로 가정
        // array의 제일 마지막 요소 row에 rows값, col에 cols값이 들어갈 수 없으니 가짜원소로 사용
        this.array[this.termCnt] = new MatrixTerm(this.rows, 0, 0);
        bXpose.array[b.termCnt] = new MatrixTerm(b.cols, -1, 0); // cols에는 값이 없을 것이므로 가짜 원소로 저장

        int sum = 0; // 한 원소의 결과를 저장하기 위한 변수

        while (currRowIndex < this.termCnt) {
            // 곱셈 결과를 저장하는 d에 currentRowA부터 값을 채워넣기
            int currColB = bXpose.array[0].row;
            int currColIndex = 0;

            while (currColIndex <= b.termCnt) {
                // 내 행렬의 currRowA의 행과 b의 currColB의 값을 곱해 나가기

                // Case 1: currRowA의 원소가 없거나 다 처리한 경우
                if (array[currRowIndex].row != currRowA) {
                    d.storeSum(sum, currRowA, currColB); // 지금까지 구한 sum을 새 행렬에 저장
                    sum = 0; // reset sum
                    currRowIndex = currRowBegin; // 현재 행의 처음으로 되돌아감

                    // 다음 열로 진행
                    while (bXpose.array[currColIndex].row == currColB)
                        currColIndex++;
                    currColB = bXpose.array[currColIndex].row;
                }
                // Case 2: b행렬에 currColB를 모두 다 처리한 경우
                else if (bXpose.array[currColIndex].row != currColB) {
                    d.storeSum(sum, currRowA, currColB);
                    sum = 0; // reset sum
                    currRowIndex = currRowBegin; // 다음 열부터 진행하기 위해 현재 행의 처음으로 되돌림
                    currColB = bXpose.array[currColIndex].row;
                }
                // Case 3: 인덱스가 매칭되지 않을 때 (a의 열 < b의 행)
                else if (array[currRowIndex].col < bXpose.array[currColIndex].col) {
                    currRowIndex++; // b배열의 다음 행에 대한 계산 계속
                }
                // Case 4: 내 행렬의 열과 전치행렬의 열(b행렬에서는 행)의 값이 같으면 곱해야 할 값
                else if (array[currRowIndex].col == bXpose.array[currColIndex].col) {
                    sum += array[currRowIndex].value * bXpose.array[currColIndex].value;
                    currRowIndex++;
                    currColIndex++;
                }
                // Case 5: next term in currColB (a의 열 > b의 행)
                else {
                    currColIndex++;
                }
            } // end of while (currColIndex <= b.termCnt)

            // advance to next row: 현재 행(currRowA) 처리가 완전히 끝났으므로 다음 행으로 이동
            while (array[currRowIndex].row == currRowA)
                currRowIndex++;
            currRowBegin = currRowIndex;
            currRowA = array[currRowIndex].row;
        } // end of while (currRowIndex < termCnt)

        return d;
    }

    // 합계가 0이 아닐 때만 결과 행렬에 저장
    public void storeSum(int sum, int r, int c) {
        if (sum != 0) {
            array[termCnt++] = new MatrixTerm(r, c, sum);
        }
    }

    public void print(String name) {
        System.out.println("--- " + name + " ---");
        System.out.println("Row\tCol\tValue");
        for (int i = 0; i < termCnt; i++) {
            System.out.println(array[i].row + "\t" + array[i].col + "\t" + array[i].value);
        }
    }
}