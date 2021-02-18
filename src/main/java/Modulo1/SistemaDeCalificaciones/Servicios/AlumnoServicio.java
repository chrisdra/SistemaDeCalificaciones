package Modulo1.SistemaDeCalificaciones.Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Modulo1.SistemaDeCalificaciones.Modelos.Alumno;
import Modulo1.SistemaDeCalificaciones.Modelos.Materia;
import Modulo1.SistemaDeCalificaciones.Modelos.MateriaEnum;

public class AlumnoServicio {

	//se crea atributo listaAlumnos de tipo Map que simula almacenamiento
	private Map<String, Alumno> listaAlumnos = new HashMap<String, Alumno>();
	private ArrayList<Alumno> alumno = null;
	private List<MateriaEnum> materias = null;
	
	public Map<String, Alumno> getListaAlumnos() {
		return listaAlumnos;
	}
	public void setListaAlumnos(Map<String, Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
	public ArrayList<Alumno> getAlumno() {
		return alumno;
	}
	public void setAlumno(ArrayList<Alumno> alumno) {
		this.alumno = alumno;
	}
	public List<MateriaEnum> getMaterias() {
		return materias;
	}
	public void setMaterias(List<MateriaEnum> materias) {
		this.materias = materias;
	}
	
	@SuppressWarnings("null")
	//metodo crearAlumno el cual recibe parametro alumno
	public ArrayList<Alumno> crearAlumno(ArrayList<Alumno> alumno, Materia m1){
		
		List<Materia> materias = new ArrayList<Materia>();
		ArrayList<Alumno> alumnoNuevo = new ArrayList<Alumno>();
		//Scanner scan = new Scanner(System.in);
		
		String rut = "asdasdasd";
		int a = 2;
		do {
			System.out.println("Ingrese Rut del alumno nuevo (sin punto ni guion)");
			rut = "asdasdasd";
			System.out.println(alumno.size());
			for(int i = 0; i < alumno.size(); i++) {
				if(alumno.get(i).getRut().contains(rut)) {
					a = 1;
					System.out.println("Rut Ya Existe");
				} else {
					a = 2;
				}
			}
		} while (a == 1);
		System.out.println("Ingrese Nombre Alumno Nuevo");
		String nombre = "a";
		System.out.println("Ingrese Apellido Alumno Nuevo");
		String apellido = "b";
		System.out.println("Ingrese Direccion Alumno Nuevo");
		String direccion = "c";
		
		if(rut.equals(null) || nombre.equals(null) || apellido.equals(null) || direccion.equals(null)) {
			return null;
		} else {
			alumnoNuevo.add(new Alumno(rut, nombre, apellido, direccion, materias));
			return alumnoNuevo;
		}
	}
	
	@SuppressWarnings("null")
	//crear metodo agregarMateria, recibe rut alumno de tipo string
	public static Boolean agregarMateria(Scanner scan, ArrayList<Alumno> alumno, List<MateriaEnum> materias) {
		System.out.println("-----------------------------------Agregar Materia");
		System.out.println("Ingrese Rut del Alumno");
		String rut = "asdasdasd";
		for(int i = 0; i < alumno.size(); i++) {
			if(alumno.get(i).getRut().equals(rut)) {
				System.out.println("Hola " + alumno.get(i).getNombre());
				ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
				Materia nuevaMateria = new Materia();
				Materia nuevaMateria1 = new Materia();
				Materia nuevaMateria2 = new Materia();
				Materia nuevaMateria3 = new Materia();
				String a = "1";
				do {
					System.out.println(" 1. MATEMATICAS \n 2. LENGUAJE \n 3. CIENCIAS \n 4. HISTORIA");
					System.out.println("Selecciona una materia:");
					int op = 1;
					switch(op) {
						case 1:
							nuevaMateria.setNombre(MateriaEnum.MATEMATICAS);
							listaMaterias.add(nuevaMateria);
							System.out.println("Desea Agregar otra Materia (S o N)");
							break;
						case 2:
							nuevaMateria1.setNombre(MateriaEnum.LENGUAJE);
							listaMaterias.add(nuevaMateria1);
							System.out.println("Desea Agregar otra Materia (S o N)");
							break;
						case 3:
							nuevaMateria2.setNombre(MateriaEnum.CIENCIAS);
							listaMaterias.add(nuevaMateria2);
							System.out.println("Desea Agregar otra Materia (S o N)");
							break;
						case 4:
							nuevaMateria3.setNombre(MateriaEnum.HISTORIA);
							listaMaterias.add(nuevaMateria3);
							System.out.println("Desea Agregar otra Materia (S o N)");
							break;
					}
				} while (a.equals("S"));
				ArrayList<Materia> m2 = new ArrayList<Materia>();
				m2.addAll(listaMaterias);
				alumno.get(i).setMaterias(m2);
				System.out.println("Materia Agregada");
			}
		}
		Boolean validar = true;
		return validar;
	}
	
	//crear metodo materiaPorAlumnos retorna list, recibe rutAlumno de tipo string
	public static List<String> materiasPorAlumno(String rutAlumno){
		List<String> materias = null;
		return materias;
	}
	
	//se crea metodo para listar alumnos el cual retorna un Map<String, alumno>
	public static void listarAlumno(Scanner scan, ArrayList<Alumno> alumno) {
		ArrayList<String> al = new ArrayList<String>();
		Map<String, Alumno> listar = new HashMap<>();
		for(int i = 0; i < alumno.size(); i++) {
			if(listar.containsValue(alumno.get(i).getRut())) {
			} else {
				String rut = alumno.get(i).getRut();
				listar.put(rut, alumno.get(i));
				for(int j = 0; j < listar.size(); j++) {
					System.out.println("----------------------------------------------------Listar Alumnos");
					System.out.println(listar.values());
					System.out.println("----------------------------------------------------------------");
				}
			}
		}
	}
	@Override
	public String toString() {
		return "AlumnoServicio [listaAlumnos = " + listaAlumnos + "]";
	}
}
