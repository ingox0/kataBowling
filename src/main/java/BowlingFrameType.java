
public abstract class BowlingFrameType {

	public final static BowlingFrameType RULE_STRIKE = new BowlingFrameType() {
		@Override protected int getNumberOfForeignThrowsToRegard()	{ return 2; }
		@Override public 	int getNumberOfThrowsToComplete()		{ return 1; }
	};
	public final static BowlingFrameType RULE_SPARE = new BowlingFrameType() {
		@Override protected int getNumberOfForeignThrowsToRegard()	{ return 1; }
		@Override public 	int getNumberOfThrowsToComplete()		{ return 2; }
	};
	public final static BowlingFrameType RULE_DEFAULT = new BowlingFrameType() {
		@Override protected int getNumberOfForeignThrowsToRegard()	{ return 0; }
		@Override public 	int getNumberOfThrowsToComplete()		{ return 2; }
	};
	
	
	protected abstract int getNumberOfForeignThrowsToRegard();
	public    abstract int getNumberOfThrowsToComplete();
	
	public int getNumberOfThrowsToRegard() {
		return getNumberOfThrowsToComplete() + getNumberOfForeignThrowsToRegard();
	}
	public boolean hasForeignThrowsToRegard() {
		return getNumberOfForeignThrowsToRegard() > 0;
	}

}
