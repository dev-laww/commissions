package mpfileiocollectionhealthclub;

public class Person {
    private String fname;
    private String lname;
    private String age;
    private String address;

    public Person(String fname, String lname, String age, String address) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.address = address;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String fname() {
        return fname;
    }

    public String lname() {
        return lname;
    }

    public String age() {
        return age;
    }

    public String address() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", fname, lname, age, address);
    }
}
