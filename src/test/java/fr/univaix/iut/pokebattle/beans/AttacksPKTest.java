package fr.univaix.iut.pokebattle.beans;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AttacksPKTest {

	@Test
	public void test() {
		AttacksPK test = new AttacksPK();
		test = new AttacksPK("@GwenGoupix", "Flammeche");
		
		assertEquals("Flammeche", test.getAttack());
		assertEquals("@GwenGoupix", test.getPoke());

		test.setAttack("Vive-attaque");
		test.setPoke("@Dracaufeu13");
		
		assertEquals("Vive-attaque", test.getAttack());
		assertEquals("@Dracaufeu13", test.getPoke());
		
		AttacksPK test2 = new AttacksPK("@Dracaufeu13", "Vive-attaque");
		assertEquals (test, test2);
		
		System.out.println(test.toString());
		System.out.println(test.hashCode());
	}
}