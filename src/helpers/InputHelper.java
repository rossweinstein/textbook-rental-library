package helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputHelper {

	private Scanner input;

	/** Default constructor for the InputHelper Class */
	public InputHelper() {
		this.input = new Scanner(System.in);
	}

	/** Returns a String for the given question */
	public String askForString(String question) {

		System.out.print(question);
		String answer = this.input.next();
		return answer;

	}

	/**
	 * Returns a int for a given question. This method checks to see if the
	 * answer is a int before returning anything.
	 */
	public int askForInteger(String question) {

		
		int answer = 0;
		boolean itsANumber = false;

		while (!itsANumber) {

			try {
				String possibleInt = this.askForString(question);
				answer = Integer.parseInt(possibleInt);
				itsANumber = true;

			} catch (NumberFormatException e) {
				System.out.println("You Must Enter A Number.");
			}
		}

		return answer;

	}

	/**
	 * This method takes a question and it's possible answers. If one of the
	 * predetermined answers is not given, it will ask the question again.
	 */
	public String askQuestion(String question, String... possibleAnswers) {

		String answer = "";
		boolean questionUnanswered = true;

		List<String> theList = Arrays.asList(possibleAnswers);

		while (questionUnanswered) {

			System.out.print(question + ": ");
			answer = this.input.next();

			if (theList.contains(answer)) {
				questionUnanswered = false;
			} else {
				System.out.println("Invalid Entry. Must Return " + this.listChoices(theList));
			}
		}
		return answer;
	}

	public int askForSelection(List<String> choices) {

		int validAnswer = 0;

		while (!this.numberInRange(validAnswer, choices.size())) {

			validAnswer = this.askForInteger("Selection (1-" + choices.size() + "): ");
		}

		return validAnswer;
	}
	
	private boolean numberInRange(int number, int upperRange) {
		return number >= 1 && number <= upperRange;
	}

	/**
	 * This method asks a question with only two possible responses. It returns
	 * true if the first selection is chosen, false otherwise
	 */
	public boolean askBinaryQuestion(String question, String yes, String no) {

		String answer = "";
		boolean questionUnanswered = true;
		boolean result = false;

		while (questionUnanswered) {

			System.out.print(question + ": ");
			answer = this.input.next();

			if (answer.equalsIgnoreCase(yes)) {
				result = true;
				questionUnanswered = false;
			} else if (answer.equalsIgnoreCase(no)) {
				result = false;
				questionUnanswered = false;
			} else {
				System.out.println("Invalid Entry. You Must Enter " + yes + " or " + no);
			}
		}
		return result;
	}

	/** Returns the possible choices as a comma separated list */
	private String listChoices(List<String> choices) {

		String theChoices = "";

		for (int x = 0; x < choices.size(); x++) {

			if (x < choices.size() - 1) {
				theChoices += choices.get(x) + ", ";
			} else {
				theChoices += "or " + choices.get(x);
			}
		}
		return theChoices;
	}

	public static void main(String[] args) {
		InputHelper input = new InputHelper();

		input.askQuestion("Yes or No", "y", "n");

	}
}
