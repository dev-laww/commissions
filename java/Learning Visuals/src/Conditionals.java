public class Conditionals {
    public static void main(String[] args) {
        // If Statement
        int x = 20;
        int y = 18;
        if (x > y) {
            System.out.println("x is greater than y");
        }

        // If Else Statement
        int time = 20;
        if (time < 18) {
            System.out.println("Good day.");
        } else {
            System.out.println("Good evening.");
        }


        // If Else If Statement
        int time2 = 22;
        if (time2 < 10) {
            System.out.println("Good morning.");
        } else if (time2 < 20) {
            System.out.println("Good day.");
        } else {
            System.out.println("Good evening.");
        }

        // Nested If Statement
        int time3 = 20;
        if (time3 < 18) {
            System.out.println("Good day.");
        } else {
            System.out.println("Good evening.");
            if (time3 < 22) {
                System.out.println("It's a nice evening.");
            } else {
                System.out.println("It's a bit too late.");
            }
        }

        // Terenary Operator
        int time4 = 20;
        String result = time4 < 18 ? "Good day." : "Good evening.";
        System.out.println(result);

/*        if (time4 < 18) {
            String result = "Good day";
            System.out.println(result);
        } else {
            String result = "Good evening";
            System.out.println(result);
        }
*/



        // Switch Statement
        int day = 4;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day");
        }

        // Note: in default case, you don't need to use break
    }
}
