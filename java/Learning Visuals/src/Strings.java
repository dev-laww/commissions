public class Strings {
    public static  void main(String[] args) {
        String txt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("The length of the txt string is: " + txt.length());

        // String Methods
        String firstName = "John";
        String lastName = "Doe";
        System.out.println(firstName + " " + lastName);
        System.out.println(firstName.concat(" ").concat(lastName));
        System.out.println(firstName.toUpperCase());
        System.out.println(firstName.toLowerCase());

        // String Index
        String txt2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println(txt2.charAt(0));
        System.out.println(txt2.indexOf("E"));

        // String Comparison
        String txt3 = "Hello";
        String txt4 = "Hello";
        System.out.println(txt3.equals(txt4));

        // Find how to capitalize the first letter of a string
        // Find how to check if a string contains a substring
        // Find how to replace a substring with another substring
        // Find how to remove whitespace from the beginning and end of a string
        // Find how to split a string into substrings
    }
}
