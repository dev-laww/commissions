/*

Activity #1 (50 pts)
Deadline of Submission: Nov 18 11:59pm
Submission link: https://drive.google.com/drive/folders/1qK6Ouvi05k_f0mCSAM_nABa4-MfX4XT1?usp=sharing
Create your own folder in the link above following the format: <lastName>_<firstName>_0000<studentNumber>
Do not apply any feature or topic that we haven't discussed yet

Given an array of integer [0, 1, 2, 4, 10, 22, 50, 51, 60, 200, 100, 1001, 2003, 54023, 237493, 10000, 23400, 284750].
Check if the length of the array is even or odd, if it is even then add all even elements of the array to a new array with variable name
evenElements otherwise if the length of the array is odd then add the significant value of each element in the array with variable name
signicantValueElements e.g. 50 -> 1, 2003 -> 4, 10000 -> 1 (significant value is the count of numbers before trailing N zero number).



example: array = [10, 20, 30, 33, 1001, 200]
	 length = 6
	 evenElements = [10, 20, 30, 200]
	 significantValueElements = []

	 array = [10, 20, 30, 33, 1001, 200, 201]
	 length = 7
	 evenElements = []
	 significantValueElements = [1, 1, 1, 2, 4, 1, 3]

Regardless which array is created (evenElements or significantValueElement), check if the array is a palindrome
 - a palindrome is sequence that reads the same backwards as forward e.g. [1, 1, 1, 4, 1, 1, 1] -> [1, 1, 1, 4, 1, 1, 1].
If the array is a palindrome then count the occurrence of each element of the array e.g. [1, 1, 1, 4, 1, 1, 1] -> 1 = 6, 4 = 1
and put it in a multidimensional array with the outer matrix holding the value of the element and the inner matrix holding the value
of the occurrence of the specific element, make sure that in index 0 the number stored is the element itself while in index 1 is the
count of occurrence e.g. [1, 1, 1, 4, 1, 1, 1] -> array[][] = [[1,6],[4,1]] where 1 and 6 or 4 and 1 is the element and count of occurrence
in the original array respectively. After putting the desired values in the multidimensional array, display it in the order below:

Element 1 occurred 6 times..
Element 4 occurred once..
Element 6 occurred twice..
Element 10 occurred 3 times..
END

If the array is not palindrome, display the roman numeral of each element of the array e.g. [1, 10, 100] -> I, X, C with the format below:

given: [1, 10, 100] -> I, X, C

Element 1 is I..
Element 10 is X..
Element 100 is C..
END

*
* */

import java.util.Arrays;

