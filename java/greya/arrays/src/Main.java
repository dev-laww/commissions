import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of columns: ");
        int columns = scanner.nextInt();

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // determine the most common and highest element
        int mostCommonElement = matrix[0][0];
        int maxCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int count = 0;

                for (int k = 0; k < rows; k++) {
                    for (int l = 0; l < columns; l++) {
                        //  0 0               0 0
                        //    1                1
                        //   0 1              0 0
                        //    2        !=      1
                        //   0 2               0 0
                        //     3      !=         1
                        //    1  0              0 0
                        //      1    ==          1

                        if (matrix[k][l] == matrix[i][j]) {
                            count++;
                        }
                    }
                }

                if (count > maxCount || (count > 1 && matrix[i][j] > mostCommonElement)) {
                    maxCount = count;
                    mostCommonElement = matrix[i][j];
                }

            }
        }

        System.out.println("Most severe crime = " + (maxCount > 1 ? mostCommonElement : "No Severe crime"));
    }
}