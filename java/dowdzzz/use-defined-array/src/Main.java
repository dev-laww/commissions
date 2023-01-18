import java.util.Scanner;

/**
 * @author tora
 */

// Given an array A of N integers, classify it as being Good, Bad or Average. It is called
// Good if it contains exactly X distinct integers, Bad if it contains less than X distinct
// integers and Average if it contains more than X distinct integers.

// Input format:
// The first line contains an integer T, the number of test cases. Then the test cases follow.
// The first line of each test case contains two integers N and X. The second line of each
// test case contains N space-separated integers A1,A2,…,AN.

// Constraints:
// 1 <= T <= 50
// 1 <= X, N <= 13000
// 1 <= A[i] <= 10^9
class OrdArray {
    private final long[] a;
    private int nElems;

    public OrdArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems-1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound ) / 2;
            if(a[curIn] ==  searchKey)
                return curIn;
            else if(lowerBound > upperBound)
                return nElems;
            else {
                if(a[curIn] < searchKey)
                    lowerBound = curIn + 1;
                else
                    upperBound = curIn - 1;
            }
        }
    }

    public long get(int index) {
        return a[index];
    }

    public void insert(long value) {
        int j;
        for(j = 0;j < nElems;j++)
            if(a[j] > value)
                break;
        for(int k = nElems; k > j; k--)
            a[k] = a[k - 1];
        a[j] = value;
        nElems++;
    }

    public void delete(long value) {
        int j = find(value);
        if (j != nElems) {
            for(int k = j;k < nElems;k++)
                a[k] = a[k + 1];
            nElems--;
        }
    }

    public void display()  {
        for(int j = 0;j < nElems;j++)
            System.out.print(a[j] + " ");
        System.out.println();
    }

}

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            System.out.println("Enter the number of elements and the number of distinct elements: ");
            int n = sc.nextInt();
            OrdArray arr = new OrdArray(n);
            int x = sc.nextInt();
            System.out.println("Enter the elements: ");
            for (int j = 0; j < n; j++) {
                arr.insert(sc.nextLong());
            }
            int count = 1;
            for (int j = 1; j < n; j++) {
                if (arr.get(j) != arr.get(j - 1)) {
                    count++;
                }
            }
            if (count == x) {
                System.out.println("Good");
            } else if (count < x) {
                System.out.println("Bad");
            } else {
                System.out.println("Average");
            }
        }

    }
}