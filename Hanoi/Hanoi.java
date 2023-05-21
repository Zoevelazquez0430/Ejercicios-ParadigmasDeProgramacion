package Hanoi;

public class Hanoi {
	
	private static String HANOIBASE = "----------------------------";
	public static String RIGHT2 = "Right: ";
	public static String MIDDLE = "Middle: ";
	public static String LEFT = "Left: ";
	public Stick left;
	public Stick middle;
	public Stick right;
	
	public Hanoi(Stick stickL, Stick stickC, Stick stickR) {
		this.left = stickL;
		this.middle = stickC;
		this.right = stickR;
	}
	
	 public void moveLM() {
		 mover(middle(),left());
	 }
	 
	 public void moveMR() {
		 mover(right(),middle());
	 }

	 public void moveRL() {
		 mover(left(),right());
	 }
	 
	 public void moveLR() {
		 mover(right(),left());
	 }
	 
	 public void moveML() {
		 mover(left(),middle());
	 }
	 
	 public void moveRM() {
		mover(middle(),right());
	 }
	 
	 public void print() {
		 System.out.print( LEFT );
		 left().print();
		 System.out.println();
		 System.out.print( MIDDLE );
		 middle().print();
		 System.out.println();
		 System.out.print( RIGHT2 );
		 right().print();
		 System.out.println();
		 System.out.println( HANOIBASE );
		 
	 }
	 public Stick left() { return left; }
	 public Stick middle() { return middle; }
	 public Stick right() { return right; }
	 
 	private Stick mover(Stick stick1, Stick stick2) {
		return stick1.push( stick2.pop() );
	}
}
//una función es estáatica cuando no está asociada a una instancia