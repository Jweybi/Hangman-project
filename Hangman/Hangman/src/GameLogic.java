import java.util.Random;
import java.util.Scanner;


public class GameLogic {
    
 
private String[] foods = {"icecream", "fries", "hamburger", "spaghetti", "carbonara"};
private String[] programming = {"java", "netbeans", "inheritance", "interface", "debugging"};
private String[] animals = {"giraffe", "fish", "dog", "zebra", "capybara"};
    
    private String secretWord;
    
    private char[] guessedWord;
    
    private int attempts = 6;
    
    private Scanner scanner = new Scanner(System.in);


    public GameLogic() {
    {
        
        }
    }

    private void chooseCategory() {

        System.out.println("Choose a Category:");
        System.out.println("1. Foods");
        System.out.println("2. Programming");
        System.out.println("3. Animals");
        System.out.println("Enter category: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Random random = new Random();

        switch(choice) {
            case 1:
                secretWord = foods[random.nextInt(foods.length)];
                break;

            case 2:
                secretWord = programming[random.nextInt(programming.length)];
                break;

            case 3:
                secretWord = animals[random.nextInt(animals.length)];
                break;

            default:
                System.out.println("Invalid choice.Defaulting to Foods.");
                secretWord = foods[random.nextInt(foods.length)];
        }
            guessedWord = new char[secretWord.length()];

            for (int i = 0; i < guessedWord.length; i++) {
                guessedWord[i] = '_';
            }
    }

    public void startGame() {
        System.out.println("=== WELCOME TO THE HANGMAN GAME ===");

        chooseCategory();

        while (attempts > 0) {

            displayWord();
            drawHangman();

            System.out.println("Attempts left: " + attempts);
            System.out.print("Enter a letter: ");
            
            try {
                String input = scanner.nextLine();
               
                if(input.trim().isEmpty()) {
                  throw new Exception("Input cannot be empty!!");
                }
                char guess = input.charAt(0);

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

  
        drawHangman();
        System.out.println("GAME OVER! The word was: " + secretWord);
    }

    
        private void displayWord() {
        System.out.print("Word: ");
        for (char c : guessedWord) {
        System.out.print(c + " ");
      }
         System.out.println();
    }
    
    private void processGuess(char guess) {
         boolean correct = false;

         for (int i = 0; i < secretWord.length(); i++) {

            if (secretWord.charAt(i) == guess) {
                guessedWord[i] = guess;
                correct = true;
            }
         }

         
         if (!correct) {
            attempts--;
            System.out.println("You guessed the wrong word!");
         }
    }
    
    
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

    
    private boolean isWordGuessed() {

        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
} 








