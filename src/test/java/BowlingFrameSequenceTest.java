import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class BowlingFrameSequenceTest {
	
	private BowlingFrameSequence 	emptySequence,
									sequenceOf3Frames;
	
	@Before
	public void setUp() {
		emptySequence 		= BowlingFrame.createSequenceOfFrames(0);
		sequenceOf3Frames 	= BowlingFrame.createSequenceOfFrames(3);
	}
	
	@Test
	public void testHasNext_emptySequence_shouldReturnFalse() {
		assertFalse(emptySequence.hasNext());
	}
	@Test
	public void testHasNext_shouldReturnFalseOnceTheLastFrameHasBeenReached() {
									assertTrue (sequenceOf3Frames.hasNext());
		sequenceOf3Frames.next();	assertTrue (sequenceOf3Frames.hasNext());
		sequenceOf3Frames.next();	assertTrue (sequenceOf3Frames.hasNext());
		sequenceOf3Frames.next();	assertFalse(sequenceOf3Frames.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testNext_emptySequence_exception() {
		emptySequence.next();
	}
	@Test
	public void testNext_sequenceOf3Frames_shouldReturnNewInstancesOfFramesUpToThePredefinedNumber() {
		BowlingFrame[] frames = new BowlingFrame[3];
		frames[0] = sequenceOf3Frames.next();
		frames[1] = sequenceOf3Frames.next();
		frames[2] = sequenceOf3Frames.next();
		
		assertNotEquals(frames[0], frames[1]);
		assertNotEquals(frames[0], frames[2]);
		assertNotEquals(frames[1], frames[2]);
		
		assertNotNull(frames[0]);
		assertNotNull(frames[1]);
		assertNotNull(frames[2]);
	}
	@Test(expected=NoSuchElementException.class)
	public void testNext_sequenceOf3Frames_exceptionOnceTheLastFramesHasBeenReached() {
		sequenceOf3Frames.next();
		sequenceOf3Frames.next();
		sequenceOf3Frames.next();
		sequenceOf3Frames.next();
	}

}
