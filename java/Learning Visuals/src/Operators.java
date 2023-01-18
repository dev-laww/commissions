public class Operators {
    public static void main(String[] args) {
        // Arithmetic Operators
        int x = 5;
        int y = 3;
        System.out.println(x + y); // Outputs 8
        System.out.println(x - y); // Outputs 2
        System.out.println(x * y); // Outputs 15
        System.out.println(x / y); // Outputs 1
        System.out.println(x % y); // Outputs 2

        // Bitwise Operators
        int a = 12; // 1100
        int b = 6; // 0110
        System.out.println(a & b); // Outputs 4
        /*
        * 1100
        * 0110
        * ----
        * 0100
        */
        System.out.println(a | b); // Outputs 14
        /*
        * 1100
        * 0110
        * ----
        * 1110
         */
        System.out.println(a ^ b); // Outputs 10
        /*
        * 1100
        * 0110
        * ----
        *
        * 1 ^ 1 = 0
        * 1 ^ 0 = 1
        * 0 ^ 1 = 1
        * 0 ^ 0 = 0
         */
        System.out.println(~a); // Outputs -13
        /*
        * 1100
        * ----
        * 0011
         */
        System.out.println(a << 1); // Outputs 24
        /*
        * 1100
        * ----
        * 11000
         */
        System.out.println(a >> 1); // Outputs 6
        /*
        * 1100
        * ----
        * 110
         */

        // Assignment Operators
        a = 5;
        b = 3;
        a += b; // a = a + b
        System.out.println(a); // Outputs 8
        a -= b; // a = a - b
        System.out.println(a); // Outputs 5
        a *= b; // a = a * b
        System.out.println(a); // Outputs 15
        a /= b; // a = a / b
        System.out.println(a); // Outputs 5
        a %= b; // a = a % b
        System.out.println(a); // Outputs 2

        // Comparison Operators
        int c = 5;
        int d = 3;
        System.out.println(c == d); // Outputs false
        System.out.println(c != d); // Outputs true
        System.out.println(c > d); // Outputs true
        System.out.println(c < d); // Outputs false
        System.out.println(c >= d); // Outputs true
        System.out.println(c <= d); // Outputs false

        // Logical Operators
        int e = 5;
        int f = 3;
        System.out.println(e > f && e != f); // Outputs true
        System.out.println(e > f || e != f); // Outputs true
        System.out.println(!(e > f)); // Outputs false

        // Increment and Decrement Operators
        int g = 5;
        int h = 3;
        System.out.println(g++); // Outputs 5 (g = 6)
        System.out.println(++h); // Outputs 4 (h = 4)
        System.out.println(g--); // Outputs 6 (g = 5)
        System.out.println(--h); // Outputs 3 (h = 3)

        // Ternary Operator
        int i = 1;
        int j = 3;
        System.out.println(i > j ? "i is greater than j" : "i is less than than j"); // Outputs "i is greater than j


        // condition ? true : false
    }
}
