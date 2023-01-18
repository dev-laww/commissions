import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of columns: ");
        int n = sc.nextInt();
        System.out.print("Enter number of rows: ");
        int m = sc.nextInt();

        System.out.println();

        int[] [] arr = new int[n] [m];
        int i, j, z = 1;

        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.print("Enter element" + z + ": ");
                arr[i][j] = sc.nextInt();
                z++;
            }
        }

        System.out.println("\nMatrix input:");
        // Printing the array
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nTransposed matrix:");
        // Printing the transposed array
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(arr[j][i] + " ");
            }
            System.out.println();
        }

    }
}