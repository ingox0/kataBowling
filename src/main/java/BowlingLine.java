/**
 * This class represents a game of bowling.
 * @author ingox0
 */
public class BowlingLine {

	private String lineSequence;
	
	
	/**
	 * Creates a new bowling line based on the given sequences of rolls.
	 * @param lineSequences sequences of roles for this line
	 */
	public BowlingLine(String... lineSequences) {
		lineSequence = String.join(" ", lineSequences);
	}
	
	
	/**
	 * Returns the total score of this line.
	 * @return the sum of points made in this line
	 */
	public int getScore() {
		return 0;
	}
}
