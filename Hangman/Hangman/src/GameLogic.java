//Imports random class para makapag pick ng word yung program
import java.util.Random;
//Scanner class para makapag input si user
import java.util.Scanner;

//Main class containing the game logic
public class GameLogic {

//Arrays na nag sstore ng words para sa categories:
private String[] foods = {"icecream", "fries", "hamburger", "spaghetti", "carbonara"};
private String[] programming = {"java", "netbeans", "inheritance", "interface", "debugging", "","sql"};
private String[] animals = {"giraffe", "fish", "dog", "zebra", "capybara"};

   //Ito yung mag sstore sa word na randomly chosen
   private String secretWord;

    //Ito yung array na mag sstore ng letter ng ginuess ni user
    private char[] guessedWord;
    
    //Ito yung number of attempts si user para mahulaan yung word
    private int attempts = 6;
    
    // Scanner object para mabasa yung input ni user
    private Scanner scanner = new Scanner(System.in);

    // Constructer para sa "GameLogic" class
    public GameLogic() {
    {
        //Empty sya pero pwede gamitin kung kailangan
        }
    }

    //Ito yung method na nag aallow pumili si user ng category
    private void chooseCategory() {

        //Ito yung mag didisplay kay user which is yung options
        System.out.println("Choose a Category:");
        System.out.println("1. Foods");
        System.out.println("2. Programming");
        System.out.println("3. Animals");
        System.out.println("Enter category: ");

        //Binabasa yung napili ni user na category
        int choice = scanner.nextInt();
        scanner.nextLine();

        //Nag crecreate ng random object para maka select ng random na word
        Random random = new Random();

        
        switch(choice) { //switch statement para ma deterrmine kung anong category ang napili ni user
            case 1: //Pumipili ng random word sa food array
                secretWord = foods[random.nextInt(foods.length)];
                break;

            case 2://Pumipili ng random word sa programming arrray
                secretWord = programming[random.nextInt(programming.length)];
                break;
                
                
            case 3://Pumipili ng random word dun sa animals array
                secretWord = animals[random.nextInt(animals.length)];
                break;

            default://Pag invalid category matic mag dedefault sa foods
                System.out.println("Invalid choice Foods Category is now chosen.");
                secretWord = foods[random.nextInt(foods.length)];
        }
            guessedWord = new char[secretWord.length()]; //Nag crecreate ng array na same length dun sa secret word

            //Ito yung nag fifill in ng underscore dun sa guessedword array
            for (int i = 0; i < guessedWord.length; i++) {
                guessedWord[i] = '_';
            }
    }

    //Method pag nag start ang game
    public void startGame() {
        System.out.println("=== WELCOME TO THE HANGMAN GAME ===");

        chooseCategory();//Ito yung nagcacall dun sa method sa taas para maka pili ng category or word

        while (attempts > 0) {//ito yung game loop na nag rurun habang may attempt pang natitira si user

            displayWord();//Dinidisplay yung current guessed word
            drawHangman();//Ito yung nag dradraw dun sa hangman depende sa dami ng attempts na natitira ng user

            //Pinapakita sa user kung ilan nalang meron syang attempts
            System.out.println("Attempts left: " + attempts);
            System.out.print("Enter a letter: ");
            
            //Exception Handling
            try {
                String input = scanner.nextLine();//Ito yung nagbabasa dun sa user input
               
                if(input.trim().isEmpty()) {//Ito yung nag checheck at nag rurun pag empty ang input ni user
                  throw new Exception("Input cannot be empty!!");
                }

                char guess = input.charAt(0);

                if(!Character.isLetter(guess)) {//Chinecheck netoh if letter ba ang input ni user
                    throw new Exception("Please enter a letter only.");
                }  
                processGuess(Character.toLowerCase(guess));//prinoprocess yung guessed letter if capital gagawing lower

                //Chinecheck if na guess na ba yung word    
                if (isWordGuessed()) {
                    System.out.println("Congratulations!! You guessed the word: " + secretWord);
                    return;
                }
            } catch (Exception e) {//Ito yung mag hahandle ng mga invalid input errors
                System.out.println("Invalid input: " + e.getMessage());
            }
        }

        //Ito yung mag rurun kapag naubos na yung input or attempts ni user
        drawHangman();
        System.out.println("GAME OVER! The word was: " + secretWord);
    }

    //Ito yung method para ipakita yung progress ng word
    //Halimbawa: _ _ a _ _ a
    private void displayWord() {
    System.out.print("Word: ");

    //Enhance for loop para i print bawat character sa guessedWord array
     for (char c : guessedWord) {
    System.out.print(c + " ");
      }

         System.out.println();//Mag new line pagkatapos i display yung word
    }
    
    //Method na nag pprocess kung tama ba o mali yung guess ni user
    private void processGuess(char guess) {

        //Boolean variable para malaman kung may natamaan bang letter
         boolean correct = false;

         //Loop para i check bawat letter ng secret word
         for (int i = 0; i < secretWord.length(); i++) {

            //Ilalagay yung tamang letter sa guessedWord array
            if (secretWord.charAt(i) == guess) {
                guessedWord[i] = guess;

                //Pag dito binato ibig sabihin tama yung guess
                correct = true;
            }
         }

         //Kung walang natamaan na letter
         if (!correct) {
            attempts--;//Mangyayari jan babawasan ang attempts
            System.out.println("You guessed the wrong letter!");//Magpapakita pag maling letter
         }
    }
    
    //Method na mag dradraw sa hangman bawat maling guess ni user at depende sa attempts na natitira
    private void drawHangman() {

        System.out.println("  +----+");
        System.out.println("  |    |");

        //Switch statements para mag display ng iba't ibang parts ng hangman
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

    //Method para i check kung lahat ng letters ay nahulaan na
    private boolean isWordGuessed() {

        for (char c : guessedWord) {//Loop para i check kung may underscore pa

            if (c == '_') {//Kapag may underscore pa ibig sabihin may letters pang hindi nahuhulaan
                return false;
            }
        }
        //Kapag wala nang underscore ibig sabihin nahulaan na ang buong word
        return true;
    }
} 











