import game.HangmanGame;
import game.HangmanGameAutoGuesser;
import game.HangmanGameCheatingComputer;
import util.HangmanDictionary;
import game.Executioner;
import game.ExecutionerCheat;
import game.ExecutionerHuman;
import game.Guesser;
import game.GuesserAuto;
import game.Guesser;

/**
 * This class launches the Hangman game and plays once.
 * 
 * @author Michael Hewner
 * @author Mac Mason
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class Main {
    public static final String DICTIONARY = "data/lowerwords.txt";
    public static final int NUM_LETTERS = 6;
    public static final int NUM_MISSES = 8;


    public static void main (String[] args) {
    	
    	/*
    	 * Computer that cheats the secret word = exeAuto
    	 * Computer that keeps one secret word = exeComp
    	 * You play as the person with secret word = exeHuman
    	 * Auto guesser = guesserAuto
    	 * Computer guesser = guesserComp
    	 * You play as a guesser = guesser
    	 */
    	Executioner execute = new Executioner();
    	GuesserAuto guess = new GuesserAuto();
    	
        new HangmanGame(new HangmanDictionary(DICTIONARY), NUM_LETTERS, NUM_MISSES, guess, execute).play();
        //new HangmanGameAutoGuesser(new HangmanDictionary(DICTIONARY), NUM_LETTERS, NUM_MISSES).play();
    	//new HangmanGameCheatingComputer(new HangmanDictionary(DICTIONARY), NUM_LETTERS, NUM_MISSES).play();
    }
}
