package GarageNew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Garage {
  public static String MissingCar = "Missing car!";
  public static String TwinCars = "Twin Cars!";
  public static String NoSpaceAvailable = "No space available";
  public static int basicFee = 10;

  private int capacity;
  private List<Car> cars = new ArrayList();
  private Map<Car, Integer> members = new HashMap();
  private int fees = 0;

  public Garage( int size ) {
    this.capacity = size;
  }

  public boolean isEmpty() { return cars.isEmpty(); }
  public int getNumCars() { return cars.size(); }
  public boolean isFull() { return cars.size() == capacity; }

  public Garage parkCar( Car car ) {
    if (isFull()) { 
      throw new RuntimeException( NoSpaceAvailable );
    }
    if ( cars.contains( car ) ) {
      throw new RuntimeException( TwinCars );
    }

    fees += feeFor( car );
    cars.add( car );
    return this;
  }

  public Garage unparkCar( Car car ) {
    if ( !cars.remove( car ) ) {
      throw new RuntimeException( MissingCar );
    }
    return this;
  }

  public int feeFor(Car car) {
	  return members.getOrDefault(car, basicFee).intValue();
	}

  public int totalFees() {
    return fees;
  }

  public Garage associate( Car car, int fee ) {
    members.put( car, fee );
    return this;
  }

}