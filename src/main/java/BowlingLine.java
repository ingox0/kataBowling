import java.util.Arrays;
import java.util.stream.Stream;

/**
 * This class represents a game of bowling.
 * @author ingox0
 */
public class BowlingLine {

	private Stream<String> streamOfTurnStrings;
	
	
	/**
	 * Creates a new bowling line based on the given sequences of rolls.
	 * @param lineSequences sequences of roles for this line
	 */
	public BowlingLine(String... lineSequences) {
		streamOfTurnStrings = convertSequencesToStreamOfTurns(lineSequences);
	}
	
	
	/**
	 * Returns the total score of this line.
	 * @return the sum of points made in this line
	 */
	public int getScore() {
		return streamOfTurnStrings
				.map(turnString -> parseStringOfTurnToPoints(turnString))
				.reduce(0, (pointsA,pointsB) -> pointsA+pointsB)
				.intValue();
	}
	
	private Stream<String> convertSequencesToStreamOfTurns(String[] lineSequences) {
		return Arrays.stream(String.join("",lineSequences)
				.replaceAll("\\s", "")
				.split(""));
	}
	private Integer parseStringOfTurnToPoints(String turnCharacter) {
		try { return Integer.parseInt(turnCharacter); }
		catch(NumberFormatException nfe) { return 0; }
	}
}
