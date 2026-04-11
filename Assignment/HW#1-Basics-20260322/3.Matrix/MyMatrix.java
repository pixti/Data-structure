import java.util.Random;

public class MyMatrix {
    private int[][] matrix;
    private int rows, cols;

    public MyMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = rand.nextInt(999) + 1;
            }
        }
    }

    private MyMatrix(int[][] data, int rows, int cols) {
        this.matrix = data;
        this.rows = rows;
        this.cols = cols;
    }

    public MyMatrix rightRotate() {
        int[][] res = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[j][rows - 1 - i] = this.matrix[i][j];
            }
        }
        return new MyMatrix(res, cols, rows);
    }

    public MyMatrix leftRotate() {
        int[][] res = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[cols - 1 - j][i] = this.matrix[i][j];
            }
        }
        return new MyMatrix(res, cols, rows);
    }

    public MyMatrix transpose() {
        int[][] res = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[j][i] = this.matrix[i][j];
            }
        }
        return new MyMatrix(res, cols, rows);
    }

    public void printHeader(String title) {
        int width = 2 + (this.cols * 4) + 2 + 2;
        String format = "%-" + width + "s";
        System.out.printf(format, title);
    }

    public void printRow(int line) {
        System.out.print("| ");
        for (int j = 0; j < cols; j++) {
            System.out.printf("%3d ", this.matrix[line][j]);
        }
        System.out.print("|   ");
    }
}