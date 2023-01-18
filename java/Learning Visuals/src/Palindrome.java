import java.util.Arrays;

public class Palindrome {
    public static void main(String[] args) {
        char[][] palindrome = {
                //0    1
                {'A', 'B', 'A', 'B', 'A'},
                {'B', 'B', 'A', 'B', 'A'},
                {'A', 'B', 'A', 'B', 'B'},
                {'B', 'A', 'B', 'B', 'A'}
        };

        for (char[] row: palindrome) {
            char[] rowCopy = row.clone();

            for (int i = 0; i < row.length / 2; i++) {
                char temp = row[i];
                row[i] = row[row.length - i - 1];
                row[row.length - i - 1] = temp;
            }

            boolean isPalindrome = true;
            for (int i = 0; i < row.length; i++) {
                if (row[i] != rowCopy[i]) {
                    isPalindrome = false;
                    break;
                }
            }

            System.out.println(isPalindrome ? "Palindrome" : "Not");

        }

        System.out.println();

        char[][] column = new char[5][4];

        for (int i = 0; i < column.length; i++) {
            for (int j = 0; j < column[i].length; j++) {
                column[i][j] = palindrome[j][i];
            }
        }

        for (int i = 0; i < column.length / 2; i++) {
            char[] temp = column[i];
            column[i] = column[column.length - i - 1];
            column[column.length - i - 1] = temp;
        }

        for (char[] row: column) {
            char[] rowCopy = row.clone();


            for (int i = 0; i < row.length / 2; i++) {
                char temp = row[i];
                row[i] = row[row.length - i - 1];
                row[row.length - i - 1] = temp;
            }


            boolean isPalindrome = true;
            for (int i = 0; i < row.length; i++) {
                if (row[i] != rowCopy[i]) {
                    isPalindrome = false;
                    break;
                }
            }

            System.out.println(isPalindrome ? "Palindrome" : "Not");

        }
    }
}
