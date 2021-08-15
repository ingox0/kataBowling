import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BowlingFrameTest {

	@Test
	public void testScore_NoSparesAndNoStrikes_shouldAddUpThrows() {
		assertEquals(0, createFrame().informOfPointsRecursively(0).getScore());
		assertEquals(2, createFrame().informOfPointsRecursively(2).getScore());
		assertEquals(5, createFrame().informOfPointsRecursively(5).getScore());
		
		assertEquals(0, createFrame().informOfPointsRecursively(0,0).getScore());
		assertEquals(2, createFrame().informOfPointsRecursively(0,2).getScore());
		assertEquals(5, createFrame().informOfPointsRecursively(5,0).getScore());
		assertEquals(7, createFrame().informOfPointsRecursively(5,2).getScore());
	}
	@Test
	public void testScore_NoSparesAndNoStrikes_shouldAccountOnlyFirst2Throws() {
		assertEquals(0, createFrame().informOfPointsRecursively(0,0, 7).getScore());
		assertEquals(2, createFrame().informOfPointsRecursively(0,2, 7).getScore());
		assertEquals(5, createFrame().informOfPointsRecursively(5,0, 7).getScore());
		assertEquals(7, createFrame().informOfPointsRecursively(5,2, 7).getScore());
		
		assertEquals(7, createFrame().informOfPointsRecursively(5,2, 7,1).getScore());
		assertEquals(3, createFrame().informOfPointsRecursively(1,2).informOfPointsRecursively(3,4).getScore());
	}
	@Test
	public void testScore_Strike_shouldReturnInScoreOf10() {
		assertEquals(10, createFrame().informOfPointsRecursively(10).getScore());
	}
	@Test
	public void testScore_Strike_shouldAddOnNext2Throws() {
		assertEquals(15, createFrame().informOfPointsRecursively(10, 5).getScore());
		assertEquals(20, createFrame().informOfPointsRecursively(10, 10).getScore());
		assertEquals(17, createFrame().informOfPointsRecursively(10, 0,7).getScore());
		assertEquals(17, createFrame().informOfPointsRecursively(10, 5,2).getScore());
		assertEquals(20, createFrame().informOfPointsRecursively(10).informOfPointsRecursively(10).getScore());
		assertEquals(17, createFrame().informOfPointsRecursively(10).informOfPointsRecursively(5,2).getScore());
	}
	@Test
	public void testScore_Strike_shouldAccountOnlyNext2Throws() {
		assertEquals(10, createFrame().informOfPointsRecursively(10, 0,0, 7).getScore());
		assertEquals(17, createFrame().informOfPointsRecursively(10, 5,2, 7).getScore());
		assertEquals(30, createFrame().informOfPointsRecursively(10, 10, 10, 10).getScore());
		assertEquals(17, createFrame().informOfPointsRecursively(10, 5,2).informOfPointsRecursively(7,1).getScore());
	}
	@Test
	public void testScore_Spare_shouldReturnInScoreOf10() {
		assertEquals(10, createFrame().informOfPointsRecursively(0,10).getScore());
		assertEquals(10, createFrame().informOfPointsRecursively(7,3).getScore());
	}
	@Test
	public void testScore_Spare_shouldAddOnNext1Throw() {
		assertEquals(10, createFrame().informOfPointsRecursively(7,3, 0).getScore());
		assertEquals(15, createFrame().informOfPointsRecursively(7,3, 5).getScore());
		assertEquals(20, createFrame().informOfPointsRecursively(7,3, 10).getScore());
	}
	@Test
	public void testScore_Spare_shouldAccountOnlyNext1Throw() {
		assertEquals(10, createFrame().informOfPointsRecursively(7,3, 0,2).getScore());
		assertEquals(12, createFrame().informOfPointsRecursively(7,3, 2,0).getScore());
		assertEquals(20, createFrame().informOfPointsRecursively(7,3, 10, 5).getScore());
		assertEquals(20, createFrame().informOfPointsRecursively(7,3).informOfPointsRecursively(10).getScore());
		assertEquals(15, createFrame().informOfPointsRecursively(7,3).informOfPointsRecursively(5,2).getScore());
	}
	
	@Test
	public void testIsCompleted_NoSparesAndNoStrikes_shouldReturnTrueIffAtLeast2Notifications() {
		assertFalse(createFrame().isCompleted());
		assertFalse(createFrame().informOfPointsRecursively(0).isCompleted());
		assertFalse(createFrame().informOfPointsRecursively(5).isCompleted());
		assertTrue (createFrame().informOfPointsRecursively(0,0).isCompleted());
		assertTrue (createFrame().informOfPointsRecursively(5,2).isCompleted());
		assertTrue (createFrame().informOfPointsRecursively(5,2, 1).isCompleted());
		assertTrue (createFrame().informOfPointsRecursively(5).informOfPointsRecursively(2).isCompleted());
		assertTrue (createFrame().informOfPointsRecursively(5,2).informOfPointsRecursively(1).isCompleted());
	}
	@Test
	public void testIsCompleted_Strike_shouldReturnTrue() {
		assertTrue(createFrame().informOfPointsRecursively(10).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(10, 5).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(10, 10).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(10, 5,2).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(10).informOfPointsRecursively(10).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(10).informOfPointsRecursively(5,2).isCompleted());
	}
	@Test
	public void testIsCompleted_Spare_shouldReturnTrue() {
		assertTrue(createFrame().informOfPointsRecursively(0,10).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(7,3).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(7,3, 1).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(7,3).informOfPointsRecursively(10).isCompleted());
		assertTrue(createFrame().informOfPointsRecursively(7,3).informOfPointsRecursively(5,2).isCompleted());
	}
	
	@Test
	public void testNumberOfRemainingPins_NoSparesAndNoStrikes_shouldReturnDifferenceTo10() {
		assertEquals(10, createFrame().getNumberOfPinsRemaining());
		assertEquals(10, createFrame().informOfPointsRecursively( 0).getNumberOfPinsRemaining());
		assertEquals( 3, createFrame().informOfPointsRecursively( 7).getNumberOfPinsRemaining());
		assertEquals(10, createFrame().informOfPointsRecursively(0,0).getNumberOfPinsRemaining());
		assertEquals( 3, createFrame().informOfPointsRecursively(5,2).getNumberOfPinsRemaining());
		assertEquals( 0, createFrame().informOfPointsRecursively(8,2).getNumberOfPinsRemaining());
	}
	@Test
	public void testNumberOfRemainingPins_NoSparesAndNoStrikes_shouldAccountOnlyFirst2Throws() {
		assertEquals(10-0-0, createFrame().informOfPointsRecursively(0,0, 7).getNumberOfPinsRemaining());
		assertEquals(10-0-2, createFrame().informOfPointsRecursively(0,2, 7).getNumberOfPinsRemaining());
		assertEquals(10-5-0, createFrame().informOfPointsRecursively(5,0, 7).getNumberOfPinsRemaining());
		assertEquals(10-5-2, createFrame().informOfPointsRecursively(5,2, 7).getNumberOfPinsRemaining());
		
		assertEquals(10-5-2, createFrame().informOfPointsRecursively(5,2, 7,1).getNumberOfPinsRemaining());
		assertEquals(10-1-2, createFrame().informOfPointsRecursively(1,2).informOfPointsRecursively(3,4).getNumberOfPinsRemaining());
	}
	@Test
	public void testNumberOfRemainingPins_Strike_shouldReturn0() {
		assertEquals(0, createFrame().informOfPointsRecursively(10).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(10, 0,0).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(10, 5,2).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(10, 10, 10).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(10).informOfPointsRecursively(7,3).getNumberOfPinsRemaining());
	}
	@Test
	public void testNumberOfRemainingPins_Spare_shouldReturn0() {
		assertEquals(0, createFrame().informOfPointsRecursively(0,10).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(7,3).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(7,3, 5).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(7,3, 10, 10).getNumberOfPinsRemaining());
		assertEquals(0, createFrame().informOfPointsRecursively(7,3).informOfPointsRecursively(5,2).getNumberOfPinsRemaining());
	}
	
	private BowlingFrame createFrame() {
		return BowlingFrame.createSequenceOfFrames(2).next();
	}
}
