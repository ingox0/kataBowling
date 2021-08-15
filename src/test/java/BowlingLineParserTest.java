import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BowlingLineParserTest {

	private BowlingFrameSequence sequence;
	
	@Before
	public void setUp() {
		sequence = BowlingFrame.createSequenceOfFrames(2);
	}
	
	@Test
	public void testParseCharToPoints_Numeral_shouldReturnCorrespondingNumber() {
		assertEquals(Integer.valueOf(0),
					 new BowlingLineParser(sequence.next()).parseCharToPoints('0'));
		assertEquals(Integer.valueOf(7),
					 new BowlingLineParser(sequence.next()).parseCharToPoints('7'));
	}
	@Test
	public void testParseCharToPoints_Strike_shouldReturn10() {
		assertEquals(Integer.valueOf(10),
					 new BowlingLineParser(sequence.next()).parseCharToPoints(BowlingLineParser.SIGN_STRIKE));
	}
	@Test
	public void testParseCharToPoints_Spare_shouldReturnRemainingPoints() {
		BowlingFrame frameWithScore7 = sequence.next().informOfPointsRecursively(7);
		assertEquals(Integer.valueOf(10-7),
					 new BowlingLineParser(frameWithScore7).parseCharToPoints(BowlingLineParser.SIGN_SPARE));
		BowlingFrame frameWithScore0 = sequence.next().informOfPointsRecursively(0);
		assertEquals(Integer.valueOf(10-0),
					 new BowlingLineParser(frameWithScore0).parseCharToPoints(BowlingLineParser.SIGN_SPARE));
	}

}
