package codigorepetido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class CustomerBookTest {

  private static String Vacio = "";
  private static String RingoStar = "Ringo Star";
  private static String GeorgeHarrison = "George Harrison";
  private static String PaulMcCartney = "Paul McCartney";
  private static String JohnLennon = "John Lennon";

@Test public void testAddingCustomerShouldNotTakeMoreThan50Milliseconds() {
    assertTimingIsCorrect(JohnLennon);
  }

  @Test public void testRemovingCustomerShouldNotTakeMoreThan100Milliseconds() {
    beatlesBook().addCustomerNamed( PaulMcCartney );
    assertTimingIsCorrect(PaulMcCartney);
  }

  @Test public void testCanNotAddACustomerWithEmptyName() {
    CustomerBook customerBook = beatlesBook();
    assertThrowsLike(CustomerBook.CustomerNameCanNotBeEmpty,()->customerBook.addCustomerNamed( Vacio ));
    assertTrue( customerBook.isEmpty() );
    }

  @Test public void testCanNotRemoveAnInvalidCustomer() {
    CustomerBook customerBook = beatlesBookWithCustomerAdded(JohnLennon);
    assertThrowsLike(CustomerBook.CustomerNotFound,()->customerBook.removeCustomerNamed( PaulMcCartney ));
    assertCheckCustomersNumbersAndInclussion(1,JohnLennon,customerBook);
    }

  @Test public void testSuspendingACustomerShouldNotRemoveItFromCustomerBook() {
    CustomerBook customerBook = beatlesBookWithACostumerAddedAndThenSuspended(PaulMcCartney);
    assertCheckBookNumbers(0,1,1,PaulMcCartney,customerBook);
  }

  @Test public void testRemovingASuspendedCustomerShouldRemoveItFromCustomerBook() {
    CustomerBook customerBook = beatlesBookWithACostumerAddedAndThenSuspended(PaulMcCartney);
    customerBook.removeCustomerNamed( PaulMcCartney );
    assertCheckActiveAndSuspendedCustomers(0,0,customerBook);
    assertNumberOfCustumers(0,customerBook);
    assertFalse( customerBook.includesCustomerNamed( PaulMcCartney ) );
  }

  @Test public void testAddingNewCustomerDoesAffectSuspendedStatus() {
    CustomerBook customerBook = beatlesBookWithACostumerAddedAndThenSuspended(PaulMcCartney);
    customerBook.addCustomerNamed( RingoStar );
    assertCheckBookNumbers(1,1,2,PaulMcCartney,customerBook);
  }

  @Test public void testCanNotSuspendAnInvalidCustomer() {
    CustomerBook customerBook = beatlesBookWithCustomerAdded(JohnLennon);
    
    assertThrowsLike(CustomerBook.CanNotSuspendCustomer,()->customerBook.suspendCustomerNamed( GeorgeHarrison ));
    assertCheckCustomersNumbersAndInclussion(1,JohnLennon,customerBook);
    }

  @Test public void testCanNotSuspendAnAlreadySuspendedCustomer() {
    CustomerBook customerBook = beatlesBookWithCustomerAdded(JohnLennon);
    customerBook.suspendCustomerNamed( JohnLennon );

    assertThrowsLike(CustomerBook.CanNotSuspendCustomer,()->customerBook.suspendCustomerNamed( JohnLennon ));
    assertCheckCustomersNumbersAndInclussion(1,JohnLennon,customerBook);
    }
  
  
  private CustomerBook beatlesBook() {
		return new CustomerBook();
	}
  
  private void assertThrowsLike(String msg, Executable codeToRun) {
		assertEquals(msg, assertThrows(Exception.class, codeToRun).getMessage());
	}
  
  private CustomerBook beatlesBookWithCustomerAdded(String customerName) {
		CustomerBook customerBook = beatlesBook();
	    customerBook.addCustomerNamed( customerName );
		return customerBook;
	}
  
  private CustomerBook beatlesBookWithACostumerAddedAndThenSuspended(String costumerName) {
		CustomerBook customerBook = beatlesBookWithCustomerAdded(costumerName);
	    customerBook.suspendCustomerNamed( costumerName );
		return customerBook;
	}
  
  private void assertTimingIsCorrect(String customer) {
		long millisecondsBeforeRunning = System.currentTimeMillis();
	    beatlesBook().addCustomerNamed( customer );
	    long millisecondsAfterRunning = System.currentTimeMillis();
	    
	    assertTrue( millisecondsAfterRunning - millisecondsBeforeRunning < 50 );
	}
  
  private void assertNumberOfSuspendedCustomers(int numberOfSuspendedCustomers,CustomerBook customerBook) {
		assertEquals( numberOfSuspendedCustomers, customerBook.numberOfSuspendedCustomers() );
	}

	private void assertCheckNumberOfActiveCustomers(int activeCustomers, CustomerBook customerBook) {
		assertEquals( activeCustomers, customerBook.numberOfActiveCustomers() );
	}
	
	private void assertBookIncludesCustomer(String customer,CustomerBook customerBook) {
		assertTrue( customerBook.includesCustomerNamed( customer ) );
	}
	
	private void assertNumberOfCustumers(int numberOfCustumers, CustomerBook customerBook) {
		assertEquals( numberOfCustumers, customerBook.numberOfCustomers() );
	}
	
	private void assertCheckCustomersNumbersAndInclussion(int customersNumber, String customerName,CustomerBook customerBook) {
		assertNumberOfCustumers(customersNumber,customerBook);
	    assertBookIncludesCustomer(customerName,customerBook);
	}
	
	private void assertCheckActiveAndSuspendedCustomers(int activeCustomers, int suspendedCustomers,CustomerBook customerBook) {
		assertCheckNumberOfActiveCustomers(activeCustomers,customerBook);
	    assertNumberOfSuspendedCustomers(suspendedCustomers,customerBook);
	}
	
	private void assertCheckBookNumbers(int activeCustomers, int suspendedCustomers, int customers, String customerName,CustomerBook customerBook) {
		assertCheckActiveAndSuspendedCustomers(activeCustomers,suspendedCustomers,customerBook);
	    assertCheckCustomersNumbersAndInclussion(customers,customerName,customerBook);
	}
}