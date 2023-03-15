package module2_excercise2_1_datatypesAndOperator;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class BookAquisitionRunner {
    byte bookQuantity;
    float discountedBookPrice;
    boolean orderBook = false;
    float totalPrice;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Author author = new Author();
        Book book = new Book();
        Supplier supplier = new Supplier();
        BookAquisitionRunner bookAquisitionRunner = new BookAquisitionRunner();
        bookAquisitionRunner.orderBook = true;

        JOptionPane.showMessageDialog(null, "Welcome!");
        System.out.println("----------------------------------------");
        System.out.print("Enter supplier name: ");
        supplier.supplierName = input.nextLine();
        System.out.print("Enter supplier address: ");
        supplier.supplierAddress = input.nextLine();

        String supplierId = JOptionPane.showInputDialog("Enter supplier ID: ");
        supplier.supplierId = Integer.parseInt(supplierId);
        String supplierTelephoneNumber = JOptionPane.showInputDialog("Enter supplier telephone number: ");
        supplier.supplierTelephoneNumber = Long.parseLong(supplierTelephoneNumber);

        System.out.println("----------------------------------------");
        System.out.print("Enter book title: ");
        book.bookTitle = input.nextLine();
        System.out.print("Enter book category: ");
        book.bookCategory = input.nextLine().charAt(0);
        String bookId = JOptionPane.showInputDialog("Enter book ID: ");
        book.bookId = Integer.parseInt(bookId);
        String bookPrice = JOptionPane.showInputDialog("Enter book price: ");
        book.bookPrice = Double.parseDouble(bookPrice);

        System.out.println("----------------------------------------");
        System.out.print("Enter author first name: ");
        author.bookAuthorFirstName = input.nextLine();
        System.out.print("Enter author last name: ");
        author.bookAuthorLastName = input.nextLine();

        String bookQuantity = JOptionPane.showInputDialog("Enter book quantity: ");
        bookAquisitionRunner.bookQuantity = Byte.parseByte(bookQuantity);
        float discountPercentage = bookAquisitionRunner.bookQuantity >= 4 ? 0.25f : 0f;
        discountPercentage = bookAquisitionRunner.bookQuantity >= 6 ? 0.5f : discountPercentage;

        // check if the book category is the same is the author's first name
        if (book.bookCategory == author.bookAuthorFirstName.charAt(0)) {
            discountPercentage += 0.2f;
        }

        // check if the book and author is the same
        if (book.bookTitle.equals(author.bookAuthorFirstName) && book.bookTitle.equals(author.bookAuthorLastName)) {
            discountPercentage += 0.1f;
        }

        bookAquisitionRunner.discountedBookPrice = (float) book.bookPrice * discountPercentage;

        bookAquisitionRunner.totalPrice = (float) (
                bookAquisitionRunner.bookQuantity * (book.bookPrice - bookAquisitionRunner.discountedBookPrice)
        );

        double totalPrice = bookAquisitionRunner.totalPrice;
        int bookQuantityInt = bookAquisitionRunner.bookQuantity;

        System.out.println("\n\n----------------------------------------");
        System.out.println("Supplier name: " + supplier.supplierName);
        System.out.println("Supplier address: " + supplier.supplierAddress);
        System.out.println("Supplier ID: " + supplier.supplierId);
        System.out.println("Supplier telephone number: " + supplier.supplierTelephoneNumber);
        System.out.println();
        System.out.println("Book title: " + book.bookTitle);
        System.out.println("Book category: " + book.bookCategory);
        System.out.println("Book quantity: " + bookQuantityInt);
        System.out.println();
        System.out.println("Author first name: " + author.bookAuthorFirstName);
        System.out.println("Author last name: " + author.bookAuthorLastName);
        System.out.println();
        System.out.println("Book price: " + book.bookPrice);
        System.out.println("Discounted book price: " + bookAquisitionRunner.discountedBookPrice);
        System.out.println("Total price: " + totalPrice);
    }
}
