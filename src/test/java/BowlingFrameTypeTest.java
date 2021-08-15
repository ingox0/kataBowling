import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BowlingFrameTypeTest {

	@Test
	public void testNumberOfThrowsToRegard_shouldAddUpNumberOfThrowsToCompleteTheFrameAndExtraThrows() {
		assertEquals(2+0, BowlingFrameType.RULE_DEFAULT.getNumberOfThrowsToRegard());
		assertEquals(2+1, BowlingFrameType.RULE_SPARE.getNumberOfThrowsToRegard());
		assertEquals(1+2, BowlingFrameType.RULE_STRIKE.getNumberOfThrowsToRegard());
	}
	
	@Test
	public void testHasForeignThrowsToRegard_shouldIndicateWhetherExtraThrowsAreTakenIntoAccount() {
		assertFalse(BowlingFrameType.RULE_DEFAULT.hasForeignThrowsToRegard());
		assertTrue (BowlingFrameType.RULE_SPARE.hasForeignThrowsToRegard());
		assertTrue (BowlingFrameType.RULE_STRIKE.hasForeignThrowsToRegard());
	}

}
