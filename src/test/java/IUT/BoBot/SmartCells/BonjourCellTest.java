package IUT.BoBot.SmartCells;

import static org.junit.Assert.*;

import org.junit.Test;

public class BonjourCellTest {

	@Test
	public void testBonjour() {
		BonjourCell cell = new BonjourCell("bonjour");
		assertEquals("Bonjour!", cell.answer());
	}

}