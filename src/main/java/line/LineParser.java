package line;
import turn.BowlingTurn;

public class LineParser {

	public final static char SIGN_MISS 		= '-';
	public final static char SIGN_SPARE 	= '/';
	public final static char SIGN_STRIKE 	= 'X';
	
	private BowlingTurn turn;
	
	
	public LineParser(BowlingTurn turn) {
		this.turn = turn;
	}
	
	
	public Integer parseCharToPoints(char sequenceChar) throws IllegalArgumentException {
		try { return Integer.parseInt(""+sequenceChar); }
		catch(NumberFormatException nfe) {};
		return parseNonNumberToPoints(sequenceChar);
	}
	private Integer parseNonNumberToPoints(char sequenceChar) throws IllegalArgumentException {
		if(sequenceChar==SIGN_STRIKE || sequenceChar==SIGN_SPARE)
			return turn.getNumberOfPinsRemaining();
		if(sequenceChar==SIGN_MISS)
			return 0;
		throw new IllegalArgumentException("Invalid character: '"+sequenceChar+"'");
	}
}
