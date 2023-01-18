/**
 * Note: Try the regex version too!
 *
 * @author tora
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /**
     * This is a method that will check the input string for the following:
     * 1. The string length must be 50 characters long and not more than 100 characters.
     * 2. The string follows a pattern of 3 numbers, 3 hashes and !.
     * 3. The numbers are in the range of 0-9.
     *
     * @param input The input string to be checked.
     * @return String array that contains the pattern and the characters before it
     */
    public static String[] match(String input) {
//        if (!(input.length() < 50 || input.length() >= 100)) {
//            System.out.println("The string length is not between 50 and 100 characters.");
//            // return null if the string is not in the correct length
//            return null;
//        }

        StringBuilder match = new StringBuilder();

        for (int i = 0; i < input.length() - 7; i++) {
            if (i != 0) match.append(input.charAt(i));
            // Explicitly check every character in the string to see if it matches the pattern
            if (Character.isDigit(input.charAt(i)) && Character.isDigit(input.charAt(i + 1)) && Character.isDigit(input.charAt(i + 2)) && input.charAt(i + 3) == '#' && input.charAt(i + 4) == '#' && input.charAt(i + 5) == '#' && input.charAt(i + 6) == '!') {
                match.insert(0, input.charAt(0));
                return new String[]{match.toString(), input.substring(i, i + 7)};
            }
        }

        // return null if the string does not match the pattern
        return null;
    }

    /**
     * This is the alternative solution to the problem. It uses regex to check the string.
     * As you can see, it is much shorter and easier to read.
     *
     * @param input The input string to be checked.
     * @return String array that contains the pattern and the characters before it
     */
    public static String[] match_regex(String input) {
//        if (!(input.length() < 50 || input.length() >= 100)) {
//            System.out.println("The string length is not between 50 and 100 characters.");
//            // return null if the string is not in the correct length
//            return null;
//        }

        // Declare the regex pattern
        Pattern regex = Pattern.compile("(.*)([0-9][0-9][0-9]###!)");
        // Declare the matcher
        Matcher matcher = regex.matcher(input);
        // Check if the input matches the pattern

        if (matcher.find()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        }

        // return null if the string does not match the pattern
        return null;
    }

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        System.out.print("Input: ");
        String input = scanner.nextLine();

        // Calling the method match() and passing the input string
        String[] result = match(input);
        String[] result_regex = match_regex(input);

        // Check if the result is null
        if (result != null) {
            System.out.println("Output: ");
            // !result[0].isEmpty() ? result[0] : "null" is a ternary operator that checks if result[0] is empty or not
            System.out.println("Remaining program: " + (!result[0].isEmpty() ? result[0].substring(0, result[0].length() - 1) : "null"));
            System.out.println("Virus: " + result[1]);
        }

        // Check if the result is null
        System.out.println("\n--------------------------------------------");
        if (result_regex != null) {
            System.out.println("This is the regex version:\n");
            System.out.println("Output:");
            System.out.println("Remaining program: " + (!result_regex[0].isEmpty() ? result_regex[0] : "null"));
            System.out.println("Virus: " + result_regex[1]);
        }*/

       String[][] items = {
            //   name     color   category
               {"redmi", "blue", "phone"}
       };
    }
}