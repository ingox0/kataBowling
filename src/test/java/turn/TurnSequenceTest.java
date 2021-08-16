package turn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TurnSequenceTest {
	
	private TurnSequence sequenceOf3Frames;
	
	@Before
	public void setUp() {
		sequenceOf3Frames = new TurnSequence(3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmtpySequence_shouldThrowIllegalArgumentException() {
		new TurnSequence(0);
	}
	
	@Test
	public void testForEachFrameTraversedInReverse_initialSequence_shouldPerformOnFirstFrameOnly() {
		final List<BowlingTurnFrame> FRAMES_TRAVERSED = new ArrayList<BowlingTurnFrame>();
		sequenceOf3Frames.forEachFrameTraversedInReverse(frame -> FRAMES_TRAVERSED.add(frame));
		assertEquals(1, FRAMES_TRAVERSED.size());
		assertEquals(FRAMES_TRAVERSED.get(0), sequenceOf3Frames.getCurrentTurn());
	}
	@Test
	public void testForEachFrameTraversedInReverse_traversedSequence_shouldPerformOnEachFrame() {
		final List<BowlingTurnFrame> FRAMES_TRAVERSED = new ArrayList<BowlingTurnFrame>();
		sequenceOf3Frames.proceedWithPoints(1,2, 10, 3,5)
			.forEachFrameTraversedInReverse(frame -> FRAMES_TRAVERSED.add(frame));
		assertEquals(3, FRAMES_TRAVERSED.size());
		assertEquals(FRAMES_TRAVERSED.get(0), sequenceOf3Frames.getCurrentTurn());
	}
	@Test
	public void testForEachFrameTraversedInReverse_bonusTurn_shouldIgnoreBonusTurn() {
		final List<BowlingTurnFrame> FRAMES_TRAVERSED = new ArrayList<BowlingTurnFrame>();
		sequenceOf3Frames.proceedWithPoints(1,2, 3,4, 10)
			.forEachFrameTraversedInReverse(frame -> FRAMES_TRAVERSED.add(frame));
		assertEquals(3, FRAMES_TRAVERSED.size());
		assertNotEquals(FRAMES_TRAVERSED.get(0), sequenceOf3Frames.getCurrentTurn());
	}
	
	@Test
	public void testProcessWithPoints_shouldPropagatePointsAndAutoForwardIfNeeded() {
		BowlingTurn turn0 = sequenceOf3Frames.proceedWithPoints(7).getCurrentTurn();
		assertEquals(7, turn0.getScore());
		
		sequenceOf3Frames.proceedWithPoints(3);
		assertEquals(7+3, turn0.getScore());
		BowlingTurn turn1 = sequenceOf3Frames.getCurrentTurn();
		assertEquals(0,  turn1.getScore());
		
		sequenceOf3Frames.proceedWithPoints(10);
		assertEquals(10, turn1.getScore());
		BowlingTurn turn2 = sequenceOf3Frames.getCurrentTurn();
		assertEquals(0,  turn2.getScore());
		
		sequenceOf3Frames.proceedWithPoints(8,2);
		assertEquals(10, turn2.getScore());
		
		BowlingTurn turn3 = sequenceOf3Frames.proceedWithPoints(5).getCurrentTurn();
		assertEquals(5, turn3.getScore());
		
		assertEquals(turn0.getClass(), BowlingTurnFrame.class);
		assertEquals(turn1.getClass(), BowlingTurnFrame.class);
		assertEquals(turn2.getClass(), BowlingTurnFrame.class);
		
		assertEquals(turn3.getClass(), BowlingTurnBonus.class);
	}

}
