package turn;

public abstract class TurnType {

	public final static TurnType TYPE_STRIKE = new TurnType() {
		@Override protected int getNumberOfForeignThrowsToRegard()	{ return 2; }
		@Override public 	int getNumberOfThrowsToComplete()		{ return 1; }
	};
	public final static TurnType TYPE_SPARE = new TurnType() {
		@Override protected int getNumberOfForeignThrowsToRegard()	{ return 1; }
		@Override public 	int getNumberOfThrowsToComplete()		{ return 2; }
	};
	public final static TurnType TYPE_DEFAULT = new TurnType() {
		@Override protected int getNumberOfForeignThrowsToRegard()	{ return 0; }
		@Override public 	int getNumberOfThrowsToComplete()		{ return 2; }
	};
	
	public final static TurnType TYPE_BONUS_AFTER_STRIKE = new TurnType() {
		@Override protected int getNumberOfForeignThrowsToRegard() 	{ return 0; }
		@Override public 	int getNumberOfThrowsToComplete() 		{ return 2; }
	};
	public final static TurnType TYPE_BONUS_AFTER_SPARE = new TurnType() {
		@Override protected int getNumberOfForeignThrowsToRegard() 	{ return 0; }
		@Override public 	int getNumberOfThrowsToComplete() 		{ return 1; }
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
