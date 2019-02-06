
public class MainController {
	
	public static void main(String args[]) {
		
		String input = "siya ay isang madasalin bata. magaganda daw at malaki baso."; // input
		CheckGrammar grammar = new CheckGrammar(input.trim()); // pass the clean string using trim()
		System.out.println(grammar.getSentence()); // display
	}
}
