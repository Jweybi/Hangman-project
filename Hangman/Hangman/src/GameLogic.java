import java.util.Random;
import java.util.Scanner;

//Game logic ng game
public class GameLogic {
    
    //possible of chosen words:
    private String[] words = {"netbeans", "java", "inheritance", "subclass", "mainclass"};

    //Ito yung napili ng random word which is yung secret word
    private String secretWord;
    //array para sa pag display ng letter
    private char[] guessedWord;
    //Number ng kung ilang attempts allowed(kahit ilan yan btw)
    private int attempts = 6;
    
    private Scanner scanner = new Scanner(System.in);

    //Constructor ito yung nag seselect ng random word
    public GameLogic() {
        Random random = new Random(); 
        secretWord = words[random.nextInt(words.length)];
        guessedWord = new char[secretWord.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
    }

    //Ito yung nag cocontrol ng main game loop
    public void startGame() {
        System.out.println("=== WELCOME TO THE HANGMAN GAME ===");
        while (attempts > 0) {

            displayWord();
            drawHangman();

            System.out.println("Attempts left: " + attempts);
            System.out.print("Enter a letter: ");
            
            try {
                String input = scanner.nextLine();
                //Exception handling yung pag empty letter ang input
                if(input.trim().isEmpty()) {
                  throw new Exception("Input cannot be empty!!");//Ito yung lalabas pag empty ang input ng user
                }
                char guess = input.charAt(0);

                //Exception handling pag hindi letter nilagay eg: Number
                if(!Character.isLetter(guess)) {
                    throw new Exception("Please enter a letter only.");
                }  
                processGuess(Character.toLowerCase(guess));

                if (isWordGuessed()) {
                    System.out.println("Congratulations!! You guessed the word: " + secretWord);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }

        //Game over condition
        drawHangman();
        System.out.println("GAME OVER! The word was: " + secretWord);
    }

    //Method na mag didisplay ng guessed word
    private void displayWord() {
        System.out.println("Word: ");
        for (char c : guessedWord) {
            System.out.println(c + " ");
        }
        System.out.println();
    }

    //Method na nagchecheck if yung letter na na-guess ay nasa word
    private void processGuess(char guess) {
         boolean correct = false;

         for (int i = 0; i < secretWord.length(); i++) {

            if (secretWord.charAt(i) == guess) {
                guessedWord[i] = guess;
                correct = true;
            }
         }

         //Pag mali naman yung guess
         if (!correct) {
            attempts--;
            System.out.println("You guessed the wrong word!");
         }
    }
    
    //Method para sa hangman stickman
    private void drawHangman() {

        System.out.println("  +----+");
        System.out.println("  |    |");

        switch (attempts) {
            
            case 6:
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("       |");
                break;

            case 5:
                System.out.println("  O    |");
                System.out.println("       |");
                System.out.println("       |");
                break;
            case 4:
                System.out.println("  O    |");
                System.out.println("  |    |");
                System.out.println("       |");
                break;
            case 3:
                System.out.println("  O    |");
                System.out.println(" /|    |");
                System.out.println("       |");
                break;
            case 2:
                System.out.println("  O    |");
                System.out.println(" /|\\   |");
                System.out.println("       |");
                return;
            case 1:
                System.out.println("  O    |");
                System.out.println(" /|\\   |");
                System.out.println(" /     |");
                return;
            case 0:
                System.out.println("  O    |");
                System.out.println(" /|\\   |");
                System.out.println(" / \\   |");
                break;
        }
        System.out.println("       |");
        System.out.println("========="); 
    }

    //Ito yung method na mag checheck if nahulaan na yung buong word bale result or outcome
    private boolean isWordGuessed() {

        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
} 







