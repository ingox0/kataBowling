package line;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import turn.TurnSequence;

public class BowlingLine {

	private final int NUMBER_OF_FRAMES = 10;
	
	private TurnSequence sequenceOfFrames = new TurnSequence(NUMBER_OF_FRAMES);
	
	
	public BowlingLine(String... lineSequences) {
		processSequence(lineSequences);
	}
	
	public void processSequence(String... lineSequence) {
		processEachCharacterOfSequence(getIntStreamOfAllCharacters(lineSequence));
	}
	private IntStream getIntStreamOfAllCharacters(String... lineSequence) {
		return String.join("", lineSequence)
				.replaceAll("\\s","")
				.chars();
	}
	private void processEachCharacterOfSequence(IntStream intStreamOfChars) {
		intStreamOfChars.forEach(charInt -> processCharacter((char)charInt));
	}
	private void processCharacter(char sequenceChar) {
		sequenceOfFrames.proceedWithPoints(createParserForCurrentFrame().parseCharToPoints(sequenceChar));
	}
	
	private LineParser createParserForCurrentFrame() {
		return new LineParser(sequenceOfFrames.getCurrentTurn());
	}
	
	public int getScore() {
		final List<Integer> SCORES = new ArrayList<Integer>(NUMBER_OF_FRAMES);
		sequenceOfFrames.forEachFrameTraversedInReverse(frame -> SCORES.add(frame.getScore()));
		return SCORES.stream().mapToInt(score -> score).sum();
	}

}
