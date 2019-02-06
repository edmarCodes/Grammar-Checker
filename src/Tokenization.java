public class Tokenization {
	
    /*
     * String tokens (word in a sentence)
     */
    private String[] tokens;
    
    /*
     * Split each word
     */
    public Tokenization(String sInput, String splitUsing){
        tokens = sInput.split(splitUsing);
    }
    
    /*
     * Return each word
     */
    public String[] getTokens() {
    	return tokens;
    }
}
