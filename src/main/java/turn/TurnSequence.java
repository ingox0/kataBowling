package turn;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class TurnSequence {

	private BowlingTurn 		currentTurn;
	private BowlingTurnFrame 	currentFrame;
	private int 				numberOfRemainingFrames;
	
	
	public TurnSequence(int numberOfFrames) throws IllegalArgumentException {
		if(numberOfFrames<=0)
			throw new IllegalArgumentException();
		numberOfRemainingFrames = numberOfFrames;
		nextTurn();
	}
	
	
	public BowlingTurn getCurrentTurn() {
		return currentTurn;
	}
	
	public void forEachFrameTraversedInReverse(Consumer<BowlingTurnFrame> action) {
		Optional<BowlingTurnFrame> frame = Optional.of(currentFrame);
		while(frame.isPresent()) {
			action.accept(frame.get());
			frame = frame.get().getPredecessorFrame();
		}
	}
	
	public TurnSequence proceedWithPoints(Integer... numbersOfPoints) {
		for(Integer integer:numbersOfPoints)
			proceedWithPoints(integer);
		return this;
	}
	private void proceedWithPoints(final int numberOfPoints) {
		getCurrentTurn().informOfPointsRecursively(numberOfPoints);
		if(getCurrentTurn().isCompleted() && hasNextTurn())
			nextTurn();
	}
	
	private boolean hasNextTurn() {
		return hasNextFrame() || hasExtraThrows();
	}
	private boolean hasNextFrame() {
		return numberOfRemainingFrames > 0;
	}
	private boolean hasExtraThrows() {
		return getCurrentTurn().getType().hasForeignThrowsToRegard();
	}
	
	private BowlingTurn nextTurn() throws NoSuchElementException {
		if(!hasNextTurn())
			throw new NoSuchElementException();
		currentTurn = createTurnNextTo(Optional.ofNullable(currentFrame));
		return currentTurn;
	}
	private BowlingTurn createTurnNextTo(Optional<BowlingTurnFrame> predecessor) {
		return hasNextFrame() ? createFrameNextTo(predecessor) : new BowlingTurnBonus(predecessor);
	}
	private BowlingTurnFrame createFrameNextTo(Optional<BowlingTurnFrame> predecessor) {
		currentFrame = new BowlingTurnFrame(predecessor);
		numberOfRemainingFrames--;
		return currentFrame;
	}

}
