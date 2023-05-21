package Primos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Garage.Car;

public class Primos {

//@param max es el valor máximo
//@return Vector de números primos

public static int[] generarPrimos(int max) {
	int i, j;
	if (max >= 2) {

		int dim = max + 1; // Tamaño del array
		boolean[] esPrimo = new boolean[dim];
		
		Arrays.fill(esPrimo, true);

		esPrimo[0] = esPrimo[1] = false;

		descarteDeNoPrimos(dim, esPrimo);

		int cuenta = cantPrimos(dim, esPrimo);
		
		int[] primos = new int[cuenta];
		
		return nuevaListaPrimos(dim, esPrimo, primos);
		} 
		else { // max < 2
		return new int[0]; // Vector vacío
		}
		}

private static int[] nuevaListaPrimos(int dim, boolean[] esPrimo, int[] primos) {
	int i;
	int j;
	for (i = 0, j = 0; i < dim; i++) {
	if (esPrimo[i])
	primos[j++] = i;
	}
	return primos;
}

private static int cantPrimos(int dim, boolean[] esPrimo) {
	int i;
	int cuenta = 0;
	for (i = 0; i < dim; i++) {
	if (esPrimo[i])
	cuenta++;
	}
	return cuenta;
}

private static void descarteDeNoPrimos(int dim, boolean[] esPrimo) {
	int i;
	int j;
	for (i = 2; i < Math.sqrt(dim) + 1; i++) {
		if (esPrimo[i]) {
			for (j = 2 * i; j < dim; j += i)
				esPrimo[j] = false;
		}
	}
}
}