package module3.exercise3_2methodsAndWithoutParameters;

public class Borrower {
    private int borrowerId;
    private String lastName;
    private String firstName;

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
