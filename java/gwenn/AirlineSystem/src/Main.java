import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void printSeats(boolean[][] seats, int[] count) {
        System.out.println("************************* AIRLINE RESERVATION SYSTEM************************");
        System.out.println();
        System.out.printf("\t\t\t  Seats Availability: Economy Plus = %d Economy = %d%n", count[0], count[1]);
        System.out.println();
        System.out.println("\t        1  2  3  4  5  6  7  8  9 10 11 18 19 20 21 22 23 24");
        System.out.println("\t--------------------------------------------------------------------");
        for (int i = 0; i < 3; i++) {
            char row = (i == 0) ? 'D' : (i == 1) ? 'C' : 'A';
            System.out.print("\t  " + row + " |   ");
            for (int j = 0; j < 18; j++) {
                System.out.print((seats[i][j] ? "A" : ((i == 0 || i == 1) && (j == 0 || j == 1)) ? "N" : "O") + "  ");
            }
            System.out.println(" | " + row);
        }
        System.out.println("\t--------------------------------------------------------------------");
        System.out.println("\n\tLegend: A = Available O = Occupied N = Not Applicable");
        System.out.println("****************************************************************************");
    }

    public static int[] countSeats(boolean[][] seats) {
        int[] count = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 18; j++) {
                if ((i == 2 && j == 0) || ((i == 0 && j == 2) || (i == 1 && j == 2)) || j == 11) {
                    count[0] += seats[i][j] ? 1 : 0;
                    continue;
                }
                count[1] += seats[i][j] ? 1 : 0;
            }
        }
        return count;
    }

    public static String pickRandomSeat(boolean[][] seats) {
        int row = (int) (Math.random() * 3);
        int col = (int) (Math.random() * 18);
        if (seats[row][col]) {
            seats[row][col] = false;
            return ((row == 0) ? "D" : (row == 1) ? "C" : "A") + (col + 1);
        }
        return pickRandomSeat(seats);
    }

    public static boolean checkSeats(boolean[][] seats) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 18; j++) {
                if (seats[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String pickRandomEconomySeat(boolean[][] seats) {
        int[][] ecoPlus = {{2, 0}, {0, 2}, {1, 2}, {0, 11}, {1, 11}, {2, 11}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 18; j++) {
                for (int[] ints : ecoPlus) {
                    if (!(i == ints[0] && j == ints[1])) continue;

                    if (seats[i][j]) {
                        seats[i][j] = false;
                        return ((i == 0) ? "D" : (i == 1) ? "C" : "A" ) + (j + 1);
                    }
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        boolean[][] seats = new boolean[3][18]; // Generate seats
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 18; j++) {
                if ((i == 0 || i == 1) && (j == 0 || j == 1)) {
                    seats[i][j] = false;
                    continue;
                }
                seats[i][j] = true;
            }
        }

        while (true) {
            int[] count = countSeats(seats);
            printSeats(seats, count);
            System.out.println("\nReservation System Menu");
            System.out.println("[1] Economy Plus");
            System.out.println("[2] Economy");
            System.out.print("\nEnter chosen menu: ");
            int choice = scanner.nextInt();
            String seat = choice == 1 ? "Economy Plus" : "Economy";

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice!");
                continue;
            }

            if (!checkSeats(seats)) {
                System.out.println("All passengers are on-board!");
                break;
            }

            if (count[0] == 0 && choice == 1) {
                System.out.printf("%s seats are FULL!%n", seat.toUpperCase());
                System.out.print("CHANGE to ECONOMY seating [Y]Yes/[N]No: ");
                String change = scanner.next();
                if (change.equalsIgnoreCase("y")) {
                    seat = "Economy";
                } else {
                    System.out.println("Thank you! Next flight leaves in 3 hours.");
                    continue;
                }
            }

            if (count[1] == 0 && choice == 2) {
                System.out.printf("%s seats are FULL!%n", seat.toUpperCase());
                System.out.print("CHANGE to ECONOMY PLUS seating [Y]Yes/[N]No: ");
                String change = scanner.next();
                if (change.equalsIgnoreCase("y")) {
                    seat = "Economy Plus";
                } else {
                    System.out.println("Thank you! Next flight leaves in 3 hours.");
                    continue;
                }
            }

            System.out.println("*************************ENTER PASSENGER DETAILS****************************");
            System.out.printf("Seat Type: %s%n%n", seat.toUpperCase());
            System.out.print("Enter Booking Number        : ");
            String bookingNumber = scanner.next();
            System.out.print("Enter Passenger's Last Name : ");
            String lastName = scanner.next();
            System.out.print("Enter Passenger's First Name: ");
            String firstName = scanner.next();
            System.out.println("****************************************************************************\n");
            System.out.println();
            System.out.println(seat + " Seat Assignment");
            System.out.println("[1] Random");
            System.out.println("[2] Manual");
            System.out.print("\nType chosen menu: ");
            choice = scanner.nextInt();
            String seatNumber = "";
            switch (choice) {
                case 1:
                    seatNumber = seat.equalsIgnoreCase("economy") ? pickRandomSeat(seats) : pickRandomEconomySeat(seats);
                    break;
                case 2:
                    System.out.println("----------- Manual Pick-------------");
                    System.out.print("Enter Row [A, C, D]: ");
                    char row = scanner.next().toUpperCase().charAt(0);
                    int rowNumber = (row == 'A') ? 2 : (row == 'C') ? 1 : 0;
                    System.out.printf("Enter Column %s: ", (seat.equalsIgnoreCase("economy") ? "[2 – 11 and 19-24]" : "[1, 3, 18]"));
                    int col = scanner.nextInt() - 1;
                    if (seats[rowNumber][col]) {
                        seats[rowNumber][col] = false;
                        seatNumber = row + "" + (col + 1);
                        break;
                    }
                    System.out.println("Seat is already taken!");
                    break;
                default:
                    continue;
            }
            System.out.println();
            System.out.println("--------------------------------BOARDING PASS-------------------------------\n");
            System.out.printf("SEAT TYPE: %s%n", seat.toUpperCase());
            System.out.printf("SEAT NUMBER: %s%n%n", seatNumber);
            System.out.printf("Booking Number        : %s%n", bookingNumber);
            System.out.printf("Passenger's Last Name : %s%n", lastName);
            System.out.printf("Passenger's First Name: %s%n", firstName);
            System.out.println("----------------------------------------------------------------------------");

        }
        System.out.println("Thank you! Next flight leaves in 3 hours.");
    }
}