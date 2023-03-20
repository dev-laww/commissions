package module3.exercise3_2methodsAndWithoutParameters;

import java.util.Scanner;

public class Author {
    int authorId;
    String lastName;
    String firstName;

    public void setInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter author ID: ");
        this.authorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter author's last name: ");
        this.lastName = scanner.nextLine();
        System.out.print("Enter author's first name: ");
        this.firstName = scanner.nextLine();

    }
}
