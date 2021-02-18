package Modulo1.SistemaDeCalificaciones;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Modulo1.SistemaDeCalificaciones.Modelos.Alumno;
import Modulo1.SistemaDeCalificaciones.Modelos.Materia;
import Modulo1.SistemaDeCalificaciones.Modelos.MateriaEnum;
import Modulo1.SistemaDeCalificaciones.Servicios.AlumnoServicio;

public class AlumnoServicioTest {
	
	//atributo alumnoservicio
	private AlumnoServicio alumnoServicio = new AlumnoServicio();
	
	//MOCK
	//private final AlumnoServicio alumnoServiciMock = mock(AlumnoServicio.class);
	
	@BeforeAll
	static void setup() {
		System.out.println("hola");
		
		//Atributo mtematicas
		MateriaEnum nombreMate = null;
		List<String> notasMate = new ArrayList<String>();
		Materia matematicas = new Materia();
		
		//atributo lenguaje
		MateriaEnum nombreLeng = null;
		List<String> notasLeng = new ArrayList<String>();
		Materia lenguaje = new Materia();
		
		//atributo alumno chris
		List<Materia> listaMaterias = new ArrayList<>();
		listaMaterias.add(lenguaje);
		listaMaterias.add(matematicas);
		Alumno chris = new Alumno("26-6", "chris", "herr", "lago", listaMaterias);
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		listaAlumnos.add(chris);
	}
	
	private static Logger logger = Logger.getLogger("Modulo1.SistemaDeCalificaciones.AlumnoServicioTest");
	
	@DisplayName("test de AlumnoServicioImp")
	@Test
	
	public void testCrearAlumno() {
		logger.info("info test de crear alumno");
		Materia materiaM = new Materia();
		List<Materia> materias = new ArrayList<Materia>();
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		listaAlumnos.add(new Alumno("18", "Maria", "Ruz", "Solar", materias));
		ArrayList<Alumno> respuestaCrearAlumnos = new ArrayList<Alumno>();
		respuestaCrearAlumnos.addAll(alumnoServicio.crearAlumno(listaAlumnos, materiaM));
		assertNotNull(respuestaCrearAlumnos);
	}
	
	@DisplayName("test de AlumnoServicioImp")
	@Test
	
	public void testAgregarMateria() {
		logger.info("info test de agregar materia");
		Scanner scan = new Scanner(System.in);
		List<Materia> materias = new ArrayList<Materia>();
		
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		listaAlumnos.add(new Alumno("18", "Maria", "Ruz", "Solar", materias));
		List<MateriaEnum> listaEnum = new ArrayList<MateriaEnum>();
		
		assertTrue(AlumnoServicio.agregarMateria(scan, listaAlumnos, listaEnum));
	}
	
	//metodo materiasPorAlumnos usando mock para verificar el funcionamiento de materiasporalumnos
	@DisplayName("test de listarAlumno")
	@Test
	
	public void testListarAlumno() {
		Scanner scan = null;
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		AlumnoServicio.listarAlumno(scan, listaAlumnos);
		assertNotNull(listaAlumnos);
	}
}
