package turn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TurnTypeTest {

	@Test
	public void testNumberOfThrowsToRegard_shouldAddUpNumberOfThrowsToCompleteTheFrameAndExtraThrows() {
		assertEquals(2+0, TurnType.TYPE_DEFAULT.getNumberOfThrowsToRegard());
		assertEquals(2+1, TurnType.TYPE_SPARE.getNumberOfThrowsToRegard());
		assertEquals(1+2, TurnType.TYPE_STRIKE.getNumberOfThrowsToRegard());
		
		assertEquals(0+1, TurnType.TYPE_BONUS_AFTER_SPARE.getNumberOfThrowsToRegard());
		assertEquals(0+2, TurnType.TYPE_BONUS_AFTER_STRIKE.getNumberOfThrowsToRegard());
	}
	
	@Test
	public void testHasForeignThrowsToRegard_shouldIndicateWhetherExtraThrowsAreTakenIntoAccount() {
		assertFalse(TurnType.TYPE_DEFAULT.hasForeignThrowsToRegard());
		assertTrue (TurnType.TYPE_SPARE.hasForeignThrowsToRegard());
		assertTrue (TurnType.TYPE_STRIKE.hasForeignThrowsToRegard());
		
		assertFalse(TurnType.TYPE_BONUS_AFTER_SPARE.hasForeignThrowsToRegard());
		assertFalse(TurnType.TYPE_BONUS_AFTER_STRIKE.hasForeignThrowsToRegard());
	}

}
