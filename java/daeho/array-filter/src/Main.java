/**
 * @author tora
 */

/*
 * Given a 2 dimensional array representing an inventory of your client's store.
 * Each nested array describes an item such that arr[i] = [name, color, category].
 * You were asked to code a filtering system for the store's users.
 *
 * Given the filtering variables filterKey and filterValue, return a list such that it matches one of the following criteria:
 * 1. if filterKey is "name", then the item's name must be equal to filterValue.
 * 2. if filterKey is "color", then the item's color must be equal to filterValue.
 * 3. if filterKey is "category", then the item's category must be equal to filterValue.
 *
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /**
     * This method is used to filter the inventory based on the filterKey and filterValue
     *
     * @param inventory   - 2 dimensional array representing an inventory of your client's store
     *                    Each nested array describes an item such that arr[i] = [name, color, category]
     * @param filterKey   - the key to filter the inventory
     *                    filterKey can be "name", "color" or "category"
     * @param filterValue - the value to filter the inventory
     *                    filterValue can be any string
     * @return - a list of items that matches the filterKey and filterValue
     */
    public static String[][] filterItems(String[][] inventory, String filterKey, String filterValue) {

        // create a new 2 dimensional array to store the filtered items
        String[][] result = new String[inventory.length][3];
        // create a counter to keep track of the number of filtered items
        int count = 0;

        // loop through the inventory 2D array
        // "String[] item : inventory" is the same as String[] item = inventory[i] each time the loop runs
        for (String[] item : inventory) {
            // I used a switch statement to check the filterKey instead of if statements to make the code more readable
            switch (filterKey) {
                case "name":
                    // if the item's name is equal to the filterValue, add the item to the result array
                    if (item[0].equals(filterValue)) {
                        result[count] = item;
                        count++;
                    }
                    break;
                case "color":
                    // if the item's color is equal to the filterValue, add the item to the result array
                    if (item[1].equals(filterValue)) {
                        result[count] = item;
                        count++;
                    }
                    break;
                case "category":
                    // if the item's category is equal to the filterValue, add the item to the result array
                    if (item[2].equals(filterValue)) {
                        result[count] = item;
                        count++;
                    }
                    break;
            }
        }
        // create a new array with the correct size
        String[][] finalResult = new String[count][3];
        // copy the items from the result array to the finalResult array
        System.arraycopy(result, 0, finalResult, 0, count);
        // return the result array
        return finalResult;
    }

    /**
     * Advanced version of the filterItems method
     * More readable and easier to maintain
     *
     * @param inventory   - 2 dimensional array representing an inventory of your client's store
     *                    Each nested array describes an item such that arr[i] = [name, color, category]
     * @param filterKey   - the key to filter the inventory
     *                    filterKey can be "name", "color" or "category"
     * @param filterValue - the value to filter the inventory
     *                    filterValue can be any string
     * @return - a list of items that matches the filterKey and filterValue
     */
    public static String[][] filterItemsAdvanced(String[][] inventory, String filterKey, String filterValue) {
        // used a switch statement to get the index of the filterKey and directly returning the value instead of using if statements
        // this is same as "int index = filterKey.equals("name") ? 0 : filterKey.equals("color") ? 1 : 2;" default return value is null
        return switch (filterKey) {
            // case "case" -> Arrays.stream(inventory)
            //                    .filter(i -> i[2].equals(filterValue))
            //                    .toArray(String[][]::new);
            // Arrays.stream(inventory) is used to convert the 2D array to a stream
            // .filter(item -> item[index].equals(filterValue)) is used to filter the items based on the filterKey and filterValue
            // .toArray(String[][]::new) is used to convert the stream back to a 2D array
            // the toArray method takes a function that returns a new array of the same type as the stream
            // you can make each return case more compact by doing "yield Arrays.stream(inventory).filter(item -> item[index].equals(filterValue)).toArray(String[][]::new);"
            case "name" -> Arrays.stream(inventory).filter(i -> i[0].equals(filterValue)).toArray(String[][]::new);
            case "color" -> Arrays.stream(inventory).filter(i -> i[1].equals(filterValue)).toArray(String[][]::new);
            case "category" -> Arrays.stream(inventory).filter(i -> i[2].equals(filterValue)).toArray(String[][]::new);
            default -> null;
        };

    }

    public static void main(String[] args) {
        /*// Messy code to test the filterItems method
        System.out.println("Test Case 1:");
        // create a 2 dimensional array to store the inventory
        String[][] inventory = {{"redmi", "blue", "phone"},{"acer", "gray","computer"},{"brush", "brown", "art"}};
        // convert the 2D array to a string to print it
        System.out.println("Input: " + Arrays.deepToString(inventory));
        // call the filterItems method to filter the inventory based on the filterKey and filterValue
        System.out.println("filterKey: category");
        System.out.println("filterValue: computer");
        String[][] result = filterItems(inventory, "category", "computer");
        System.out.println(Arrays.deepToString(result) + "\n");

        System.out.println("Test Case 2:");
        String[][] inventory_2 = {{"redmi", "blue", "phone"}, {"dell", "red", "computer"}, {"pixel", "white", "phone"}, {"brush", "brown", "art"}}; // test case 2
        System.out.println("Input: " + Arrays.deepToString(inventory_2));
        System.out.println("filterKey: category");
        System.out.println("filterValue: phone");
        result = filterItems(inventory_2, "category", "phone");
        System.out.println(Arrays.deepToString(result) + "\n");

        System.out.println("\n------------------------");
        System.out.println("Advanced Test Case 1:");
        System.out.println("Input: " + Arrays.deepToString(inventory));
        System.out.println("filterKey: category");
        System.out.println("filterValue: computer");
        String[][] resultAdvanced = filterItemsAdvanced(inventory, "category", "computer");
        System.out.println(Arrays.deepToString(resultAdvanced));

        System.out.println("\nAdvanced Test Case 2:");
        System.out.println("Input: " + Arrays.deepToString(inventory_2));
        System.out.println("filterKey: category");
        System.out.println("filterValue: phone");
        resultAdvanced = filterItemsAdvanced(inventory_2, "category", "phone");
        System.out.println(Arrays.deepToString(resultAdvanced));*/

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter an integer");
            int n = sc.nextInt();
            System.out.println(n);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        System.out.println("After try catch");

    }
}