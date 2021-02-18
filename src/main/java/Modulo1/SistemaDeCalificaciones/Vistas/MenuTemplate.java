package Modulo1.SistemaDeCalificaciones.Vistas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

import Modulo1.SistemaDeCalificaciones.Modelos.Alumno;
import Modulo1.SistemaDeCalificaciones.Modelos.MateriaEnum;
import Modulo1.SistemaDeCalificaciones.Modelos.Notas;

public class MenuTemplate {
	
	Scanner scan = new Scanner(System.in);
	
	public static final int LIMITE_INFERIOR_NUMEROS = 49;
	public static final int LIMITE_SUPERIOR_NUMEROS = 55;
	
	public static boolean validaNumeros(String cadena) {
		boolean estado = true;
		for(int i = 0; i < cadena.length(); i++) {
			if(cadena.charAt(i) < LIMITE_INFERIOR_NUMEROS || cadena.charAt(i) > LIMITE_SUPERIOR_NUMEROS) {
				estado = false;
				break;
			}
		}
		return estado;
	}
	
	public MenuTemplate(Scanner scan) {
		super();
		this.scan = scan;
	}
	
	public static ArrayList<Notas> cargarDatos () throws IOException {
		return null;
	}
	
	public static void exportarDatos() {}
	
	public static ArrayList<Alumno> crearAlumno (){
		return null;
	}
	
	public static void agregarMateria () {}
	
	public static void agregarNotaPasoUno () throws IOException {}
	
	public static void listarAlumnos () {}
	
	public static void terminarPrograma () {}
	
	public static int iniciarMenu (ArrayList<Alumno> alumno, List<MateriaEnum> materias) throws IOException {
		
		Scanner scan = new Scanner(System.in);
		int opcion = 0;
		String res = null;
		
		do {
			System.out.println("1. Crear Alumnos");
			System.out.println("2. Listar Alumnos");
			System.out.println("3. Agregar Materias");
			System.out.println("4. Agregar Notas");
			System.out.println("5. Cargar Datos");
			System.out.println("6. Exportar Datos");
			System.out.println("7. Salir");
			System.out.println("Seleccion: ");
			
			res = scan.nextLine();
			if(res != null) {
				StringUtils.isNumeric(res);
				opcion = Integer.parseInt(res);
				switch(opcion) {
					case 1:
						alumno.addAll(Menu.crearAlumno(alumno));
						System.out.println();
						break;
					case 2:
						//Menu.listarAlumnos(listar);
						break;
					case 3:
						Menu.agregarMateria(alumno, materias);
						//System.out.println(alumno);
						break;
					case 4:
						Menu.agregarNotaPasoUno(alumno, materias);
						break;
					case 5:
						break;
					case 6:
						break;
				}	
			}
		} while (opcion != 7);
		if (res == null) {}
		return 0;
	}
	@Override
	public String toString() {
		return "MenuTemplate [scan=" + scan + "]";
	}
}
