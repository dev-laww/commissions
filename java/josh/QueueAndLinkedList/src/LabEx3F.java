
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
        }


    }

    public static int validateT() {
        Scanner in = new Scanner(System.in);
        int valid;
        for (valid = in.nextInt(); valid < 1 || valid > 2; valid = in.nextInt()) {
            System.out.println("Invalid Input!");
            System.out.print("Please enter an integer t: ");
        }
        return valid;
    }

    public static int validateN() {
        Scanner in = new Scanner(System.in);
        int valid;
        for (valid = in.nextInt(); valid < 1 || valid > 26; valid = in.nextInt()) {
            System.out.println("Invalid Input! Please enter a number from 1 to 26.");
            System.out.print("Please enter an integer n: ");
        }
        return valid;
    }

}