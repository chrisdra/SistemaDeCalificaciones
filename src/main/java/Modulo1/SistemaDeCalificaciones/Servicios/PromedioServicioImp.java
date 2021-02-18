package Modulo1.SistemaDeCalificaciones.Servicios;

import java.text.DecimalFormat;
import java.util.List;

public class PromedioServicioImp {
	
	public static String calcularPromedio (List<Double> valores) {
		if(valores == null) {
			return null;
		} else {
			double suma = valores.stream().reduce((Double) 0.0, Double::sum);
			double promedio = (suma / valores.size());
			DecimalFormat df = new DecimalFormat("#.#");
			return df.format(promedio);
		}
	}
}
