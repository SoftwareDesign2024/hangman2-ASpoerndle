package game;

import util.ConsoleReader;
import util.DisplayWord;

/*
 * @Author: Aidan Spoerndle
 */

public class Guesser {
	protected char guess;
	protected StringBuilder myLettersLeftToGuess;
	protected static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	//protected int myNumGuessesLeft =8;

	//Constructor
	public Guesser() {
		myLettersLeftToGuess = new StringBuilder(ALPHABET);
	}
	//Getters
	public char getGuess() {
		return guess;
		
	}
	public StringBuilder getMyLettersLeftToGuess() {
		return myLettersLeftToGuess;
	}
	//Setters
	public void setGuess(char recGuess) {
		guess = recGuess;
				
	}
//	public void setNumGuessesLeft(int numGuessesLeft) {
//		myNumGuessesLeft = numGuessesLeft; 
//	}
//	public int getNumGuessLeft() {
//		return myNumGuessesLeft;
//	}
//	
	
	// Record that a specific letter was guessed

	public void recordGuess (int index) {
		myLettersLeftToGuess.deleteCharAt(index);
	}
	 // Returns true only if the guesser has used up all their chances to guess.
//    public boolean isGameLost () {
//        return myNumGuessesLeft == 0;
//    }
// // Process a guess by updating the necessary internal state.
//    public void makeGuess (Executioner exe,DisplayWord displayWord) {
//        // do not count repeated guess as a miss
//        int index = myLettersLeftToGuess.indexOf("" + getGuess());
//        if (index >= 0) {
//            recordGuess(index);
//            if (! exe.checkGuessInSecret(getGuess(), displayWord)) {
//            	
//                myNumGuessesLeft -= 1;
//            }
//        }
//    }
// 
	
	
}
