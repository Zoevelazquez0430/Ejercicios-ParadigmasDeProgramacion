package numero;

public class Entero extends Numero{
	
	public Entero(int aValue) {
		type = Entero;
		value = aValue;
	}
	
	public boolean equals(Object object) {
		return ((object != null) && this.getClass() == object.getClass()) && (this.value == ((Entero) object).value);
	}
	
	public Numero negated() {
		return new Entero(value * -1);
	}
	
	public Numero dividedBy(Numero aDivisor) {
		return aDivisor.dividedByEntero( this );
	}
	
	public Numero dividedByFraccion(Fraccion aDivisor) {
		return Numero.with( aDivisor.numerator, this.value * aDivisor.denominator );
	}
	
	public Numero dividedByEntero(Entero aDivisor) {
		return Numero.with( aDivisor.value, this.value );
	}
	
	public Numero substractedBy(Numero aSubtractor) { return aSubtractor.substractedByEntero( this ); }
	
	public boolean isZero() { return this.value == 0; }
	
	public boolean isOne() { return this.value == 1; }
	
	public Numero multipliedByEntero( Entero aMultiplier) { return new Entero(this.value * aMultiplier.value); }
	
	public Numero multipliedByFraccion(Fraccion aMultiplier) { return Numero.with(this.value * aMultiplier.numerator, aMultiplier.denominator); }
	
	public Numero multipliedBy(Numero aMultiplier) { return aMultiplier.multipliedByEntero( this ); }
	
	public Numero addedTo(Numero aAdder) { return aAdder.addedToEntero(this); }

	public Numero addedToEntero(Entero aAdder) { return new Entero(this.value + aAdder.value); }

	public Numero addedToFraccion(Fraccion aAdder) { return Numero.with(this.value * aAdder.denominator + aAdder.numerator, aAdder.denominator); }

	@Override
	public Numero substractedByEntero(Entero aAdder) {
		return new Entero(-1*(this.value - aAdder.value)); 
	}

	@Override
	public Numero substractedByFraccion(Fraccion aAdder) {
		 return Numero.with(( aAdder.numerator - this.value * aAdder.denominator ), aAdder.denominator); 
	}
}

