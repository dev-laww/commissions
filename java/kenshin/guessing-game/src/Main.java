import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi word master!");
        System.out.print("Enter word to guess: ");
        String word = scanner.nextLine();
        System.out.print("Enter how clues to provide: ");
        int n = scanner.nextInt();
        String[] clues_array = new String[n];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter clue #%d: ", i + 1);
            clues_array[i] = scanner.nextLine();
        }

        System.out.println("-------------------------");
        System.out.println("GUESSING STARTS NOW!");
        System.out.println("-------------------------");
        int tries = 3;

        System.out.println("Here are your clues!");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d.) %s\n", i + 1, clues_array[i]);
        }
        System.out.println();

        while (tries > 0) {
            System.out.printf("You have %d tries left!\n", tries);
            System.out.print("Enter your answer: ");
            String guess = scanner.next();
            if (guess.equals(word)) {
                System.out.printf("CORRECT! The word being asked is %s!\n", word);
                System.out.println("YOU WON THE GAME!");
                break;
            } else {
                System.out.println("WRONG!");
                tries--;
                if (tries == 0) {
                    System.out.printf("You have %d tries left!\n", tries);
                    System.out.println("GAME OVER!");
                    System.out.println("Would you like to play again? (y/n)");
                    String answer = scanner.next();
                    if (answer.equals("y")) {
                        tries = 3;
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
