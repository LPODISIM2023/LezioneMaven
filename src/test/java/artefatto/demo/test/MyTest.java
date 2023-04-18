package artefatto.demo.test;

import static artefatto.demo.business.SupportoNumeriUtil.isPrime;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;;

public class MyTest {

	@Test
	public void isPrimeTest() {
		assertTrue(isPrime(4));
	}
	@Test
	public void isNotPrimeTest() {
		assertTrue(!isPrime(6));
	}
}
