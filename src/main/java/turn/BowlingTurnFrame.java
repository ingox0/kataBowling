package turn;
import java.util.Arrays;
import java.util.Optional;

public class BowlingTurnFrame extends BowlingTurn {

	protected BowlingTurnFrame(Optional<BowlingTurnFrame> predecessorFrame) {
		super(predecessorFrame);
	}
	
	
	@Override
	protected void onNotificationOfThrow(Integer numberOfPinsThrown) {
		updateTurnType();
	}
	
	@Override
	protected void updateTurnType() {
		typeOfTurn = Arrays.asList(TurnType.TYPE_STRIKE, TurnType.TYPE_SPARE)
				.stream()
				.filter(type -> isConformToType(type))
				.findFirst().orElse(TurnType.TYPE_DEFAULT);
	}
	private boolean isConformToType(TurnType rule) {
		return getScoreForLeadingThrows(rule.getNumberOfThrowsToComplete()) == getNumberOfPins();
	}

}
