import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class BowlingFrameSequence implements Iterator<BowlingFrame> {

	private BowlingFrame 	currentFrame = null;
	private int 			numberOfRemainingFrames;
	
	
	protected BowlingFrameSequence(int numberOfFrames) {
		numberOfRemainingFrames = numberOfFrames;
	}
	
	
	protected abstract BowlingFrame createFrameNextTo(Optional<BowlingFrame> predecessorFrame);
	protected abstract void forEachTraversed(Consumer<? super BowlingFrame> action);
	
	@Override
	public boolean hasNext() {
		return numberOfRemainingFrames > 0;
	}
	@Override
	public BowlingFrame next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		currentFrame = createFrameNextTo(getCurrentFrame());
		numberOfRemainingFrames--;
		return currentFrame;
	}
	
	protected Optional<BowlingFrame> getCurrentFrame() {
		return Optional.ofNullable(currentFrame);
	}

}
