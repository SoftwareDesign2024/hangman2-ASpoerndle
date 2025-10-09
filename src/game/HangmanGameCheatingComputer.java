package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 */
public class HangmanGameCheatingComputer {
    //private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    

    // word that is being guessed
    private String mySecretWord;
    // how many guesses are remaining
    private int myNumGuessesLeft;
    // what is shown to the user
    private DisplayWord myDisplayWord;
    // tracks letters guessed
    //private StringBuilder myLettersLeftToGuess; 
    // executioner state
    private List<String> myRemainingWords;
    private ExecutionerCheat exeCheat;
    private Guesser guessHuman;



    /*
     * Create Hangman game with the given dictionary of words to play a game with words 
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameCheatingComputer (HangmanDictionary dictionary, int wordLength, int numGuesses) {
    	exeCheat = new ExecutionerCheat(dictionary, wordLength);
    	mySecretWord = exeCheat.getSecretWord();
    	myNumGuessesLeft = numGuesses;
        //guessHuman.setNumGuessesLeft(numGuesses);
       // myLettersLeftToGuess = new StringBuilder(ALPHABET);
        myDisplayWord = new DisplayWord(mySecretWord);
        
        guessHuman = new Guesser();
       
    }

    /**
     * Play one complete game.
     */
    public void play () {
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();
            
            String guess = ConsoleReader.promptString("Make a guess: ");
            if (guess.length() == 1 && Character.isAlphabetic(guess.charAt(0))) {
            	guessHuman.setGuess(guess.toLowerCase().charAt(0));
                updateBasedOnGuess(guessHuman.getGuess(), myDisplayWord);
                if (isGameLost()) {
                    System.out.println("YOU ARE HUNG!!!");
                    gameOver = true;
                }
                else if (isGameWon()) {
                    System.out.println("YOU WIN!!!");
                    gameOver = true;
                }
            }
            else {
                System.out.println("Please enter a single letter ...");
            }
        }
        System.out.println("The secret word was " + exeCheat.getSecretWord());
    }



      // Returns true only if the guesser has guessed all letters in the secret word.
    private boolean isGameWon () {
        return myDisplayWord.toString().equals(exeCheat.getSecretWord());
    }

    // Returns true only if the guesser has used up all their chances to guess.
    private boolean isGameLost () {
        return myNumGuessesLeft == 0;
    }

    // Print game stats
    private void printStatus () {
        System.out.println(myDisplayWord);
        System.out.println("# misses left = " + myNumGuessesLeft);
        System.out.println("letters not yet guessed = " + guessHuman.getMyLettersLeftToGuess());
        // NOT PUBLIC, but makes it easier to test
        System.out.println("*** " + exeCheat.getSecretWord());
        System.out.println();
    }
    private void updateBasedOnGuess (Character guess,DisplayWord displayWord) {
 		// do not count repeated guess as a miss
 		
 		
 		
 		int index = guessHuman.getMyLettersLeftToGuess().indexOf("" + guess);
 		
 		if (index >= 0) {
 			guessHuman.recordGuess(index);
 			if (! exeCheat.checkGuessInSecret(guess, displayWord)) {
 				myNumGuessesLeft -= 1;
 			}
 		}
 	}
}
