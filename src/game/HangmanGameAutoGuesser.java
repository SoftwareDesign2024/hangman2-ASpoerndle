package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * where the computer guesses letters based on a predictable pattern.
 *
 * @author Robert C. Duvall
 */
public class HangmanGameAutoGuesser {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String LETTERS_ORDERED_BY_FREQUENCY = "etaoinshrldcumfpgwybvkxjqz";

    // word that is being guessed
    //private String mySecretWord;
    // how many guesses are remaining
    private int myNumGuessesLeft;
    // what is shown to the user
    private DisplayWord myDisplayWord;
    // tracks letters guessed
    //private StringBuilder myLettersLeftToGuess;
    // guesser state
    private String myLetters;
    
    private GuesserAuto guessAuto;
    private ExecutionerHuman exeHuman;

    /**
     * Create Hangman game with the given dictionary of words to play a game with words 
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameAutoGuesser (HangmanDictionary dictionary, int wordLength, int numGuesses) {
    	 guessAuto = new GuesserAuto();
         exeHuman = new ExecutionerHuman(dictionary, wordLength);
         myNumGuessesLeft = numGuesses;
    	//exeHuman.makeSecretWord(dictionary, wordLength);
       // guessAuto.setNumGuessesLeft(numGuesses);
        exeHuman.getSecretWord();
        myDisplayWord = new DisplayWord(exeHuman.getSecretWord());
       // myLetters = LETTERS_ORDERED_BY_FREQUENCY;
        
       
        }

    /**
     * Play one complete game.
     */
    public void play () {
    	
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();

            Character guess = guessAuto.getGuess();
            guessAuto.setGuess(guess);
            updateBasedOnGuess(guess, myDisplayWord);
            if (isGameLost()) {
                System.out.println("YOU ARE HUNG!!!");
                gameOver = true;
            }
            else if (isGameWon()) {
                System.out.println("YOU WIN!!!");
                gameOver = true;
            }
        }
        System.out.println("The secret word was " + exeHuman.getSecretWord());
    }


    

   

    // Returns true only if the guesser has guessed all letters in the secret word.
    private boolean isGameWon () {
        return myDisplayWord.equals(exeHuman.getSecretWord());
    }

   

    // Print game stats
    private void printStatus () {
        System.out.println(myDisplayWord);
        System.out.println("# misses left = " + myNumGuessesLeft);
        System.out.println("letters not yet guessed = " + guessAuto.getMyLettersLeftToGuess());
        // NOT PUBLIC, but makes it easier to test
        System.out.println("*** " + exeHuman.getSecretWord());
        System.out.println();
    }
 // Process a guess by updating the necessary internal state, particularly
 	// the letters to choose from (myLettersLeftToGuess) and the number of guesses
 	// left
 	private void updateBasedOnGuess (Character guess,DisplayWord displayWord) {
 		// do not count repeated guess as a miss
 		
 		
 		
 		int index = guessAuto.getMyLettersLeftToGuess().indexOf("" + guess);
 		
 		if (index >= 0) {
 			guessAuto.recordGuess(index);
 			if (! exeHuman.checkGuessInSecret(guess, displayWord)) {
 				myNumGuessesLeft -= 1;
 			}
 		}
 	}
 // Returns true only if the guesser has used up all their chances to guess.
 	private boolean isGameLost () {
 		return myNumGuessesLeft == 0;
 	}

}
