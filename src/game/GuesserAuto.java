package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.DisplayWord;

public class GuesserAuto extends Guesser {
	private static final String LETTERS_ORDERED_BY_FREQUENCY = "etaoinshrldcumfpgwybvkxjqz";
	private String myLetters;
	private int myIndex = 0;
	public GuesserAuto() {
		super();
		myLetters = LETTERS_ORDERED_BY_FREQUENCY;
	}
	
	public char getGuess() {
		
		return myLetters.charAt(myIndex++);
	}
	public void setGuess() {
		
	}
	
	
	
	
	
	
    
    
}
