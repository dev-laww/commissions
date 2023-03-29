
import java.util.*;

public class LabEx3F {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an integer (1|2): ");
        int t = validateT();
        switch (t) {
            case 1:
                System.out.print("Enter the number syllable of the song: ");
                int s = in.nextInt();
                System.out.print("Enter the number of children that will play: ");
                int n = validateN();
                char ch;

                //Queue<String> q = new Queue<>(n);
                QueueF<String> q = new QueueF<>(n);
                for (int i = 0; i < n; i++) {
                    ch = (char) ('A' + i);
                    q.enqueue(Character.toString(ch));
                }

                System.out.print("Children: ");
                for (int i = 0; i < n; i++) {
                    System.out.print(q.peek() + " ");
                    q.enqueue(q.dequeue());

                }

                System.out.println();
                StackF<String> str = new StackF<>();

                int round = 1;

                while (q.peek() != null) {
                    System.out.print("Round " + round + " : ");
                    for (int i = 0; i < s - 1; i++) {
                        q.enqueue(q.dequeue());
                    }
                    str.push(q.dequeue());
                    if (q.peek() == null) {
                        System.out.println("Empty!");
                    } else {
                        for (int i = 0; i < q.size(); i++) {
                            System.out.print(q.peek() + " ");
                            q.enqueue(q.dequeue());
                        }
                    }
                    System.out.println();
                    round++;
                }

                System.out.print("Winning Order: ");

                while (!str.isEmpty()) {
                    System.out.print(str.pop() + " ");
                }

                System.out.println();

                break;
            case 2:
                System.out.print("Enter a list of integers: ");
                String string = in.nextLine();
                String[] a = string.split(" ");

                SLL<Integer> list = new SLL<>();
                System.out.println("Linked List Contents:");

                for (String w : a) {
                    Integer info = Integer.valueOf(w);
                    list.insertAsc(info);
                    list.printAll();
                }
                break;
            default:
                System.out.println("Invalid Input!");
                break;
        }
    }

    public static int validateT() {
        Scanner in = new Scanner(System.in);

        int valid;
        do {
            valid = in.nextInt();
            if (valid != 1 && valid != 2) {
                System.out.println("Invalid Input! Please enter 1 or 2.");
                System.out.print("Please enter an integer (1|2): ");
            }
        } while (valid != 1 && valid != 2);

        return valid;
    }

    public static int validateN() {
        Scanner in = new Scanner(System.in);
        int valid;

        do {
            valid = in.nextInt();
            if (valid < 1 || valid > 26) {
                System.out.println("Invalid Input! Please enter a number between 1 and 26.");
                System.out.print("Please enter the number of children that will play: ");
            }
        } while (valid < 1 || valid > 26);

        return valid;
    }

}