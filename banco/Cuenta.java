package banco;

import java.util.ArrayList;
import java.util.List;


public class Cuenta {
	public static String ExtraccionMayorAlBalance = "Extraccion mayor al balance actual";
	
	public List<Integer> balance = new ArrayList<Integer>();
	public String report;

	public Cuenta depositar(int deposito) {
		balance.add(deposito);
		return this;
	}
	public Cuenta extraer(int extraccion) {
		if (balance() - extraccion < 0) {
            throw new RuntimeException(ExtraccionMayorAlBalance);
        }
        balance.add(extraccion * -1);
		return this;

	}
	public int balance() {
		int sum = balance.stream().mapToInt(Integer::intValue).sum();
		return sum;
	}
	public String reporte() {
	    StringBuilder report = new StringBuilder();
	    for (Integer amount : balance) {
	        if (amount > 0) {
	            report.append("Deposito: ").append(amount).append("\n");
	        } else {
	            report.append("Extraccion: ").append(-amount).append("\n");
	        }
	    }
	    return report.toString().trim();
	}

}
