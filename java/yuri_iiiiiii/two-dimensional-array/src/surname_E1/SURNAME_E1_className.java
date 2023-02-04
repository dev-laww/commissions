/**
 * @author tora
 */

package surname_E1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class SURNAME_E1_className {
    private final int q;
    private final int r;
    private final double[][] matrix1;
    private final double[][] matrix2;

    /**
     * Constructor
     *
     * @param q number of rows
     * @param r number of columns
     */
    public SURNAME_E1_className(int q, int r) {

        this.q = q <= 5 ? q * 2 : q;
        this.r = r <= 5 ? r * 2 : r;

        matrix1 = new double[this.q][this.r];
        matrix2 = new double[this.q][this.r];
    }

    /**
     * Fill matrix1 with random numbers
     */
    public void fillA1() {
        Random random = new Random();
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < r; j++) {
                matrix1[i][j] = random.nextInt(200) + 1;
            }
        }
    }


    /**
     * Fill matrix2 with random numbers
     */
    public void fillA2() {
        Random random = new Random();
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < r; j++) {
                matrix2[i][j] = random.nextInt(5000) + 10;
            }
        }
    }

    /**
     * Add or subtract matrix1 and matrix2
     *
     * @param flag 0 - subtract, 1 - add
     */
    public void addSubA1A2(int flag) {
        double[][] result = new double[q][r];
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < r; j++) {
                result[i][j] = flag != 0 ? matrix1[i][j] + matrix2[i][j] : matrix1[i][j] - matrix2[i][j];
            }
        }
        System.out.println("Resultant matrix: ");
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Multiply from input file
     */
    public void multB1B2() {
        int m = 5, n = 7, p = 5;
        double[][] b1 = new double[m][n];
        double[][] b2 = new double[n][p];
        double[][] result = new double[m][p];

        try (Scanner scanner = new Scanner(new File("SURNAME_E1.in"));
             FileWriter fileWriter = new FileWriter("SURNAME_E1.out")) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    b1[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < p; j++) {
                    b2[i][j] = scanner.nextDouble();
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < p; j++) {
                    for (int k = 0; k < n; k++) {
                        result[i][j] += b1[i][k] * b2[k][j];
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < p; j++) {
                    fileWriter.write(result[i][j] + " ");
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e);
        }
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Random random = new Random();
        int k = random.nextInt(8);
        String fullName = "Your full name";
        StringBuilder result = new StringBuilder("Current date: " + dateFormat.format(new Date()) + "\n");
        for (int i = 0; i < k; i++) {
            result.append(fullName).append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        SURNAME_E1_className[] objects = new SURNAME_E1_className[2];
        for (int i = 0; i < 2; i++) {
            objects[i] = new SURNAME_E1_className(5, 5);
            objects[i].fillA1();
            objects[i].fillA2();
            objects[i].addSubA1A2(3);
            objects[i].multB1B2();
            System.out.println(objects[i]);
        }
    }
}
