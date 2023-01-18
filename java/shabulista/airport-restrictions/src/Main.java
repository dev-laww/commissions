import java.util.Random;
import java.util.Scanner;

public class Main {
/*    public static void check(int a, int b, int c, int d, int e) {
        if (!(a <= d && b <= d && c <= d)) {
            System.out.println("NO");
            return;
        }
        if (!(a + b <= e || a + c <= e || b + c <= e)) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }
*/

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int t;
        while (true) {
            System.out.print("Enter number of test cases: ");
            t = sc.nextInt();

            if (!(t >= 1 && t <= 36000)) {
                System.out.println("Invalid number of test cases");
                continue;
            }

            break;
        }

        for (int i = 0; i < t; i++) {
            // 0 1 2 3 4 5 6 7 8 9
            // 1 2 3 4 5 6 7 8 9 10
            int a = random.nextInt(9) + 1;
            int b = random.nextInt(9) + 1;
            int c = random.nextInt(9) + 1;
            // 15 16 17 18 19 20
            int d = random.nextInt(15, 21);
            int e = random.nextInt(5, 11);

            System.out.println("--------------------------------------------------------");
            System.out.println("TEST " + (i + 1) + ": \n");
            System.out.println("Bag A: " + a);
            System.out.println("Bag B: " + b);
            System.out.println("Bag C: " + c);
            System.out.println("TOTAL SUM 0F BAG TO BE CHECK-IN: " + d);
            System.out.println("BAG WEIGHT TO BE CARRIED: " + e);

            System.out.println("--------------------------------------------------------");
            System.out.print("CAN KYLE TAKE ALL THE BAGS? ");
            if (a <= d && b <= d && c <= d) {
                if (a + b <= e || a + c <= e || b + c <= e) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else {
                System.out.println("NO");
            }

//            check(a, b, c, d, e);
            System.out.println("--------------------------------------------------------");

        }
    }
}