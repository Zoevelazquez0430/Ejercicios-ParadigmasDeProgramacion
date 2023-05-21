package GarageNew;

public class Car {
  private String plateNumber;
  
  public Car(String plateNumber ) {
      this.plateNumber = plateNumber;
  }
  
  public String getPlateNumber() {
      return plateNumber;
  }

  public boolean equals( Object o ) {
      return this == o ||
             (o != null && getClass() == o.getClass() && plateNumber.equals( Car.class.cast( o ).plateNumber ) );
  }

  public int hashCode() { return plateNumber.hashCode(); }
}