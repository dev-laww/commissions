package module3.exercise3_2methodsAndWithoutParameters;

/**
 * Justin Garcia
 * ITE012 - IT12S5
 * 3/20/2023
 * Practice Exercise 3.2 Application of Methods with and without Parameters
 */

public class Runner {
    public static void main(String[] args) {
        Author author = new Author();
        author.setInfo();


        Book book = new Book();
        book.setInfo();


        Borrower borrower = new Borrower();
        borrower.setBorrowerId(1);
        borrower.setLastName("Smith");
        borrower.setFirstName("John");

        System.out.println("-------------------------------------");
        System.out.println("Author Information: ");
        System.out.println("Author ID: " + author.authorId);
        System.out.println("Author Last Name: " + author.lastName);
        System.out.println("Author First Name: " + author.firstName);
        System.out.println("-------------------------------------");
        System.out.println("Book Information: ");
        System.out.println("Book ID: " + book.bookId);
        System.out.println("Book Title: " + book.title);
        System.out.println("Book ISBN: " + book.ISBN);
        System.out.println("Book Category: " + book.category);
        System.out.println("-------------------------------------");
        System.out.println("Borrower Information: ");
        System.out.println("Borrower ID: " + borrower.getBorrowerId());
        System.out.println("Borrower Last Name: " + borrower.getLastName());
        System.out.println("Borrower First Name: " + borrower.getFirstName());
        System.out.println("-------------------------------------");
    }
}
