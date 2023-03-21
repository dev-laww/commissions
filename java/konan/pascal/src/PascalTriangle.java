import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PascalTriangle {
    public static void main(String[] args) {
        Scanner reader = null;

        // Open the input file
        try {
            File file = new File("src" + File.separator + "pascal.txt");
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        // Read the row number from the input file
        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();


            // Skip empty lines
            if (line.isEmpty()) {
                continue;
            }

            // Parse the input row number
            int row;
            try {
                row = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.printf("Error: invalid input in input file: %s\n", line);
                continue;
            }
            if (row < 0 || row > 25) {
                System.out.printf("Error: input number must be from 0 to 25 only: %d\n", row);
                continue;
            }

            // Calculate the sums
            long sumAbove = 0;
            for (int i = 0; i < row; i++) {
                sumAbove += sumOfRow(i);
            }
            long sumNext = sumOfRow(row + 1);

            // Output the results
            System.out.printf("The sum of numbers above row %d is %d. The sum of numbers in row %d is %d.\n",
                    row, sumAbove, row + 1, sumNext);
        }

        // Close the input file
        reader.close();
    }

    // Method to calculate the sum of numbers in a row of Pascal's triangle
    public static long sumOfRow(int row) {
        long sum = 0;
        int coef = 1;
        for (int i = 0; i < row + 1; i++) {
            sum += coef;
            coef = coef * (row - i) / (i + 1);
        }
        return sum;
    }
}