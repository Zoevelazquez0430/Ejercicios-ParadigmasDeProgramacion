package Booleans;

public abstract class Boolean {
boolean value;

  static Boolean True() {
    return new True();
  }
 
  static Boolean False() {
    return new False();
  }  

  public boolean equals( Object other ) {
return other.getClass() == getClass( );
}

public abstract Boolean yy( Boolean v );

public abstract Boolean oo( Boolean v );

public abstract Boolean not();

}