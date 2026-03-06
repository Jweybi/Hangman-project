import java.util.Random;
import java.util.Scanner;

/*Dito na yung game logi mga tol */
public class GameLogic {
    
    private String[] words = {"NetBeans", "Java", "Inheritance", "Subclass", "MainClass"};
    private String secretWord;
    private char[] guessedWord;
    private int attempts = 6;
    
    private Scanner scanner = new Scanner(System.in);

    public GameLogic() {

         Random random = new Random();
         secretWord = words[random.nextInt(words.length)];

         guessedWord = new char[secretWord.length()];

         for (int i = 0; i< guessedWord.length; i++){
             guessedWord[i] = '_';
         }
    }

    public void startGame() {

        System.out.println("=== WELCOME TO THE HANGMAN GAME===");
    }
}
