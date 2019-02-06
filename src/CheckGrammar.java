import edu.stanford.nlp.tagger.maxent.MaxentTagger;

// First grammar rule is the proper usage of din, rin, daw, raw, daan, raan

public class CheckGrammar {

	/*
	 * String input
	 */
	private String strInput;
	
	/*
	 * Tokenized string input
	 */
	private String[] word;
	
	/*
	 * natag na sa POS
	 */
	private String taggedInput;
	
	/*
	 * lahat ng tags andito pakyu ka, magbasa ka ng comments
	 */
	private String[] tags;
	
	/*
	 * Constructor
	 * Initialize string input
	 */
	public CheckGrammar(String strInput) {
		this.strInput = strInput; // initialize input
		MaxentTagger tagger = new MaxentTagger("filipino-left5words-owlqn2-distsim-pref6-inf2.tagger"); // init POS tagger
		taggedInput = tagger.tagString(strInput); // get the sentence with tag input
		System.out.println(taggedInput);
		tokenizeInput();
	}
	
	/*
	 * Tokenize string input (words)
	 * to be used later
	 */
	private void tokenizeInput() {
		int even = 0, odd = 0;
		String[] strTemp = new Tokenization(taggedInput, " |\\|").getTokens(); // split using | and space delimiter
		word = new String[strTemp.length/2];
		tags = new String[strTemp.length/2];
		
		// delimiter for the tags are |, if tokenized, the index of tags will be
		// odd, so if index is odd, go to tags, if even, go to word (or words)
		
		for(int intCounter = 0; intCounter < strTemp.length; intCounter++) { 
			if((intCounter%2) != 0) { // odd
				tags[odd] = strTemp[intCounter]; // tags
				odd++;
			} else { //even
				word[even] = strTemp[intCounter]; // word
				even++;
			}
		}
		Process();
	}
	
	/*
	 * temp function to print the contents of array string
	 */
	/*
	private void printThemAll(String[] temp, String alias) {
		for(int intCounter = 0; intCounter < temp.length; intCounter++) {
			System.out.println(alias + " " + intCounter + ": " + temp[intCounter]);
		}
	}*/
	
	/*
	 * Process each word to check for grammar rules
	 */
	private void Process() {
		// test each word
		for(int intCounter = 0; intCounter < word.length; intCounter++) {
			word[intCounter] = grammarRules(word[intCounter], intCounter);
		}
		
		secondGrammarRule();
	}
	
	private void secondGrammarRule() {
		int index = 0;
		
		for(int counter = 0; counter < tags.length-1; counter++) {

			if(tags[counter].equals("JJD")) {

				if((tags[counter+1].equals("NN")) || (tags[counter+1].equals("NNC")) || (tags[counter+1].equals("NNP")) || (tags[counter+1].equals("NN"))){

					char[] wordLetters = word[counter].toCharArray(); // get the letters of the word
					char lastLetter = wordLetters[wordLetters.length-1]; // get the first letter of the word for checking later

					if(lastLetter == 'n') {
						word[counter]+="g";
					} else {
						word[counter]+="ng";
					}
				}
			}
		}
	}

	/*
	 * Check for grammar rules
	 */
	private String grammarRules(String toProcess, int intCounter) {
		String retVal = "";
		
		//if word is din, rin, daw, raw, daan, raan, check grammar
		//else if first word, capitalize, if not retain
		if( (toProcess.equalsIgnoreCase("din")) ||  (toProcess.equalsIgnoreCase("rin")) || (toProcess.equalsIgnoreCase("raw")) || 
				(toProcess.equalsIgnoreCase("daw")) || (toProcess.equalsIgnoreCase("doon")) || (toProcess.equalsIgnoreCase("roon")) || 
				(toProcess.equalsIgnoreCase("raan")) || (toProcess.equalsIgnoreCase("daan"))) {
			
			retVal = firstGrammarRule(toProcess, intCounter);
			
		} else {
			retVal = ((intCounter == 0) || ((intCounter != 0) && (word[intCounter-1].equals("."))))? toProcess.substring(0,1).toUpperCase() + toProcess.substring(1): toProcess;
		}

		return retVal;
	}
	
	/*
	 * Test for first grammar rule
	 */
	private String firstGrammarRule(String currWord, int intCounter) {
		
		char[] currWordLetters = currWord.toCharArray(); // get the letters of the word
		char currFirstLetter = currWordLetters[0]; // get the first letter of the word for checking later

		if(intCounter != 0) {
			String previous = word[intCounter - 1]; // get the previous word on the sentence
			char[] previousLetters = previous.toCharArray(); // get the letters of the word
			char prevLastLetter = previousLetters[previousLetters.length-1]; // get the last letter of the word to check if it is vowel or not
			
			boolean isVowel = ("AEIOUaeiou".indexOf(prevLastLetter)) == -1 ? false : true; // does the last letter of the previous word is a vowel?
			
			if(isVowel) { // if vowel
				if(currFirstLetter == 'd') { // does the first letter of the current word is 'd' (incorrect)
					currWordLetters[0] = 'r'; // change it to 'r' (correct)
				}
			}else if(!isVowel) { // if consonant
				if(currFirstLetter == 'r') { // does the first letter of the current word is 'r' (incorrect)
					currWordLetters[0] = 'd'; // change it to 'd' (correct)
				}
			}
		}
		
		return new String(currWordLetters);
	}
	
	/*
	 * Return the processed sentence
	 */
	public String getSentence() {
		String retVal = "";
		
		for(int intCounter = 0; intCounter < word.length; intCounter++) {
			retVal += word[intCounter];
			if(intCounter != word.length-1) {
				retVal += ((word[intCounter+1].equals(".")) ? "" : " ");
			}
		}
		
		return retVal;
	}
}
