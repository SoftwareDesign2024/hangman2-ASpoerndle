package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;
public class ExecutionerCheat extends Executioner {
	
	public ExecutionerCheat() {
		
	}
	public ExecutionerCheat(HangmanDictionary dictionary, int wordLength) {
		super(dictionary, wordLength);
		myRemainingWords = dictionary.getWords(wordLength);
		
		
	}
	private String secretWord;
	private List<String> myRemainingWords;
	
	
	 
	    // Returns a secret word.
	    public void setDictionary(HangmanDictionary dictionary) {
	    	this.dictionary = dictionary;
	    	
	    }
	    
	    
	    public String getSecretWord() {
	    	return secretWord;
	    }
	    public void cheat(char guess, DisplayWord myDisplayWord) {
	        // create template of guesses and find one with most matching remaining words
	        HashMap<DisplayWord, List<String>> templatedWords = new HashMap<DisplayWord, List<String>>();
	        for (String w : myRemainingWords) {
	            DisplayWord template = new DisplayWord(myDisplayWord);
	            template.update(guess, w);
	            if (!templatedWords.containsKey(template)) {
	                templatedWords.put(template, new ArrayList<>());
	            }
	            templatedWords.get(template).add(w);
	        }
	        int max = 0;
	        DisplayWord maxKey = new DisplayWord("");
	        for (Entry<DisplayWord, List<String>> entry : templatedWords.entrySet()) {
	            //System.out.println(entry.getValue());
	            if (entry.getValue().size() > max) {
	                max = entry.getValue().size();
	                maxKey = entry.getKey();
	            }
	        }

	        // update secret word to match template of guesses
	        myRemainingWords = templatedWords.get(maxKey);
	        Collections.shuffle(myRemainingWords);
	        secretWord = myRemainingWords.get(0);
	        myDisplayWord = maxKey;
	    }
	    
	   // @Override
	    public boolean checkGuessInSecret (char guess, DisplayWord myDisplayWord) {
	    	cheat(guess, myDisplayWord);
			if (secretWord.indexOf(guess) >= 0) {
				myDisplayWord.update(guess, secretWord);
				return true;
			}
			return false;
		}
	    public void makeSecretWord(HangmanDictionary dictionary, int wordLength) {
	    	myRemainingWords = dictionary.getWords(wordLength);
	    	secretWord = dictionary.getRandomWord(wordLength).toLowerCase();
	    }


	

}
