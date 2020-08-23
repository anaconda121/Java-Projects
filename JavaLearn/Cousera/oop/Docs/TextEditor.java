import document.BasicDocument;
import document.Doc;

public class TextEditor {
    public static void main(String[] args) {
        /* Each of the test cases below uses the method testCase.  The first 
        * argument to testCase is a Document object, created with the string shown.
        * The next three arguments are the number of syllables, words and sentences 
        * in the string, respectively.  You can use these examples to help clarify 
        * your understanding of how to count syllables, words, and sentences.
        */
        testCase(new BasicDocument("This is a test.  How many???  Senteeeeeeeeeences are here... there should be 5! Right?"), 16, 13, 5);
  
    }

    public static void testCase(Doc doc, int syllables, int words, int sentences) {
        System.out.println("text: " + doc.getText());
        int w = doc.calcNumWords(doc.getText());
		int s = doc.calcNumSentences(doc.getText());
		int sy = doc.calcSyllables(doc.getText());
        System.out.println("\nnumber of words: " + w);
		System.out.println("number of sentences: " + s);
		System.out.println("sylablles: " + sy);
        /*
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();

		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed; */
	}
}