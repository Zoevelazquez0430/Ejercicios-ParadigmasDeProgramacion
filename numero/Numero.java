package numero;

public abstract class Numero {
    public static String TipoDeNumeroNoSoportado = "Tipo de número no soportado";
	public static String UnaFraccionNoPuedeTenerDenominador1 = "Una fraccion no puede tener denominador 1 porque sino es un entero";
	public static String UnaFraccionNoPuedeSerCero = "Una fraccion no puede ser cero";
	public static String CanNotDivideByZero = "No se puede dividir por cero!!!!!!";
    public static String Entero = "Entero";
    public static String Fraccion = "Fraccion";
    
    public String type;
    public int value;
    public int numerator;
    public int denominator;

    public static Numero with( int aValue ) {
        return new Entero( aValue );
    }

    public static Numero with( int aDividend, int aDivisor ) {
        if (aDivisor == 0) {
            throw new IllegalArgumentException( CanNotDivideByZero );
        }

        if (aDividend == 0) {
            return with( 0 );
        }

        if (aDivisor < 0) {
            return with( -aDividend, -aDivisor );
        }

        int greatestCommonDivisor = Numero.greatestCommonDivisor( aDividend, aDivisor );
        int numerator = aDividend / greatestCommonDivisor;
        int denominator = aDivisor / greatestCommonDivisor;

        if (denominator == 1) {
            return with( numerator );
        }

        return new Fraccion( numerator, denominator );
    }
    
    public Numero multipliedBy(Numero numero) {
		return this.multipliedBy(numero);
	}
	
	public Numero addedTo( Numero numero) {
		return this.addedTo(numero);
	}
	
	public Numero substractedBy(Numero numero) {
		return this.substractedBy(numero);
	}
	
	public Numero dividedBy(Numero numero) {
		return this.dividedBy(numero);
	}

    	
	
	public abstract Numero dividedByEntero(Entero aDivisor);
	
	public abstract Numero dividedByFraccion(Fraccion aDivisor);
	
	public Numero negated() { return this.negated(); }
	
	public abstract Numero substractedByEntero(Entero aAdder);
	
	public abstract Numero substractedByFraccion(Fraccion aAdder);
	
	public abstract Numero addedToEntero(Entero aAdder);
	
	public abstract Numero addedToFraccion(Fraccion aAdder);
	
	public abstract boolean isZero();
	
	public abstract boolean isOne();
	
	public abstract Numero multipliedByEntero(Entero aMultiplier);
	
	public abstract Numero multipliedByFraccion(Fraccion aMultiplier);
	
	

    public Numero greatestCommonDivisorWith( int anEntero ) {
        if (type == Entero) {
            return Numero.with( greatestCommonDivisor( value, anEntero ) );
        }

        throw new UnsupportedOperationException( TipoDeNumeroNoSoportado );
    }

    public boolean isNegative() {
        if (type == Entero) {
            return value < 0;
        }
        if (type == Fraccion) {
            return denominator < 0;
        }
        throw new UnsupportedOperationException( TipoDeNumeroNoSoportado );
    }

    public String toString() {
        if (type == Entero) {
            return "" + value;
        }
        if (type == Fraccion) {
            return "" + numerator + "/" + denominator;
        }
        throw new UnsupportedOperationException( TipoDeNumeroNoSoportado );
    }

    public boolean equals( Object anObject ) {
        if (Numero.class.isInstance( anObject )) {
            Numero other = Numero.class.cast( anObject );
            if (type == other.type) {
                if (type == Entero) {
                    return value == other.value();
                } else if (type == Fraccion) {
                    return numerator * other.denominator() == denominator * other.numerator();
                }
            }
        }
        return false;
    }
    
    public Numero simplify() {
        if (type == Entero) {
            return this;
        } else if (type == Fraccion) {
            int gcd = greatestCommonDivisor(numerator, denominator);
            int newNumerator = numerator / gcd;
            int newDenominator = denominator / gcd;
            if (newDenominator == 1) {
            	return Numero.with(newNumerator);
            }
            else {
            return Numero.with(newNumerator, newDenominator);
            }
        }
        return this;
    }


    public int hashCode() {
        if (type == Entero) {
            return Integer.hashCode( value );
        }
        if (type == Fraccion) {
            return Double.hashCode( (double) numerator / (double) denominator );
        }
        return 0;
    }

    // accessors
    public int denominator() {  return denominator; }
    public int value() {        return value;       }
    public int numerator() {    return numerator;   }

    public static int greatestCommonDivisor( int a, int b ) {
        int temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}