package Hanoi;

import java.util.Arrays;

public class Game {
	public static void main(String[] args) {
		Hanoi hanoi = new Hanoi( Stick.with(Arrays.asList( 5, 4, 3, 2, 1)), new Stick(), new Stick());
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.print();
		
		hanoi.moveLR();
		hanoi.print();
		
		hanoi.moveMR();
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.print();
		
		hanoi.moveRL();
		hanoi.print();
		
		hanoi.moveRM();
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.print();
		
		hanoi.moveLR();
		hanoi.print();
		
		hanoi.moveMR();
		hanoi.print();
		
		hanoi.moveML();
		hanoi.print();
		
		hanoi.moveRL();
		hanoi.print();
		
		hanoi.moveMR();
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.print();
		
		hanoi.moveLR();
		hanoi.print();
		
		hanoi.moveMR();
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.print();
		
		hanoi.moveRM();
		hanoi.moveRL();
		hanoi.print();
		
		hanoi.moveML();
		hanoi.moveRM();
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.moveLR();
		hanoi.moveMR();
		hanoi.moveML();
		hanoi.print();
		
		hanoi.moveRM();
		hanoi.moveRL();
		hanoi.moveML();
		hanoi.moveRM();
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.moveLR();
		hanoi.moveMR();
		hanoi.moveLM();
		hanoi.print();
		
		hanoi.moveRL();
		hanoi.moveRM();
		hanoi.print();
		
		hanoi.moveLM();
		hanoi.print();
		
		
	}
}