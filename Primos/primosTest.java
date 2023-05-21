package Primos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class primosTest {
@Test public void testPrimos_0() {
	int[] nullArray = Primos.generarPrimos(0);
	assertEquals(nullArray.length, 0);
	}
@Test public void testPrimos_2() {
	int[] minArray = Primos.generarPrimos(2);
	assertEquals(minArray.length, 1);
	assertEquals(minArray[0], 2);
	}

@Test public void testPrimos_3() {
	int[] threeArray = Primos.generarPrimos(3);
	assertEquals(threeArray.length, 2);
	assertEquals(threeArray[0], 2);
	assertEquals(threeArray[1], 3);
	}

@Test public void testPrimos_100() {
	int[] centArray = Primos.generarPrimos(100);
	assertEquals(centArray.length, 25);
	assertEquals(centArray[24], 97);
	}

}