import java.util.Scanner;

public class Determinant {
    // main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter values: ");
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // determinant
        int determinant = 0;
        for (int i = 0; i < 3; i++) {
            determinant += matrix[0][i] * (matrix[1][(i + 1) % 3] * matrix[2][(i + 2) % 3] - matrix[1][(i + 2) % 3] * matrix[2][(i + 1) % 3]);
        }

        System.out.println("Determinant = " + determinant);
    }
}
