package Hanoi;

import java.util.ArrayList;
import java.util.List;

public class Stick {
	private ArrayList<Disc> discs = new ArrayList<Disc>();
	
	public Stick() {	
	}
	
	public Stick push(Disc newTop){
	if (!allowedMove(newTop)) {
		throw new RuntimeException( HanoiTest.FORBIDDEN_MOVE );
		} 
		discs.add(newTop);
		return this;
	}
	
	public Disc top() {
		return discs.get(discs.size() - 1);
	}
	
	public Disc pop() {
		return discs.remove(discs.size() - 1);
	}
	
	public static Stick with( List <Integer> sizes ) {
		Stick aStick = new Stick();
		sizes.stream().forEach(each -> aStick.push( new Disc( each ) ));
		
		return aStick;
		}
	
	public void print() {
		discs.forEach(each -> System.out.print(each.getRadio() + " "));
	}
	
	private boolean allowedMove(Disc newTop) {
		return discs.isEmpty() || newTop.stacksOn( top() );
	}
}