public class Main {
    // Explain isPalindrome method
    // the method checks if the array is a palindrome
    // it is sequence that reads the same backwards as forward e.g. [1, 1, 1, 4, 1, 1, 1] -> [1, 1, 1, 4, 1, 1, 1]
    // first you'll need to create a new array with the same length as the original array
    // then you'll need to copy the elements of the original array to the new array in reverse order
    // then you'll need to compare the original array and the new array
    public static boolean isPalindrome(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - 1 - i];
        }
        return Arrays.equals(array, reversedArray);
    }

    // Explain countOccurrence method
    // the method counts the occurrence of each element of the array
    // e.g. [1, 1, 1, 4, 1, 1, 1] -> 1 = 6, 4 = 1
    // and put it in a multidimensional array with the outer matrix holding the value of the element and the inner matrix holding the value
    // of the occurrence of the specific element, make sure that in index 0 the number stored is the element itself while in index 1 is the
    // count of occurrence e.g. [1, 1, 1, 4, 1, 1, 1] -> array[][] = [[1,6],[4,1]] where 1 and 6 or 4 and 1 is the element and count of occurrence
    // in the original array respectively.
    public static int[][] countOccurrence(int[] array) {
        int[][] result = new int[array.length][2];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int k : array) {
                if (array[i] == k) {
                    count++;
                }
            }
            result[i][0] = array[i];
            result[i][1] = count;
            count = 0;
        }
        Arrays.stream(result).forEach(x -> System.out.println(
                "Element " + x[0] + " occurred " + x[1] + "times" + ".."));
        return result;
    }

    // Explain romanNumeral method
    // the method converts the given number to roman numeral
    // e.g. 1 -> I, 10 -> X, 100 -> C
    // this method fails if the given number is greater than 3999 because of the limitation of roman numeral
    public static String romanNumeral(int number) {
        // I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
        String[] romanNumeral = {"I", "V", "X", "L", "C", "D", "M"};
        int[] romanNumeralValue = {1, 5, 10, 50, 100, 500, 1000};
        StringBuilder result = new StringBuilder();
        for (int i = romanNumeralValue.length - 1; i >= 0; i--) {
            while (number >= romanNumeralValue[i]) {
                result.append(romanNumeral[i]);
                number -= romanNumeralValue[i];
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Given an array of integer [0, 1, 2, 4, 10, 22, 50, 51, 60, 200, 100, 1001, 2003, 54023, 237493, 10000, 23400, 284750]
        int[] array = {0, 1, 2, 4, 10, 22, 50, 51, 60, 200, 100, 1001, 2003, 54023, 237493, 10000, 23400, 284750};
        int[] evenElements = {}, significantValueElements = {};
        // Check if the length of the array is even or odd
        if (array.length % 2 == 0) {
            // if it is even then add all even elements of the array to a new array with variable name evenElements
            evenElements = Arrays.stream(array).filter(
                    element -> element % 2 == 0
            ).toArray();

        } else {
            // otherwise if the length of the array is odd then add the significant value of each element in the array with variable name
            // signicantValueElements e.g. 50 -> 1, 2003 -> 4, 10000 -> 1 (significant value is the count of numbers before trailing N zero number).
            significantValueElements = Arrays.stream(array).map(x -> {
                int count = 0;
                while (x % 10 == 0) {
                    x /= 10;
                    count++;
                }
                return count;
            }).toArray();
        }
        // Print out the significant value of each element of the array
        System.out.println("Given: " + Arrays.toString(array) );
        System.out.println("Length: " + array.length);
        System.out.println("Significant value of each element: " + Arrays.toString(significantValueElements));
        System.out.println("Even elements: " + Arrays.toString(evenElements));
        // Regardless which array is created (evenElements or significantValueElement), check if the array is a palindrome
        if (evenElements.length != 0) {
            // If the array is a palindrome then count the occurrence of each element of the array e.g. [1, 1, 1, 4, 1, 1, 1] -> 1 = 6, 4 = 1
            // and put it in a multidimensional array with the outer matrix holding the value of the element and the inner matrix holding the value
            // of the occurrence of the specific element, make sure that in index 0 the number stored is the element itself while in index 1 is the
            // count of occurrence e.g. [1, 1, 1, 4, 1, 1, 1] -> array[][] = [[1,6],[4,1]] where 1 and 6 or 4 and 1 is the element and count of occurrence
            // in the original array respectively. After putting the desired values in the multidimensional array, display it in the order below

            // Check isPalindrome method for more details (line 56
            if (isPalindrome(evenElements)) {
                // Check countOccurrence method for more details (line 64)
                int[][] result = countOccurrence(evenElements);
                for (int[] i : result) {
                    System.out.println("Element " + i[0] + " occurred " + i[1] + " times..");
                }
            } else {
                // If the array is not palindrome, display the roman numeral of each element of the array e.g. [1, 10, 100] -> I, X, C with the format below:
                for (int i : evenElements) {
                    if (i != 0)
                        System.out.println("Element " + i + " is " + romanNumeral(i) + "..");
                }
            }
        } else {
            if (isPalindrome(significantValueElements)) {
                int[][] result = countOccurrence(significantValueElements);
                for (int[] i : result) {
                    System.out.println("Element " + i[0] + " occurred " + i[1] + " times..");
                }
                System.out.println("END");
            } else {
                for (int i : significantValueElements) {
                    if (i != 0)
                        System.out.println("Element " + i + " is " + romanNumeral(i) + "..");
                }
            }
        }
        System.out.println("END");
        // - a palindrome is sequence that reads the same backwards as forward e.g. [1, 1, 1, 4, 1, 1, 1] -> [1, 1, 1, 4, 1, 1, 1].

    }
}