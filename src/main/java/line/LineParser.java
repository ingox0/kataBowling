package line;
import turn.BowlingTurn;

public class LineParser {

	public final static char SIGN_SPARE 	= '/';
	public final static char SIGN_STRIKE 	= 'X';
	
	private BowlingTurn turn;
	
	
	public LineParser(BowlingTurn turn) {
		this.turn = turn;
	}
	
	
	public Integer parseCharToPoints(char sequenceChar) {
		try { return Integer.parseInt(""+sequenceChar); }
		catch(NumberFormatException nfe) {};
		return parseNonNumberToPoints(sequenceChar);
	}
	private Integer parseNonNumberToPoints(char sequenceChar) {
		return (sequenceChar==SIGN_STRIKE || sequenceChar==SIGN_SPARE) ?
			turn.getNumberOfPinsRemaining() : 0;
	}
}
