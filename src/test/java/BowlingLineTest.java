import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class BowlingLineTest {

	@Test
	public void testLastFrameIsOpen_NoMissesAndNoSparesAndNoStrikes() {
		assertEquals(10*2, new BowlingLine("11 11 11 11 11 11 11 11 11 11").getScore());
		assertEquals(  75, new BowlingLine("12 24 44 27 35 35 53 81 71 62").getScore());
		assertEquals(10*9, new BowlingLine("18 18 27 36 45 54 63 72 72 81").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsOpen_MissesAndNoSparesAndNoStrikes() {
		assertEquals(  45, new BowlingLine("-- 1- -2 35 -- -- 44 44 -9 9-").getScore());
		assertEquals(10*5, new BowlingLine("-5 -5 -5 -5 -5 -5 -5 -5 -5 -5").getScore());
		assertEquals(10*9, new BowlingLine("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-").getScore());
		assertEquals(   0, new BowlingLine("-- -- -- -- -- -- -- -- -- --").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsOpen_StrikesAndNoSpares() {
		assertEquals(       7*30 + 20 + 10 + 0, new BowlingLine("X  X X  X X X X  X X --").getScore());
		assertEquals(12+2+14+4+30+28+18+8+17+7, new BowlingLine("X 2- X 4- X X X 8- X -7").getScore());
		assertEquals(12+2+14+4+30+28+18+8+12+2, new BowlingLine("X -2 X -4 X X X -8 X 2-").getScore());
		assertEquals(10+0+10+0+30+20+10+0+19+9, new BowlingLine("X -- X -- X X X -- X 27").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsOpen_SparesAndNoStrikes() {
		assertEquals(                    9*10 + 0, new BowlingLine("-/ -/ -/ -/ -/ -/ -/ -/ -/ --").getScore());
		assertEquals(10+13+10+15+16+17+10+19+10+0, new BowlingLine("1/ -/ 3/ -/ 5/ 6/ 7/ -/ 9/ --").getScore());
		assertEquals(12+ 2+14+ 4+16+17+18+ 8+10+7, new BowlingLine("1/ 2- 3/ 4- 5/ 6/ 7/ 8- 9/ -7").getScore());
		assertEquals(10+ 2+10+ 4+16+17+10+ 8+12+2, new BowlingLine("1/ -2 3/ -4 5/ 6/ 7/ -8 9/ 2-").getScore());
		assertEquals(10+ 0+10+ 0+16+17+10+ 0+12+9, new BowlingLine("1/ -- 3/ -- 5/ 6/ 7/ -- 9/ 27").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsOpen_SparesAndStrikes() {
		assertEquals(20+20+20+20+30+20+20+20+10+0, new BowlingLine("X  -/ X  -/ X  X  X  -/ X  --").getScore());
		assertEquals(20+20+20+20+30+28+20+20+10+0, new BowlingLine("X  2/ X  4/ X  X  X  8/ X  --").getScore());
		assertEquals(20+20+20+20+16+17+20+20+10+0, new BowlingLine("1/ X  3/ X  5/ 6/ 7/ X  9/ --").getScore());
		assertEquals( 3+ 0+14+ 9+16+ 6+ 7+20+19+9, new BowlingLine("12 -- 3/ 45 X  -6 7- 8/ X  9-").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsStrike_BonusStrikes2of2() {
		// '2-of-2' requires both bonus turns to be a strike
		assertEquals(9*7     + 30, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 X", "X X").getScore());
		assertEquals(8*15+20 + 30, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ X", "X X").getScore());
		assertEquals(          30, new BowlingLine("-- -- -- -- -- -- -- -- -- X", "X X").getScore());
		assertEquals(9*30    + 30, new BowlingLine("X  X  X  X  X  X  X  X  X  X", "X X").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsStrike_BonusStrikes1of2() {
		// '1-of-2' implies only the 1st bonus turn to be a strike
		assertEquals(9*7     + 20, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 X", "X -").getScore());
		assertEquals(9*7     + 25, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 X", "X 5").getScore());
		assertEquals(8*15+20 + 20, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ X", "X -").getScore());
		assertEquals(8*15+20 + 25, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ X", "X 5").getScore());
		assertEquals(          20, new BowlingLine("-- -- -- -- -- -- -- -- -- X", "X -").getScore());
		assertEquals(          25, new BowlingLine("-- -- -- -- -- -- -- -- -- X", "X 5").getScore());
		assertEquals(9*30    + 20, new BowlingLine("X  X  X  X  X  X  X  X  X  X", "X -").getScore());
		assertEquals(9*30    + 25, new BowlingLine("X  X  X  X  X  X  X  X  X  X", "X 5").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsStrike_BonusStrikes0of2() {
		// '0-of-2' requires both bonus turns NOT to be a strike
		assertEquals(9*7     + 10, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 X", "--").getScore());
		assertEquals(9*7     + 15, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 X", "23").getScore());
		assertEquals(8*15+20 + 10, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ X", "--").getScore());
		assertEquals(8*15+20 + 15, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ X", "23").getScore());
		assertEquals(          10, new BowlingLine("-- -- -- -- -- -- -- -- -- X", "--").getScore());
		assertEquals(          15, new BowlingLine("-- -- -- -- -- -- -- -- -- X", "23").getScore());
		assertEquals(8*30+20 + 10, new BowlingLine("X  X  X  X  X  X  X  X  X  X", "--").getScore());
		assertEquals(8*30+22 + 15, new BowlingLine("X  X  X  X  X  X  X  X  X  X", "23").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsStrike_BonusResultsInSpare() {
		// requires the first bonus turn NOT to be a strike
		assertEquals(9*7     + 20, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 X", "-/").getScore());
		assertEquals(9*7     + 20, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 X", "5/").getScore());
		assertEquals(8*15+20 + 20, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ X", "-/").getScore());
		assertEquals(8*15+20 + 20, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ X", "5/").getScore());
		assertEquals(          20, new BowlingLine("-- -- -- -- -- -- -- -- -- X", "-/").getScore());
		assertEquals(          20, new BowlingLine("-- -- -- -- -- -- -- -- -- X", "5/").getScore());
		assertEquals(8*30+20 + 20, new BowlingLine("X  X  X  X  X  X  X  X  X  X", "-/").getScore());
		assertEquals(8*30+25 + 20, new BowlingLine("X  X  X  X  X  X  X  X  X  X", "5/").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsSpare_BonusStrikes1of1() {
		// '1-of-1' requires the bonus turn to be a strike
		assertEquals(9*7        + 20, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 7/", "X").getScore());
		assertEquals(9*15       + 20, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/", "X").getScore());
		assertEquals(             20, new BowlingLine("-- -- -- -- -- -- -- -- -- -/", "X").getScore());
		assertEquals(7*30+29+20 + 20, new BowlingLine("X  X  X  X  X  X  X  X  X  9/", "X").getScore());
	}
	
	@Ignore("not yet done")
	@Test
	public void testLastFrameIsSpare_BonusStrikes0of1() {
		// '0-of-1' requires the bonus turn NOT to be a strike
		assertEquals(9*7        + 10, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 7/", "-").getScore());
		assertEquals(9*7        + 15, new BowlingLine("16 25 34 7- 52 16 -7 -7 43 7/", "5").getScore());
		assertEquals(9*15       + 10, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/", "-").getScore());
		assertEquals(9*15       + 15, new BowlingLine("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/", "5").getScore());
		assertEquals(             10, new BowlingLine("-- -- -- -- -- -- -- -- -- -/", "-").getScore());
		assertEquals(             15, new BowlingLine("-- -- -- -- -- -- -- -- -- -/", "5").getScore());
		assertEquals(7*30+29+20 + 10, new BowlingLine("X  X  X  X  X  X  X  X  X  9/", "-").getScore());
		assertEquals(7*30+29+20 + 15, new BowlingLine("X  X  X  X  X  X  X  X  X  9/", "5").getScore());
	}

}