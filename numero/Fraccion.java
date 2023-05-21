package numero;

public class Fraccion extends Numero{
	
	public Fraccion(int aNumerator, int aDenominator) {
		if (aNumerator == 0) {
			throw new IllegalArgumentException("Una fraccion no puede ser cero");
		}
		if (aDenominator == 1) {
			throw new IllegalArgumentException("Una fraccion no puede tener denominador 1 porque sino es un entero");
		}
		type = Fraccion;
		numerator = aNumerator;
		denominator = aDenominator;
	}
	
	public boolean equals(Object object) {
		return ((object != null) && this.getClass() == object.getClass())
				&& (this.numerator == ((Fraccion) object).numerator)
				&& (this.denominator == ((Fraccion) object).denominator);
	}
	
	public Numero negated() {
		return Numero.with(this.numerator * -1, this.denominator);
	}
	
	public Numero dividedBy(Numero aDivisor) {
		return aDivisor.dividedByFraccion( this );
	}
	
	public Numero dividedByFraccion(Fraccion aDividend) {
		return Numero.with( this.denominator * aDividend.numerator, this.numerator * aDividend.denominator);
	}
	
	public Numero dividedByEntero(Entero aDivisor) {
		return Numero.with( this.denominator * aDivisor.value, this.numerator );
	}
	
	public Numero substractedBy(Numero aSubtractor) { return aSubtractor.substractedByFraccion( this ); }
	
	public Numero substractedByEntero(Entero aAdder) { return Numero.with((aAdder.value * this.denominator - this.numerator ), this.denominator); }
	
	public Numero substractedByFraccion(Fraccion aSubtractor) {
		return Numero.with(((aSubtractor.numerator * this.denominator) - (this.numerator * aSubtractor.denominator)),
				this.denominator * aSubtractor.denominator);
	}

	public Numero multipliedByEntero( Entero aMultiplier) { return new Fraccion(this.numerator * aMultiplier.value, this.denominator); }
	
	public Numero multipliedByFraccion(Fraccion aMultiplier) { return Numero.with(this.numerator * aMultiplier.numerator, this.denominator * aMultiplier.denominator); }
	
	public Numero multipliedBy(Numero aMultiplier) { return aMultiplier.multipliedByFraccion( this ); }
	
	public Numero addedTo(Numero aAdder) { return aAdder.addedToFraccion( this ); }

	public Numero addedToEntero(Entero aAdder) { return new Fraccion(this.numerator + aAdder.value * this.denominator, this.denominator); }

	public Numero addedToFraccion(Fraccion aAdder) {
		return Numero.with((this.numerator * aAdder.denominator) + (aAdder.numerator * this.denominator),
				this.denominator * aAdder.denominator);
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public boolean isOne() {
		return false;
	}
}
