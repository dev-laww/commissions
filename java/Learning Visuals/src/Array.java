import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        // One Dimensional Array
        int[] myNum = {10, 20, 30, 40};
                    //  0   1   2   3

//        int[] filteredArray = Arrays.stream(myNum)
//                .filter(it -> it > 15)
//                .toArray();

        for (int num : myNum) {
            if (num > 15)
                System.out.println(num);
        }

/*        // Accessing Array Elements
        System.out.println(myNum[0]);  // 10
        System.out.println(myNum[1]);  // 20
        System.out.println(myNum[2]);  // 30
        System.out.println(myNum[3]);  // 40

        // Change Array Element
        myNum[0] = 15;
        System.out.println(myNum[0]); // 15

        // Loop Through an Array
        for (int i = 0; i < myNum.length; i++) {
            System.out.println(myNum[i]);
        }

        // For Each Loop
        for (int x : myNum) {
            System.out.println(x);
        }

        for (String y : names) {
            System.out.println(y);
        }

        // Two Dimensional Array
        int[][] myNumbers = {
              // 0  1  2  3
                {1, 2, 3, 4}, // 0
              // 0  1  2
                {5, 6, 7} // 1
        };

        int x = myNumbers[1][1];
        System.out.println(x);
        */


    }
}
