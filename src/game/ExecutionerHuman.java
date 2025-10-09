package game;
import util.ConsoleReader;
import util.HangmanDictionary;

public class ExecutionerHuman extends Executioner {
	
	public ExecutionerHuman(HangmanDictionary dictionary, int wordLength) {
		super(dictionary,wordLength);
	}
	
	public void setSecretWord(String secretWord) {
		this.secretWord = secretWord;
	}
	
	
	
	public String generateSecretWord () {
        String result = ConsoleReader.promptString("Choose a secret word that is " + wordLength + " letters long: ");
        while (! dictionary.contains(result, wordLength)) {
            result = ConsoleReader.promptString("That word is not recognized, please choose another: ");
        }
        setSecretWord(result);
        return result;
    }
	
	

}
