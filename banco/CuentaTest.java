package banco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CuentaTest {

	@Test
	void testAccountIsEmpty() {
		//System.out.print(new Cuenta().balance());
		assertEquals( 0,new Cuenta().balance() );
	}
	@Test
	void testDepositar() {
		assertEquals(10, new Cuenta().depositar(10).balance());
	}
	
	@Test
	void testExtraccionMenorALoDepositado() {
		Cuenta cuenta= new Cuenta();
		cuenta.depositar(10).extraer(10);
		assertEquals(0, cuenta.balance());
	}
	@Test
	void testExtraccionMayorAlBalance() {
		Cuenta cuenta= new Cuenta();
		assertThrows(RuntimeException.class, ()-> cuenta.extraer(5));
		assertEquals(0, cuenta.balance());
	}
	
	@Test
	void testReporteDespuesDeUnDeposito() {
		assertEquals("Deposito: 10", new Cuenta().depositar(10).reporte());
	}
	
	@Test
	void testReporteDespuesDeUnaExtraccion() {
		Cuenta cuenta= new Cuenta();
		cuenta.depositar(10).extraer(5);
		assertEquals("Deposito: 10\n" + "Extraccion: 5", cuenta.reporte());
	}
	
}
