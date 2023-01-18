public class Loops {
    public static void main(String[] args) {
        // While Loop
        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }

        // Do While Loop
        int j = 0;
        do {
            System.out.println(j);
            j++;
        } while (j < 5);

        // For Loop
        for (int k = 0; k < 5; k++) {
            System.out.println(k);
        }

        // For Each Loop
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String car : cars) {
            System.out.println(car);
        }

        // Break
        for (int l = 0; l < 10; l++) {
            if (l == 4) {
                break;
            }
            System.out.println(l);
        }

        // Continue
        for (int m = 0; m < 10; m++) {
            if (m == 4) {
                continue;
            }
            System.out.println(m);
        }
    }
}
