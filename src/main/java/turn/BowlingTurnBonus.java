package turn;

import java.util.Optional;

public class BowlingTurnBonus extends BowlingTurn {

	private int numberOfPinsRemaining = getNumberOfPins();
	
	
	protected BowlingTurnBonus(Optional<BowlingTurnFrame> predecessorFrame) {
		super(predecessorFrame);
	}
	
	
	@Override
	protected void onNotificationOfThrow(Integer numberOfPinsThrown) {
		numberOfPinsRemaining = isAnotherFullSetOfPins(numberOfPinsThrown) ?
				getNumberOfPins() :
				numberOfPinsRemaining - numberOfPinsThrown;
	}
	private boolean isAnotherFullSetOfPins(Integer numberOfPinsThrown) {
		return numberOfPinsThrown==getNumberOfPins() && !isCompleted();
	}
	@Override
	public int getNumberOfPinsRemaining() {
		return numberOfPinsRemaining;
	}
	
	@Override
	protected void updateTurnType() {
		typeOfTurn = getPredecessorFrame().get().getType()==TurnType.TYPE_STRIKE ?
				TurnType.TYPE_BONUS_AFTER_STRIKE : TurnType.TYPE_BONUS_AFTER_SPARE;
	}

}
