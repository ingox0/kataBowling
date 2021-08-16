package turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class BowlingTurn {

	private final int NUMBER_OF_PINS = 10;
	
	protected 	TurnType 					typeOfTurn;
	private 	Optional<BowlingTurnFrame> 	predecessorFrame;
	private 	List<Integer> 				listOfThrowsToAccount = new ArrayList<Integer>();
	
	
	protected BowlingTurn(Optional<BowlingTurnFrame> predecessorFrame) {
		this.predecessorFrame = predecessorFrame;
		updateTurnType();
	}
	
	protected abstract void updateTurnType();
	
	public BowlingTurn informOfPointsRecursively(Integer... numbersOfPinsThrown) {
		for(final Integer numberOfPinsThrown:numbersOfPinsThrown) {
			performActionForTurnIfWaiting(Optional.of(this), turn -> turn.handleThrowForThisTurn(numberOfPinsThrown));
			performActionForTurnIfWaiting(predecessorFrame,   turn -> turn.informOfPointsRecursively(numberOfPinsThrown));
		}
		return this;
	}
	private void handleThrowForThisTurn(Integer numberOfPinsThrown) {
		listOfThrowsToAccount.add(numberOfPinsThrown);
		onNotificationOfThrow(numberOfPinsThrown);
	}
	protected void onNotificationOfThrow(Integer numberOfPinsThrown) {};
	private void performActionForTurnIfWaiting(Optional<? extends BowlingTurn> turn, Consumer<BowlingTurn> consumer) {
		turn.filter(turn0 -> turn0.isWaitingForNotifications())
			.ifPresent(consumer);
	}
	protected boolean isWaitingForNotifications() {
		return getNumberOfNotificationsRemaining() > 0;
	}
	private int getNumberOfNotificationsRemaining() {
		return getType().getNumberOfThrowsToRegard() - getNumberOfThrowsToAccount();
	}
	
	public int getScore() {
		return getScoreForLeadingThrows(getNumberOfThrowsToAccount());
	}
	protected int getScoreForLeadingThrows(int numberOfThrows) {
		return listOfThrowsToAccount.stream()
				.limit(numberOfThrows)
				.mapToInt(integer -> integer)
				.sum();
	}
	
	public boolean isCompleted() {
		return getNumberOfThrowsToAccount() >= getType().getNumberOfThrowsToComplete();
	}
	private int getNumberOfThrowsToAccount() {
		return listOfThrowsToAccount.size();
	}
	
	protected Optional<BowlingTurnFrame> getPredecessorFrame() {
		return predecessorFrame;
	}
	public TurnType getType() {
		return typeOfTurn;
	}
	
	public int getNumberOfPinsRemaining() {
		return getNumberOfPins() - getNumberOfPinsThrown();
	}
	private int getNumberOfPinsThrown() {
		return getScoreForLeadingThrows(getNumberOfOwnThrowsNotified());
	}
	private int getNumberOfOwnThrowsNotified() {
		return Math.min(getType().getNumberOfThrowsToComplete(),
						getNumberOfThrowsToAccount());
	}
	protected int getNumberOfPins() {
		return NUMBER_OF_PINS;
	}

}
