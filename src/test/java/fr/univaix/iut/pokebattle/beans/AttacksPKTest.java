package fr.univaix.iut.pokebattle.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class AttacksPKTest {

	@Test
	public void test() {
		AttacksPK test = new AttacksPK();
		test = new AttacksPK("@GwenGoupix", "Flammeche");
		
		assertEquals("Flammeche", test.getAttack());
		assertEquals("@GwenGoupix", test.getPoke());

		System.out.println(test.toString());
		
		test.setAttack("Vive-attaque");
		test.setPoke("@Dracaufeu13");
		
		assertEquals("Vive-attaque", test.getAttack());
		assertEquals("@Dracaufeu13", test.getPoke());
		
		AttacksPK test2 = new AttacksPK("@Dracaufeu13", "Vive-attaque");
		assertEquals (test, test2);
		assertEquals (test, test);
		
		AttacksPK test3 = new AttacksPK("@Dracaufeu13", null);
		assertNotSame(test,test3);

		AttacksPK test4 = new AttacksPK(null, "Vive-attaque");
		assertNotSame(test,test4);
		
		assertNotSame(null,test);
	}
}