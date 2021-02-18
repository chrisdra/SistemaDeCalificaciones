package Modulo1.SistemaDeCalificaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Modulo1.SistemaDeCalificaciones.Servicios.PromedioServicioImp;

public class PromedioServicioTest {

	private static Logger logger = Logger.getLogger("Modulo1.SistemaDeCalificaciones.PromedioServicioTest");
	private final PromedioServicioImp promedioServicioImp = new PromedioServicioImp();
	
	@DisplayName("Test PromedioServicio")
	@Test
	
	void testPromedioServicio() {
		logger.info("info test calcular promedio");
		List<Double> valores = Arrays.asList(7.0, 7.0, 7.0, 2.0, 2.0);
		@SuppressWarnings("static-access")
		String respuestaServicio = promedioServicioImp.calcularPromedio(valores);
		assertEquals("5", respuestaServicio);
	}
}
