package document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Doc {

	private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	public Doc(String text){
		this.text = text;
    }
    
    /**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z.  This method completely 
	 * ignores numbers when you count words, and assumes that the document does not have 
	 * any strings that combine numbers and letters. 
	 * 
	 * Check the examples in the main method below for more information.
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of words in the document.
	 */
    public int calcNumWords(String text) {
        String[] split = text.split("[^a-zA-z]+");
        System.out.println(Arrays.toString(split));
        return split.length;
	}
	
	public String[] splitNumWords(String text) {
        String[] split = text.split("[^a-zA-z]+");
        System.out.println(Arrays.toString(split));
        return split;
    }

    /**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of sentences in the document.
	*/
    public int calcNumSentences(String text) {
        String[] split = text.split("[!?.]+");
        System.out.println(Arrays.toString(split));
        return split.length;
    }

	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences 
	 * method here.  The reason we put countSyllables here because we'll 
	 * use it again next week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern 
	 * objects inside this method. Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	public int calcSyllables(String word) {
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument (module 2) and 
		// EfficientDocument (module 3).
		String[] split = word.split("(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\b)");
		/*
		String[] words = splitNumWords(getText());
		//System.out.println(Arrays.toString(words));
		System.out.println(Arrays.toString(split));
		int syllables = 0;
		for (int i = 0; i < split.length - 1; i++) {
			System.out.println(calcSyllablesPerWord(words[i]));
			/*
			if (split[i].toLowerCase().equals("a") || split[i].toLowerCase().equals("e") || split[i].toLowerCase().equals("i") || split[i].toLowerCase().equals("o") || split[i].toLowerCase().equals("u") || split[i].toLowerCase().equals("y")){
				if (i < split.length - 1 && split[i + 1].equals(" ")){
					continue;
				} else {
					syllables++;
				}
			}
		}*/
	    return split.length;
	}

	public int calcSyllablesPerWord(String word) {
		int syllables = 0;
		for (int i = 0; i < word.length(); i++) {
			char character = Character.toLowerCase(word.charAt(i));
			System.out.print(character + " , "); 
			if (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u' || character == 'y') {
				if (character == 'e' && i < word.length() - 1 && word.charAt(i + 1) == ' ' && syllables > 0) {
					continue;
				} else {
					char inFront = Character.toLowerCase(word.charAt(i+1));
					if (i < word.length() - 1 && (inFront == 'a' || inFront == 'e'  || inFront == 'i' || inFront == 'o' || inFront == 'u' || inFront == 'y' )){
						continue;
					} else {
						syllables++;
					}
				}
			} 
		}
		return syllables;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** return the Flesch readability score of this document */
	public double getFleschScore() {
	    // TODO: You will play with this method in week 1, and 
		// then implement it in week 2
	    return 0.0;
	}
}
