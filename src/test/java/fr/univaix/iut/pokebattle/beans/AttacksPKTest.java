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

		System.out.println(test.toString());
		System.out.println(test.hashCode());
		
		test.setAttack(null);
		test.setPoke(null);
		
		System.out.println(test.hashCode());
	}
}