package Hanoi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class HanoiTest {

	public static String FORBIDDEN_MOVE = "forbidden move";

	@Test
	public void testAddElementToNewStick() {
		assertEquals(disc(1), stick().push(disc(1)).top());
	}
	
	@Test
	public void testAddSecondElement() {
		Stick aStick = stick().push(disc(5)).push(disc(1));
		
		assertEquals(disc(1), aStick.pop());
		assertTopDiscCorrect(disc(5), aStick);
	}

	@Test
	public void testFailWhenAddingABiggerElement() {
		Stick aStick  = stick().push(disc(5));

		assertThrowsLike(FORBIDDEN_MOVE, ()->aStick.push(disc(10)) );
		
		assertTopDiscCorrect(disc(5), aStick);
	}
	
	@Test 
	public void testASmallDiscStacksOverBigDisc() {
	    assertTrue( checkDiscPiling(3,4) );
	}

	private boolean checkDiscPiling(int discSize1, int discSize2) {
		return disc(discSize1).stacksOn( disc(discSize2) );
	}
	
	@Test 
	public void testABigDoesNotStacksOverSmallDisc() {
	    assertFalse( checkDiscPiling(4,3)  );
	}
	
	@Test 
	public void testMover() {
		Hanoi hanoi = new Hanoi( stick(), stick(), stick().push( disc(3) ));
		
		hanoi.moveRL();
		   
		assertEquals( disc(3), hanoi.left().top() );
		
	}
	
	@Test
	public void TestMoverComplejo() {
		Hanoi hanoi = fullHanoi();
		
		hanoi.moveLM();
		
		assertEquals( disc(1), hanoi.middle().top() );
		assertEquals( disc ( 3 ), hanoi.left().top() );
	}
	
	@Test
	public void testStickVersatil() {
		Stick aStick = Stick.with( Arrays.asList( 4, 3, 2 ));
		
		assertCheckDiscsAreTheSame(2,aStick);
		assertCheckDiscsAreTheSame(3,aStick);
		assertCheckDiscsAreTheSame(4,aStick);
	}
	
	public void testMoveCompleto() {
		Hanoi hanoi = fullHanoi();
		
		hanoi.moveLR();
		hanoi.moveML();
		
		assertEquals( disc(1), hanoi.right().top());
		assertEquals( disc(2), hanoi.left().top());
		
	}
	
	public Disc disc(int size) {
		return new Disc(size);
	}
	
	private Stick stick() {
		return new Stick();
	}
	
	private void assertThrowsLike(String msg, Executable codeToRun) {
		assertEquals(msg, assertThrows(Exception.class, codeToRun).getMessage());
		}
	
	private void assertCheckDiscsAreTheSame(int discSize,Stick aStick) {
		assertEquals( disc( discSize ), aStick.pop());
	}
	
	private Hanoi fullHanoi() {
		return new Hanoi( Stick.with( Arrays.asList( 3, 1 )),
				                Stick.with( Arrays.asList( 4, 2 )),
				                Stick.with( Arrays.asList( 5 )));
	}
	

	private void assertTopDiscCorrect(Disc bigDisc, Stick aStick) {
		assertEquals(bigDisc, aStick.top());
	}
}