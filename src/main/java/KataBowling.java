import java.util.Scanner;

import line.BowlingLine;

public class KataBowling {

	public static void main(String[] args) {
		try (Scanner inputScanner = new Scanner(System.in)) {
			System.out.println("Input bowling line:");
			BowlingLine line = new BowlingLine();
			try {
				line.processSequence(inputScanner.nextLine());
				System.out.println("Score: " + line.getScore());
			} catch(IllegalArgumentException iae) {
				System.err.println(iae.getMessage());
			}
		}
		
	}
}
