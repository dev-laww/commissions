public class TypeCasting {
    public static void main(String[] args) {
        // Widening Casting (automatically) - converting a smaller type to a larger type size
        // byte -> short -> char -> int -> long -> float -> double
        int myInt = 9;
        double myFloat = myInt;


        // Automatic casting: int to double
        System.out.println(myInt);      // Outputs 9
        System.out.println(myFloat);   // Outputs 9.0

        // Narrowing Casting (manually) - converting a larger type to a smaller size type
        // double -> float -> long -> int -> char -> short -> byte
        double myDouble2 = 9.78d;
        int myInt2 = (int) myDouble2; // Manual casting: double to int
        System.out.println(myDouble2);   // Outputs 9.78
        System.out.println(myInt2);      // Outputs 9

       // Type Casting: String to int
        String myString = "9";
        int myInt3 = Integer.parseInt(myString); // Returns 9
        System.out.println(myInt3); // Outputs 9

        // Type Casting: int to String
        int myInt4 = 9;
        String myString2 = Integer.toString(myInt4); // Returns "9"
        System.out.println(myString2); // Outputs 9

        // Type Casting: String to double
        String myString3 = "9.78";
        double myDouble3 = Double.parseDouble(myString3); // Returns 9.78
        System.out.println(myDouble3); // Outputs 9.78

        // Type Casting: double to String
        double myDouble4 = 9.78;
        String myString4 = Double.toString(myDouble4); // Returns "9.78"
        System.out.println(myString4); // Outputs 9.78
    }
}
