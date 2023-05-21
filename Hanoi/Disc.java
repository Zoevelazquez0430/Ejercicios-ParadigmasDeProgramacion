package Hanoi;

public class Disc {
	private int radio;
	
	public Disc( int radio) {
		this.radio = radio;
	}
	
	public int getRadio() {
		return radio;
	}
	
	public int hashCode() {
		return radio;
	}
	
	public boolean equals ( Object obj ) {
		return obj != null &&
				( this == obj ||
				 (getClass() == obj.getClass() &&
				  ((Disc)obj).radio == radio));
	}

	public boolean stacksOn(Disc anotherDisc) {
		return radio < anotherDisc.radio;
	}
	
}