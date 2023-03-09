import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String gameResult = wordle();
        System.out.println(gameResult);
    }

    public static String wordle() throws IOException {
        //choosing a word to be guessed from wordbank
        String[] wordBank = {"based", "crook", "kills", "react"};
        int rnd = new Random().nextInt(wordBank.length);
        String password = wordBank[rnd];
        System.out.println(password);
        int counter = 0;
        System.out.println("Green color of the letter means the letter is correct and in correct place.");
        System.out.println("Yellow color of the letter means the letter is correct but in wrong place.");
        System.out.println("Good luck!");
        while (counter < 6) {
            //read the word
            Scanner sc = new Scanner(System.in);
            System.out.println("Guess the 5-letter word!");
            String guess = sc.nextLine();
            StringBuilder correctness = new StringBuilder();

            //check for proper length
            int guessLength = guess.length();
            if (guessLength != 5) {
                System.out.println("Guess must be 5-letter long! Try again");
                counter++;
                continue;
            }

            //colors to indicate answers
            String ANSII_RESET = "\u001B[0m";
            String ANSII_YELLOW = "\u001B[33m";
            String ANSII_GREEN = "\u001B[32m";

            //correct letters in correct places; marked with '*' on both sides of the letter
            int allGood = 0;
            for (int i = 0; i < 5; i++) {
                if (guess.charAt(i) == password.charAt(i)) {
                    correctness.append(ANSII_GREEN).append(guess.charAt(i)).append(ANSII_RESET);
                    allGood++;
                } else if (password.contains(Character.toString(guess.charAt(i)))) {
                    correctness.append(ANSII_YELLOW).append(guess.charAt(i)).append(ANSII_RESET);
                } else {
                    correctness.append(guess.charAt(i));
                }
            }

            System.out.printf("Current state of guessing: %s\n", correctness);
            if (allGood == 5) return "You won!";
        }
        return "Game over! You used all guesses.";
    }
}