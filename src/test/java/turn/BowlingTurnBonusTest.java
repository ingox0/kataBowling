package turn;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingTurnBonusTest {

	@Test
	public void testScore_BonusTurnAfterSpare_shouldReturnScoreOf1Throw() {
		assertEquals( 0, createBonusTurnAfterSpare().informOfPointsRecursively(0).getScore());
		assertEquals( 7, createBonusTurnAfterSpare().informOfPointsRecursively(7).getScore());
		assertEquals(10, createBonusTurnAfterSpare().informOfPointsRecursively(10).getScore());
	}
	@Test
	public void testScore_BonusTurnAfterStrike_shouldReturnScoreOf2Throws() {
		assertEquals( 0, createBonusTurnAfterStrike().informOfPointsRecursively(0).getScore());
		assertEquals( 7, createBonusTurnAfterStrike().informOfPointsRecursively(7).getScore());
		assertEquals(10, createBonusTurnAfterStrike().informOfPointsRecursively(10).getScore());
		
		assertEquals(0+0,   createBonusTurnAfterStrike().informOfPointsRecursively(0,0).getScore());
		assertEquals(7+3,   createBonusTurnAfterStrike().informOfPointsRecursively(7,3).getScore());
		assertEquals(10+10, createBonusTurnAfterStrike().informOfPointsRecursively(10,10).getScore());
	}
	
	@Test
	public void testIsCompleted_BonusTurnAfterSpare_shouldReturnTrueAfter1Throw() {
		assertFalse(createBonusTurnAfterSpare().informOfPointsRecursively().isCompleted());
		assertTrue (createBonusTurnAfterSpare().informOfPointsRecursively( 0).isCompleted());
		assertTrue (createBonusTurnAfterSpare().informOfPointsRecursively( 7).isCompleted());
		assertTrue (createBonusTurnAfterSpare().informOfPointsRecursively(10).isCompleted());
	}
	@Test
	public void testIsCompleted_BonusTurnAfterStrike_shouldReturnTrueAfter2Throws() {
		assertFalse(createBonusTurnAfterStrike().informOfPointsRecursively( 0).isCompleted());
		assertFalse(createBonusTurnAfterStrike().informOfPointsRecursively( 7).isCompleted());
		assertFalse(createBonusTurnAfterStrike().informOfPointsRecursively(10).isCompleted());
		assertTrue (createBonusTurnAfterStrike().informOfPointsRecursively(0,0).isCompleted());
		assertTrue (createBonusTurnAfterStrike().informOfPointsRecursively(7,3).isCompleted());
		assertTrue (createBonusTurnAfterStrike().informOfPointsRecursively(10,10).isCompleted());
	}
	
	@Test
	public void testType_BonusTurnAfterSpare_shouldNotChangeType() {
		assertEquals(TurnType.TYPE_BONUS_AFTER_SPARE, createBonusTurnAfterSpare().getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_SPARE, createBonusTurnAfterSpare().informOfPointsRecursively( 0).getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_SPARE, createBonusTurnAfterSpare().informOfPointsRecursively( 7).getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_SPARE, createBonusTurnAfterSpare().informOfPointsRecursively(10).getType());
	}
	@Test
	public void testType_BonusTurnAfterStrike_shouldNotChangeType() {
		assertEquals(TurnType.TYPE_BONUS_AFTER_STRIKE, createBonusTurnAfterStrike().getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_STRIKE, createBonusTurnAfterStrike().informOfPointsRecursively( 0).getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_STRIKE, createBonusTurnAfterStrike().informOfPointsRecursively( 7).getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_STRIKE, createBonusTurnAfterStrike().informOfPointsRecursively(10).getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_STRIKE, createBonusTurnAfterStrike().informOfPointsRecursively(0,0).getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_STRIKE, createBonusTurnAfterStrike().informOfPointsRecursively(7,3).getType());
		assertEquals(TurnType.TYPE_BONUS_AFTER_STRIKE, createBonusTurnAfterStrike().informOfPointsRecursively(10,10).getType());
	}
	
	@Test
	public void testNumberOfRemainingPins_BonusTurnAfterSpare_shouldReturnDifferenceTo10() {
		assertEquals(10,    createBonusTurnAfterSpare().getNumberOfPinsRemaining());
		assertEquals(10-0,  createBonusTurnAfterSpare().informOfPointsRecursively( 0).getNumberOfPinsRemaining());
		assertEquals(10-7,  createBonusTurnAfterSpare().informOfPointsRecursively( 7).getNumberOfPinsRemaining());
		assertEquals(10-10, createBonusTurnAfterSpare().informOfPointsRecursively(10).getNumberOfPinsRemaining());
	}
	@Test
	public void testNumberOfRemainingPins_BonusTurnAfterStrike_shouldReturnDifferenceTo10ForTheFirstThrow() {
		assertEquals(10,   createBonusTurnAfterStrike().getNumberOfPinsRemaining());
		assertEquals(10-0, createBonusTurnAfterStrike().informOfPointsRecursively(0).getNumberOfPinsRemaining());
		assertEquals(10-7, createBonusTurnAfterStrike().informOfPointsRecursively(7).getNumberOfPinsRemaining());
	}
	@Test
	public void testNumberOfRemainingPins_BonusTurnAfterStrike_shouldReturnAnother10IfFirstThrowIsStrike() {
		assertEquals(10, createBonusTurnAfterStrike().informOfPointsRecursively(10).getNumberOfPinsRemaining());
	}
	@Test
	public void testNumberOfRemainingPins_BonusTurnAfterStrikeWithNoStrikeInFirstThrow_shouldConsiderBothThrowsCollectively() {
		assertEquals(10-0-3, createBonusTurnAfterStrike().informOfPointsRecursively(0,3).getNumberOfPinsRemaining());
		assertEquals(10-7-0, createBonusTurnAfterStrike().informOfPointsRecursively(7,0).getNumberOfPinsRemaining());
		assertEquals(10-7-3, createBonusTurnAfterStrike().informOfPointsRecursively(7,3).getNumberOfPinsRemaining());
	}
	@Test
	public void testNumberOfRemainingPins_BonusTurnAfterStrikeWithStrikeInFirstThrow_shouldAllowAnotherFullSetOfPins() {
		assertEquals(20-10-0,  createBonusTurnAfterStrike().informOfPointsRecursively(10, 0).getNumberOfPinsRemaining());
		assertEquals(20-10-3,  createBonusTurnAfterStrike().informOfPointsRecursively(10, 3).getNumberOfPinsRemaining());
		assertEquals(20-10-10, createBonusTurnAfterStrike().informOfPointsRecursively(10,10).getNumberOfPinsRemaining());
	}
	
	private BowlingTurn createBonusTurnAfterStrike() {
		return createTurnForPoints(10);
	}
	private BowlingTurn createBonusTurnAfterSpare() {
		return createTurnForPoints(0,10);
	}
	private BowlingTurn createTurnForPoints(Integer... numbersOfPoints) {
		return new TurnSequence(1).proceedWithPoints(numbersOfPoints).getCurrentTurn();
	}

}
