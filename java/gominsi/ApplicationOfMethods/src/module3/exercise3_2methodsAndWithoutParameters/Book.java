package module3.exercise3_2methodsAndWithoutParameters;

import java.util.Scanner;

public class Book {
    int bookId;
    String title;
    String ISBN;
    String category;

    public void setInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book ID: ");
        this.bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter book title: ");
        this.title = scanner.nextLine();
        System.out.println("Enter book ISBN: ");
        this.ISBN = scanner.nextLine();
        System.out.println("Enter book category: ");
        this.category = scanner.nextLine();
    }

}
