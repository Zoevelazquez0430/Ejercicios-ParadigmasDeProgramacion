package GarageNew;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class GarageTest {

  @Test public void testNewParkingIsEmpty() {
    assertTrue( new Garage( 3 ).isEmpty() );
  }

  @Test public void testNewParkingHasNoMoney() {
    assertEquals( 0, new Garage( 3 ).totalFees() );
  }

  @Test public void testParkOneCar() {
    assertFalse( garageWithAlvinsCar( 3 ).isEmpty() );
  }

  @Test public void testOneCarFee() {
    assertEquals( Garage.basicFee, garageWithAlvinsCar( 3 ).totalFees() );
  }

  @Test public void testOneMemberCarFee() {
    Garage garage = new Garage( 3 );
    int alvinsFee = 15;

    Car alvins = alvinsCar();
    garage.associate( alvins, alvinsFee )
          .parkCar( alvins );
    assertEquals( alvinsFee, garage.totalFees() );
  }

  @Test public void testManyCarsFees() {
    Garage garage = new Garage( 3 );
    Car alvins = alvinsCar();
    int membershiptFee = 8;

    garage.associate( alvins, membershiptFee )
          .parkCar( alvins )
          .parkCar( simonsCar() );
    assertEquals( Garage.basicFee + membershiptFee, garage.totalFees() );
  }

  @Test public void testManyCarsMembershipFees() {
    Garage garage = new Garage( 3 );
    int alvinsMembershiptFee = 8;
    int simonsMembershiptFee = 7;

    garage.associate( alvinsCar(), alvinsMembershiptFee )
          .associate( simonsCar(), simonsMembershiptFee );

    garage.parkCar( alvinsCar() )
          .parkCar( simonsCar() );
    assertEquals( alvinsMembershiptFee + simonsMembershiptFee, garage.totalFees() );
  }

  @Test public void testInOutOneCar() {
    Garage garage = garageWithAlvinsCar( 3 );

    assertFalse( garage.isEmpty() );
    garage.unparkCar( alvinsCar() );
    
    assertTrue( garage.isEmpty() );
  }

  @Test public void testInOutOneCarFees() {
    Garage garage = garageWithAlvinsCar( 1 );

    assertFalse( garage.isEmpty() );

    garage.unparkCar( alvinsCar() );
    assertTrue( garage.isEmpty() );
    assertEquals( 10, garage.totalFees() );
  }

  @Test public void testUnparkAnEmptyLot() {
    assertThrowsLike( () -> new Garage( 3 ).unparkCar( theodoresCar() ), Garage.MissingCar );
  }

  @Test public void testFullLot() {
    assertTrue( garageWithAlvinsCar( 1 ).isFull() );
  }

  @Test public void testOverFillLot() {
    Garage garage = garageWithAlvinsCar( 1 );

    assertThrowsLike( () -> garage.parkCar( simonsCar() ), Garage.NoSpaceAvailable );
    assertEquals( 1, garage.getNumCars() );
  }
  
  @Test public void testRotateLot() {
    Garage garage = garageWithAlvinsCar( 1 )
                      .unparkCar( alvinsCar() )
                      .parkCar( simonsCar() );

    assertEquals( 1, garage.getNumCars() );
    assertEquals( 20, garage.totalFees() );
  }
  
  @Test public void testParkOneCarTwice() {
    Garage garage = garageWithAlvinsCar( 3 );

    assertThrowsLike( () -> garage.parkCar( alvinsCar() ), Garage.TwinCars );
    assertEquals( 1, garage.getNumCars() );
  }

  private void assertThrowsLike( Executable executable, String message ) {
    assertEquals( message,
                  assertThrows( Exception.class, executable ).getMessage() );
  }

  private Garage garageWithAlvinsCar( int size ) {
    return new Garage( size ).parkCar( alvinsCar() );
  }

  private Car alvinsCar() {       return new Car( "ABC123" );  }
  private Car simonsCar() {       return new Car( "DEF456" );  }
  private Car theodoresCar() {    return new Car( "ABC123" );  }

}