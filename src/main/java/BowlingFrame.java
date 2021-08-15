import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class BowlingFrame {

	private final int NUMBER_OF_PINS = 10;
	
	private BowlingFrameType 		typeOfFrame;
	private Optional<BowlingFrame> 	predecessorFrame;
	private List<Integer> 			listOfThrowsToRegard = new ArrayList<Integer>();
	
	
	private BowlingFrame(Optional<BowlingFrame> predecessorFrame) {
		this.predecessorFrame = predecessorFrame;
		updateFrameTypeBasedOnPoints();
	}
	
	public static BowlingFrameSequence createSequenceOfFrames(int numberOfFrames) {
		return new BowlingFrameSequence(numberOfFrames) {
			@Override
			public BowlingFrame createFrameNextTo(Optional<BowlingFrame> predecessorFrame) {
				return new BowlingFrame(predecessorFrame);
			}
			@Override
			public void forEachTraversed(Consumer<? super BowlingFrame> action) {
				Optional<BowlingFrame> frame = getCurrentFrame();
				while(frame.isPresent()) {
					frame.ifPresent(action);
					frame = frame.flatMap(frame0 -> frame0.predecessorFrame);
				}
			}
		};
	}
	
	public int getScore() {
		return getScoreForLeadingThrows(getNumberOfThrowsToRegard());
	}
	private int getScoreForLeadingThrows(int numberOfThrows) {
		return listOfThrowsToRegard.stream()
				.limit(numberOfThrows)
				.mapToInt(score -> score)
				.sum();
	}
	
	public boolean isCompleted() {
		return getNumberOfThrowsToRegard() >= typeOfFrame.getNumberOfThrowsToComplete();
	}
	private int getNumberOfThrowsToRegard() {
		return listOfThrowsToRegard.size();
	}
	
	public BowlingFrame informOfPointsRecursively(Integer... numbersOfPinsThrown) {
		for(final Integer numberOfPinsThrown:numbersOfPinsThrown) {
			performActionForFrameIfWaiting(Optional.of(this), frame -> frame.handlePointsForThisFrame(numberOfPinsThrown));
			performActionForFrameIfWaiting(predecessorFrame,  frame -> frame.informOfPointsRecursively(numberOfPinsThrown));
		}
		return this;
	}
	private void performActionForFrameIfWaiting(Optional<BowlingFrame> frame, Consumer<? super BowlingFrame> consumer) {
		frame.filter(frame0 -> frame0.isWaitingForNotifications())
			 .ifPresent(consumer);
	}
	private void handlePointsForThisFrame(Integer numberOfPinsThrown) {
		listOfThrowsToRegard.add(numberOfPinsThrown);
		updateFrameTypeBasedOnPoints();
	}
	private void updateFrameTypeBasedOnPoints() {
		typeOfFrame = Stream.of(BowlingFrameType.RULE_STRIKE, BowlingFrameType.RULE_SPARE)
			.filter(type -> isConformToType(type))
			.findFirst().orElse(BowlingFrameType.RULE_DEFAULT);
	}
	private boolean isConformToType(BowlingFrameType rule) {
		return getScoreForLeadingThrows(rule.getNumberOfThrowsToComplete()) == NUMBER_OF_PINS;
	}
	private boolean isWaitingForNotifications() {
		return getNumberOfNotificationsRemaining() > 0;
	}
	private int getNumberOfNotificationsRemaining() {
		return typeOfFrame.getNumberOfThrowsToRegard() - getNumberOfThrowsToRegard();
	}
	
	public int getNumberOfPinsRemaining() {
		return NUMBER_OF_PINS - getNumberOfPinsThrown();
	}
	private int getNumberOfPinsThrown() {
		return getScoreForLeadingThrows(getNumberOfOwnThrowsNotified());
	}
	private int getNumberOfOwnThrowsNotified() {
		return Math.min(typeOfFrame.getNumberOfThrowsToComplete(),
						getNumberOfThrowsToRegard());
	}

}
