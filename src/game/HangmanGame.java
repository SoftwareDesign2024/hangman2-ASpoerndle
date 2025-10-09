package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 * @author Shannon Duvall
 * Edits by: Aidan Spoernde
 */
public class HangmanGame {
	
	
	// how many guesses are remaining
	private int myNumGuessesLeft;
	// what is shown to the user
	private DisplayWord myDisplayWord;
	// tracks letters guessed
	private Guesser guesser;
	private Executioner executioner;
	

	/**
	 * Create Hangman game with the given dictionary of words to play a game with words 
	 * of the given length and giving the user the given number of chances.
	 */
	public HangmanGame (HangmanDictionary dictionary, int wordLength, int numGuesses, Guesser guesser, Executioner executioner) {
		
		this.executioner = executioner;
		executioner.setDictionary(dictionary);
		executioner.setWordLength(wordLength);
		myNumGuessesLeft = numGuesses;
		this.guesser = guesser;
		executioner.makeSecretWord(dictionary, wordLength);
		myDisplayWord = new DisplayWord(this.executioner.getSecretWord());
	}

	/**
     * Play one complete game.
     */
	public void play () {
		
		boolean gameOver = false;
		while (!gameOver) {
			printStatus(this.guesser);
			String guess = ConsoleReader.promptString("Make a guess: ");
			// Make sure the guess is a single letter in the alphabet
			while (!(guess.length() == 1 && Character.isAlphabetic(guess.charAt(0)))) {
				System.out.println("Please enter a single letter ...");
				guess = ConsoleReader.promptString("Make a guess: ");
			}
			char guessedLetter = guess.toLowerCase().charAt(0);
			
			this.guesser.setGuess(guessedLetter);
			updateBasedOnGuess(this.guesser, this.executioner, this.myDisplayWord);
			if (isGameLost()) {
				System.out.println("YOU ARE HUNG!!!");
				gameOver = true;
			}
			else if (isGameWon()) {
				System.out.println("YOU WIN!!!");
				gameOver = true;
			}
		}
		System.out.println("The secret word was " + this.executioner.getSecretWord());
	}


	// Process a guess by updating the necessary internal state, particularly
	// the letters to choose from (myLettersLeftToGuess) and the number of guesses
	// left
	private void updateBasedOnGuess (Guesser guesser, Executioner execute, DisplayWord displayWord) {
		// do not count repeated guess as a miss
		char guess = guesser.getGuess();
		int index = guesser.getMyLettersLeftToGuess().indexOf("" + guess);
		if (index >= 0) {
			guesser.recordGuess(index);
			if (! execute.checkGuessInSecret(guesser.getGuess(), displayWord)) {
				myNumGuessesLeft -= 1;
			}
		}
	}

	

	
	

	// Returns true only if the guesser has guessed all letters in the secret word.
	private boolean isGameWon () {
		return myDisplayWord.equals(this.executioner.getSecretWord());
	}

	// Returns true only if the guesser has used up all their chances to guess.
	private boolean isGameLost () {
		return myNumGuessesLeft == 0;
	}

	// Print game stats
	private void printStatus (Guesser guesser) {
		System.out.println(myDisplayWord);
		System.out.println("# misses left = " + myNumGuessesLeft);
		System.out.println("letters not yet guessed = " + guesser.getMyLettersLeftToGuess());
		// NOT PUBLIC, but makes it easier to test
		//System.out.println("*** " + mySecretWord);
		System.out.println();
	}
}
