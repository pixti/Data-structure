public class Main {
    public static void main(String[] args) {
        final int MAX = 4;

        MyMatrix srcMat = new MyMatrix(MAX, MAX);
        MyMatrix rightMat = srcMat.rightRotate();
        MyMatrix leftMat = srcMat.leftRotate();
        MyMatrix transpMat = srcMat.transpose();

        srcMat.printHeader("Initial");
        rightMat.printHeader("Rotate Right");
        leftMat.printHeader("Rotate Left");
        transpMat.printHeader("Transpose");
        System.out.println();

        for (int line = 0; line < MAX; line++) {
            srcMat.printRow(line);
            rightMat.printRow(line);
            leftMat.printRow(line);
            transpMat.printRow(line);

            System.out.println();
        }
    }
}