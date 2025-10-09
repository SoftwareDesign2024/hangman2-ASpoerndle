package game;

import util.DisplayWord;
import util.HangmanDictionary;
/*
 * @Author: Aidan Spoerndle
 */
public class Executioner {
	//Constructor
	
	public Executioner() {
		
	}
	public Executioner(HangmanDictionary dictionary, int wordLength) {
		makeSecretWord(dictionary, wordLength);
		this.dictionary = dictionary;
		this.wordLength = wordLength;
	}
	
	protected String secretWord;
	protected HangmanDictionary dictionary;
	protected int wordLength;
	//Setters
	public void setDictionary(HangmanDictionary dictionary) {
		this.dictionary = dictionary; 
	}
	public void setWordLength(int wordLength) {
		this.wordLength = wordLength;
	}
	
	
	    // Returns true only if given guess is in the secret word.
		// As a byproduct, it updates the word to display based on the guess.
		public boolean checkGuessInSecret (char guess, DisplayWord myDisplayWord) {
			if (secretWord.indexOf(guess) >= 0) {
				myDisplayWord.update(guess, secretWord);
				
				return true;
			}
			
			return false;
		}
		
		// Returns a secret word - a random word from the dictionary with the given length.
		public void makeSecretWord (HangmanDictionary dictionary, int wordLength) {
			secretWord = dictionary.getRandomWord(wordLength).toLowerCase();	
		}
		public String getSecretWord() {
			return secretWord;
		}
}
