import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DecimalFormat df = new DecimalFormat("0.00");
        double m, q, c, to, p, ch, discount, to2;
        try {
            System.out.println("Menu:");
            System.out.println("[1] Meal A(P100.00)");
            System.out.println("[2] Meal B(P150.00)");
            System.out.println("Enter Order:");
            m = Double.parseDouble(br.readLine());
            if (m == 1) {
                System.out.println("Enter Quantity:");
                q = Double.parseDouble(br.readLine());
                System.out.println("Type of Customer:");
                System.out.println("[1] Regular");
                System.out.println("[2] Senior Citizen(20% Discount)");
                System.out.println("Enter Customer:");
                c = Double.parseDouble(br.readLine());
                to = q * 100.00;
                if (c == 1) {
                    System.out.println("Your Total Order is: " + to);
                    System.out.println("Enter Payment: ");
                    p = Double.parseDouble(br.readLine());
                    ch = p - to;
                    System.out.println("Your change is: " + ch);
                } else if (c == 2) {
                    to2 = to - (to * .20);
                    System.out.println("Your Total Order is: " + to2);
                    System.out.println("Enter Payment: ");
                    p = Double.parseDouble(br.readLine());
                    ch = p - to2;
                    System.out.println("Your change is: " + ch);
                }
            } else if (m == 2) {
                System.out.println("Enter Quantity:");
                q = Double.parseDouble(br.readLine());
                System.out.println("Type of Customer:");
                System.out.println("[1] Regular");
                System.out.println("[2] Senior Citizen(20% Discount)");
                System.out.println("Enter Customer:");
                c = Double.parseDouble(br.readLine());
                to = q * 150.00;
                if (c == 1) {
                    System.out.println("Your Total Order is: " + to);
                    System.out.println("Enter Payment: ");
                    p = Double.parseDouble(br.readLine());
                    ch = p - to;
                    System.out.println("Your change is: " + ch);
                } else if (c == 2) {
                    to2 = to - (to * .20);
                    System.out.println("Your Total Order is: " + to2);
                    System.out.println("Enter Payment: ");
                    p = Double.parseDouble(br.readLine());
                    ch = p - to2;
                    System.out.println("Your change is: " + ch);
                }
            }
        } catch (Exception ac3) {
            System.out.println("Try AGAIN");
        }
    }
}