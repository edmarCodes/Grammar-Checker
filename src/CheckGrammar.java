// First grammar rule is the proper usage of din, rin, daw, raw, daan, raan

public class CheckGrammar {

	// String input
	private String strInput;
	
	// Tokenized input
	private String[] tokens;
	
	public CheckGrammar(String strInput) {
		this.strInput = strInput; // initialize input
		tokenizeInput();
	}
	
	
	private void tokenizeInput() {
		Tokenization tokenObj = new Tokenization(strInput);
		tokens = tokenObj.getTokens();
		Process();
	} 
	
	private void Process() {
		for(int intCounter = 0; intCounter < tokens.length; intCounter++) {
			tokens[intCounter] = grammarRules(tokens[intCounter], intCounter);
		}
	}
	
	private String grammarRules(String toProcess, int intCounter) {
		String retVal = "";
		
		//if word is din, rin, daw, raw, daan, raan, check grammar
		//else if first word, capitalize, if not retain
		if( (toProcess.equalsIgnoreCase("din")) ||  (toProcess.equalsIgnoreCase("rin")) || (toProcess.equalsIgnoreCase("raw")) || 
				(toProcess.equalsIgnoreCase("daw")) || "daan".indexOf(toProcess) > 0 || "raan".indexOf(toProcess) > 0 || 
				(toProcess.equalsIgnoreCase("doon")) || (toProcess.equalsIgnoreCase("roon"))) {
			retVal = firstGrammarRule(toProcess, intCounter);
		} else {
			retVal = (intCounter == 0) ? toProcess.substring(0,1).toUpperCase() + toProcess.substring(1): toProcess;
		}

		return retVal;
	}
	
	private String firstGrammarRule(String currWord, int intCounter) {
		
		char[] currWordLetters = currWord.toCharArray(); // get the letters of the word
		char currFirstLetter = currWordLetters[0]; // get the first letter of the word for checking later

		if(intCounter != 0) {
			String previous = tokens[intCounter - 1]; // get the previous word on the sentence
			char[] previousLetters = previous.toCharArray(); // get the letters of the word
			char prevLastLetter = previousLetters[previousLetters.length-1]; // get the last letter of the word to check if it is vowel or not
			
			boolean isVowel = ("AEIOUaeiou".indexOf(prevLastLetter)) == -1 ? false : true;
			
			if(isVowel) {
				if(currFirstLetter == 'd') {
					currWordLetters[0] = 'r';
				}
			}else if(!isVowel) {
				if(currFirstLetter == 'r') {
					currWordLetters[0] = 'd';
				}
			}
		}
		
		return new String(currWordLetters);
	}
	
	public String getSentence() {
		String retVal = "";
		
		for(int intCounter = 0; intCounter < tokens.length; intCounter++) {
			retVal += tokens[intCounter] + ((intCounter == tokens.length-1) ? "." : " ");
		}
		
		return retVal;
	}
}
