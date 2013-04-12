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
		System.out.println(test.hashCode());
		
		test.setAttack("Vive-attaque");
		test.setPoke("@Dracaufeu13");
		
		assertEquals("Vive-attaque", test.getAttack());
		assertEquals("@Dracaufeu13", test.getPoke());
		
		AttacksPK test2 = new AttacksPK("@Dracaufeu13", "Vive-attaque");
		if (test==test2);
		
		assertEquals (test, test);
		
		AttacksPK test3 = new AttacksPK("@Dracaufeu13", null);
		if (test==test3);

		AttacksPK test4 = new AttacksPK(null, "Vive-attaque");
		if (test==test4);
		
		AttacksPK test5 = new AttacksPK(null, null);
		if (test==test5);

		System.out.println(test5.hashCode());
		
		assertNotSame(null,test3);
	}
}