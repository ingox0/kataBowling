package line;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import turn.BowlingTurn;
import turn.TurnSequence;

public class LineParserTest {

	@Test
	public void testParseCharToPoints_Numeral_shouldReturnCorrespondingNumber() {
		assertEquals(Integer.valueOf(0),
					 new LineParser(createTurnWithNumberOfPinsThrown(0)).parseCharToPoints('0'));
		assertEquals(Integer.valueOf(7),
					 new LineParser(createTurnWithNumberOfPinsThrown(0)).parseCharToPoints('7'));
	}
	@Test
	public void testParseCharToPoints_Strike_shouldReturn10() {
		assertEquals(Integer.valueOf(10),
					 new LineParser(createTurnWithNumberOfPinsThrown(0)).parseCharToPoints(LineParser.SIGN_STRIKE));
	}
	@Test
	public void testParseCharToPoints_Spare_shouldReturnRemainingPoints() {
		assertEquals(Integer.valueOf(10-7),
					 new LineParser(createTurnWithNumberOfPinsThrown(7)).parseCharToPoints(LineParser.SIGN_SPARE));
		assertEquals(Integer.valueOf(10-0),
					 new LineParser(createTurnWithNumberOfPinsThrown(0)).parseCharToPoints(LineParser.SIGN_SPARE));
	}
	
	private BowlingTurn createTurnWithNumberOfPinsThrown(Integer numberOfPins) {
		return new TurnSequence(2).proceedWithPoints(numberOfPins).getCurrentTurn();
	}

}
