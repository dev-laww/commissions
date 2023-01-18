public class Variables {
    public static void main(String[] args) {

        // type variableName = value;

        int myNum = 5;
        float myFloatNum = 5.99f;
        char myLetter = 'D';
        boolean myBool = true;
        // String is not a primitive data type, it is a class.
        String myText = "Hello";
        System.out.println(myNum);
        System.out.println(myFloatNum);
        System.out.println(myLetter);
        System.out.println(myBool);
        System.out.println(myText);

        // Initialize variable without value
        int x;
        x = 5;

        System.out.println(x);

        // Initialize variable with value and modify it
        int y = 5;
        System.out.println(y);
        y = 6;
        System.out.println(y);

        // Multiple variables of the same type
        int a, b, c;
        a = 5;
        b = 6;
        c = 7;

        // Multiple variables of different types
        int d = 5, e = 6, f = 7;



        // Assign the same value to multiple variables
        int h, i;
        int g = h = i = 5;



    }
}
