package codigorepetido;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Garage.Car;

public class CustomerBook {
  public static String CanNotSuspendCustomer = "Cannot suspend customer";
  public static String CustomerNotFound = "Customer not found";
  public static String CustomerAlreadyExists = "customer already exists!!!!!!";
  public static String CustomerNameCanNotBeEmpty = "customer name cannot be empty!!!!!!";
  
  private List<String> active;
  private List<String> suspended;

  public CustomerBook() {
    active = new ArrayList();
    suspended = new ArrayList();
  }

  public boolean includesCustomerNamed( String s ) {
    return active.contains( s ) || suspended.contains( s );
  }

  public boolean isEmpty() { 
    return active.isEmpty() && suspended.isEmpty(); 
  }

  public void addCustomerNamed( String s ) {
    if (s.isEmpty()) { 
      throw new RuntimeException( CustomerNameCanNotBeEmpty ); 
    }
    
    if (includesCustomerNamed(s)) {
      throw new RuntimeException( CustomerAlreadyExists );
    }
    active.add( s );
  }

  public int numberOfActiveCustomers() {
    return active.size();
  }

  public int numberOfCustomers() {
    return active.size() + suspended.size();
  }

  public int numberOfSuspendedCustomers() {
    return suspended.size();
  }

  public void removeCustomerNamed( String aName ) {
        if(customerIsInTheRecord(aName)) {
        	return;
        }
    throw new RuntimeException( CustomerNotFound );
  }

  public void suspendCustomerNamed( String aName ) {
    if (!active.contains( aName )) { 
      throw new RuntimeException( CanNotSuspendCustomer );
    }
    active.remove( aName );
    suspended.add( aName );
  }
  
  
  private boolean customerIsIn(List list,String aName) {
		return list.removeIf((each)-> aName.equals(each));
	}
  
  private boolean customerIsInTheRecord(String aName) {
		return customerIsIn(active,aName)||customerIsIn(suspended,aName);
	}

 

}
