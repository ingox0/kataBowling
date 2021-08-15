import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingLine {

	private final int NUMBER_OF_FRAMES = 10;
	
	private BowlingFrameSequence 	sequenceOfFrames = BowlingFrame.createSequenceOfFrames(NUMBER_OF_FRAMES);
	private IntStream 				intStreamOfChars;
	
	
	public BowlingLine(String... lineSequences) {
		intStreamOfChars = getIntStreamOfAllCharacters(lineSequences);
		sequenceOfFrames.next();
		processEachCharacterOfSequence();
	}
	
	private IntStream getIntStreamOfAllCharacters(String... lineSequence) {
		return String.join("", lineSequence)
				.replaceAll("\\s","")
				.chars();
	}
	private void processEachCharacterOfSequence() {
		intStreamOfChars.forEach(charInt -> processCharacter((char)charInt));
	}
	private void processCharacter(char sequenceChar) {
		proceedWithPoints(createParserForCurrentFrame().parseCharToPoints(sequenceChar));
	}
	private void proceedWithPoints(int numberOfPoints) {
		sequenceOfFrames.getCurrentFrame().ifPresent(frame -> frame.informOfPointsRecursively(numberOfPoints));
		sequenceOfFrames.getCurrentFrame().filter(frame -> frame.isCompleted() && sequenceOfFrames.hasNext())
			.ifPresent(frame -> sequenceOfFrames.next());
	}
	private BowlingLineParser createParserForCurrentFrame() {
		return new BowlingLineParser(sequenceOfFrames.getCurrentFrame().get());
	}
	
	public int getScore() {
		final List<Integer> scores = new ArrayList<Integer>(NUMBER_OF_FRAMES);
		sequenceOfFrames.forEachTraversed(frame -> scores.add(frame.getScore()));
		return scores.stream().mapToInt(score -> score).sum();
	}

}
