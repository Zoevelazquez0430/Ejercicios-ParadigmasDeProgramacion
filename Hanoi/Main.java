package Hanoi;

public class Main {

	public static void main(String[] args) {
		System.out.println(stick());
		System.out.println(stick());
		System.out.println(stick());
		
		System.out.println(disc(5));
		System.out.println(disc(4));
		System.out.println(disc(3));
		
	}

	private static Disc disc(int size) {
		return new Disc(size);
	}

	private static Stick stick() {
		return new Stick();
	}

}
