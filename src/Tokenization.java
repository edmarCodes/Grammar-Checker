public class Tokenization {
    
    private String[] tokens;
    public Tokenization(String sInput){
        tokens = sInput.split(" ");
    }
    public String[] getTokens() {
    	return tokens;
    }
}
