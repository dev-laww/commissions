import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%-25s","Employee Name: ");
        String name = scanner.nextLine();
        System.out.printf("%-25s", "Gross Pay: ");
        double grossPay = scanner.nextDouble();

        double withholdingTax = grossPay * 0.15;
        double sssContribution = grossPay * 0.0363;
        double mediaCare = grossPay * 0.0125;
        double pagibigContribution = 100.0;

        double netPay = grossPay - withholdingTax - sssContribution - mediaCare - pagibigContribution;

        System.out.println("---------------------------------------------");
        System.out.printf("%-25s%-20s%n", "Deduction", "Amount");
        System.out.printf("%-25s%-20.1f%n", "Withholding Tax", withholdingTax);
        System.out.printf("%-25s%-20.1f%n", "SSS Contribution", sssContribution);
        System.out.printf("%-25s%-20.1f%n", "Mediacare", mediaCare);
        System.out.printf("%-25s%-20.1f%n", "Pag-ibig Contribution", pagibigContribution);
        System.out.println("---------------------------------------------");
        System.out.printf("%-25s%-20.1f%n", "Net Pay", netPay);
    }
}