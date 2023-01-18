import java.util.Scanner;

public class Array {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of 1st array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter size of second array: ");
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        System.out.print("Enter size of third array: ");
        int k = sc.nextInt();
        int[] arr3 = new int[k];

        System.out.print("\nEnter elements of 1st array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter elements of 2nd array: ");
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }

        System.out.print("Enter elements of 3rd array: ");
        for (int i = 0; i < k; i++) {
            arr3[i] = sc.nextInt();
        }

        sort(arr);
        sort(arr2);
        sort(arr3);


        System.out.print("\nValue of 3rd array: ");
        for (int i = 0; i < k; i++) {
            System.out.print(arr3[i] + " ");
        }

        System.out.print("\nValue of 2nd array: ");
        for (int i = 0; i < m; i++) {
            System.out.print(arr2[i] + " ");
        }

        System.out.print("\nValue of 1st array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }



        System.out.println("\n\nCommon elements: ");
        int[] common = new int[n + m + k];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < k; l++) {
                    if (arr[i] == arr2[j] && arr2[j] == arr3[l]) {
                        common[count] = arr[i];
                        count++;
                    }
                }
            }
        }

        if (count == 0) {
            System.out.println("No common elements");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.print(common[i] + " ");
            }
        }

    }
}
