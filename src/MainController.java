import java.util.Scanner;

public class MainController {
	
	public static void main(String args[]) {
		
		Scanner getInput = new Scanner(System.in);
		System.out.println("Punan ng pangungusap: ");
		String input = getInput.nextLine();
		
		CheckGrammar grammar = new CheckGrammar(input.trim()); // pass the clean string using trim()
		System.out.println(grammar.getSentence());
	}
}
