public class BowlingLineParser {

	public final static char SIGN_SPARE 	= '/';
	public final static char SIGN_STRIKE 	= 'X';
	
	private BowlingFrame frame;
	
	
	public BowlingLineParser(BowlingFrame frame) {
		this.frame = frame;
	}
	
	
	public Integer parseCharToPoints(char sequenceChar) {
		try { return Integer.parseInt(""+sequenceChar); }
		catch(NumberFormatException nfe) {};
		return parseNonNumberToPoints(sequenceChar);
	}
	private Integer parseNonNumberToPoints(char sequenceChar) {
		return (sequenceChar==SIGN_STRIKE || sequenceChar==SIGN_SPARE) ?
			frame.getNumberOfPinsRemaining() : 0;
	}
}
