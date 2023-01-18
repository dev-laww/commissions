import java.util.Scanner;

public class Main {
    public static int checkLength(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int m = matrix[0].length;
        int length = 1;
        int i = row;
        int j = col;

        while (true) {
            // Check if we reached the end of the matrix
            if (i == n - 1 && j == m - 1) {
                break;
            }

            // Check if the next element is in the next row or the same row
            if (j == m - 1) {
                i++;
                j = 0;
            } else {
                j++;
            }

            // Check if the previous element is valid
            if (i != 0 || j != 0) {
                // prevent out of bounds exception
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (matrix[i][j] == matrix[i - 1][j - 1]) {
                        break;
                    }
                }
            }

            length++;
        }

        return length;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read in the size of the matrix
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Read in the values of the matrix
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Find the first element of the longest consecutive increasing numbers
        int maxLength = 0;
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Check for consecutive increasing numbers starting at (i, j)
                int length = checkLength(matrix, i, j);

                // Update the maximum length if necessary
                if (length > maxLength) {
                    maxLength = length;
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        // Print the result
        System.out.println(maxRow + " " + maxCol);


    }
}