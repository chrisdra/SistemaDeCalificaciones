package Modulo1.SistemaDeCalificaciones.Vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import Modulo1.SistemaDeCalificaciones.Modelos.Alumno;
import Modulo1.SistemaDeCalificaciones.Modelos.Materia;
import Modulo1.SistemaDeCalificaciones.Modelos.MateriaEnum;
import Modulo1.SistemaDeCalificaciones.Modelos.Notas;
import Modulo1.SistemaDeCalificaciones.Modelos.Texto;
import Modulo1.SistemaDeCalificaciones.Servicios.AlumnoServicio;
import Modulo1.SistemaDeCalificaciones.Servicios.ArchivoServicio;
import Modulo1.SistemaDeCalificaciones.Servicios.PromedioServicioImp;

public class Menu extends MenuTemplate {

	//atributos de la clase
	private AlumnoServicio alumnoServicio;
	private ArchivoServicio archivoServicio;
	private Scanner scanner;
	
	//constructor
	public Menu(Scanner scan, AlumnoServicio alumnoServicio, ArchivoServicio archivoServicio, Scanner scanner) {
		super(scan);
		this.alumnoServicio = alumnoServicio;
		this.archivoServicio = archivoServicio;
		this.scanner = scanner;
	}
	
	//metodo para calcular promedio por alumno //recibe por parametro el arraylist alumno
	public static List<Texto> promedioAlumno(List<Alumno> alumno){
		List<Texto> textoProm = new ArrayList<Texto>();
		String promM;
		//recorre a alumnos y despues recorre sus materias
		for(int i = 0; i < alumno.size(); i++) {
			for (int j = 0; j < alumno.get(i).getMaterias1().size(); j++) {
				//luego se llama al metodo calcular promedio que retorna el promedio, pasandole por parametro las nootas
				promM = (PromedioServicioImp.calcularPromedio(alumno.get(i).getMaterias1().get(j).getNotas()));
				//se almacena en una lista y luego se retorna
				Texto textoM = new Texto(alumno.get(i).getRut(),  alumno.get(i).getNombre(), alumno.get(i).getMaterias1().get(j), promM);
				textoProm.add(textoM);
			}
		} 
		//retorna ArrayList TextoProm
		return textoProm;
	}
	
	//metodo para cargar los datos desde el archivo CSV
	public static ArrayList<Notas> cargarDatos() throws IOException {
		Scanner scan = new Scanner(System.in);
		//se solicita el ingreso de la ruta del archivo
		System.out.println("------------------------------------Cargar Datos");
		System.out.println("Ingrese la ruta donde se encuentra el archivo Notas.csv");
		//se ingresa la ruta especifica del arhivo
		String ruta = scan.nextLine();
		//se corrobora que la ruta ingresada por el usuario sea la qu pide el programa
		File file = new File("Notas.csv");
		String path = file.getAbsolutePath();
		if(path.equals(ruta)) {
			System.out.println("Datos Cargados Correctamente");
			System.out.println("-----------------------------------------------------------");
			//SE ABRE el archivo y retiramos la informacion. 
			//El archivo debe estar en el proyecto
			BufferedReader br = new BufferedReader(new FileReader(file));
			//retornamos en un arreglo dinamico los datos
			return (ArrayList<Notas>) br.lines().map(line -> line.split(",")).map(values -> new Notas(values[0], values[1], values[2], values[3])).collect(Collectors.toList());
			//si la ruta es incorrecta nos indica que la ruta es erronea y nos retorna un Null	
		} else {
			System.out.println("Ruta incorrecta");
		}
		return null;
	}
	
	//metodo para revisar los rut y que no se repitan
	//recibe como parametro el arreglo notas
	public static Set<String> modificarRut(ArrayList<Notas> notas){
		Set<String> rutUnicos = new HashSet();
		//se recorre el arreglo notas
		for(int i = 0; i < notas.size(); i++) {
			//se incluyen a un set que sera retornado con rut sin repetir
			rutUnicos.add(notas.get(i).getRut());
		} 
		//retorna rut unico
		return rutUnicos;
	}
	
	//metodo para importar alumno
	//recibe por parametro el arreglo notas y rut unicos
	public static List<Alumno> importarAlumno(ArrayList<Notas> notas, Set<String> rutUnicos){
		List<Alumno> alumnos = new ArrayList<Alumno>();
		Set<String> nom = new TreeSet<String>();
		//iteramos los rut
		for(String rut : rutUnicos) {
			Materia matematicas = new Materia();
			Materia lenguaje = new Materia();
			Materia ciencias = new Materia();
			Materia historia = new Materia();
			Set<String> asig = new HashSet();
			
			List<Materia> materia = new ArrayList<Materia>();
			ArrayList<String> mat = new ArrayList<String>();
			ArrayList<String> leng = new ArrayList<String>();
			ArrayList<String> cienc = new ArrayList<String>();
			ArrayList<String> hist = new ArrayList<String>();
			ArrayList<Double> mat2 = new ArrayList<Double>();
			ArrayList<Double> leng2 = new ArrayList<Double>();
			ArrayList<Double> cienc2 = new ArrayList<Double>();
			ArrayList<Double> hist2 = new ArrayList<Double>();
			
			//iteramos arreglo notas
			for(int i = 0; i < notas.size(); i++) {
				//y las vamos ingresando una a una a su respectiva materia
				if(notas.get(i).getRut().equals(rut)) {
					nom.add(notas.get(i).getNombre());
					if(notas.get(i).getMaterias().contains("MATEMATICAS")) {
						//MIENTRAS AL MISMO TIEMPO VAMOS AGREGANDO LA MATERIA
						asig.add(notas.get(i).getMaterias());
						mat.add(notas.get(i).getNotas());
					}
					if(notas.get(i).getMaterias().contains("LENGUAJE")) {
						//MIENTRAS AL MISMO TIEMPO VAMOS AGREGANDO LA MATERIA
						asig.add(notas.get(i).getMaterias());
						leng.add(notas.get(i).getNotas());
					}
					if(notas.get(i).getMaterias().contains("CIENCIAS")) {
						//MIENTRAS AL MISMO TIEMPO VAMOS AGREGANDO LA MATERIA
						asig.add(notas.get(i).getMaterias());
						cienc.add(notas.get(i).getNotas());
					}
					if(notas.get(i).getMaterias().contains("HISTORIA")) {
						//MIENTRAS AL MISMO TIEMPO VAMOS AGREGANDO LA MATERIA
						asig.add(notas.get(i).getMaterias());
						hist.add(notas.get(i).getNotas());
					}
				}
			}
			//parseamos los datos notas a double para poder ingresarlo al arreglo alumno
			for(int j = 0; j < mat.size(); j++) {
				mat2.add(Double.parseDouble(mat.get(j)));
			}
			for(int j = 0; j < leng.size(); j++) {
				leng2.add(Double.parseDouble(leng.get(j)));
			}
			for(int j = 0; j < cienc.size(); j++) {
				cienc2.add(Double.parseDouble(cienc.get(j)));
			}
			for(int j = 0; j < hist.size(); j++) {
				hist2.add(Double.parseDouble(hist.get(j)));
			}
			//iteramos asignaturas y se ingresan al arreglo alumno
			for (String asign : asig) {
				if(asign.contains("MATEMATICAS")) {
					matematicas.setNombre(MateriaEnum.MATEMATICAS);
					matematicas.setNotas(mat2);
					materia.add(matematicas);
				}
				if(asign.contains("LENGUAJE")) {
					lenguaje.setNombre(MateriaEnum.LENGUAJE);
					lenguaje.setNotas(leng2);
					materia.add(lenguaje);
				}
				if(asign.contains("CIENCIAS")) {
					ciencias.setNombre(MateriaEnum.CIENCIAS);
					ciencias.setNotas(cienc2);
					materia.add(ciencias);
				}
				if(asign.contains("HISTORIA")) {
					historia.setNombre(MateriaEnum.HISTORIA);
					historia.setNotas(hist2);
					materia.add(historia);
				}
			}
			//datos ingresados a alumno
			Alumno alumno = new Alumno(rut, null, null, null, materia);
			alumnos.add(alumno);
		}
		//recorre nombre de arriba a abajo para asegurar datos
		int h = nom.size() - 1;
		Iterator i = nom.iterator();
		while (i.hasNext()) {
			alumnos.get(h).setNombre(i.next().toString());
			h--;
		}
		//retorna alumno
		return alumnos;
	}
	//menu para exportar datos txt
	public static void exportarDatos(List<Texto> textoProm) {
		Scanner scan = new Scanner(System.in);
		System.out.println("------------------------------------Exportar Datos");
		System.out.println("Ingresa la ruta en donde se encuentra el archivo promedios.txt");
		//la ruta debe ser completa ejemplo: C:\Users\Chris\eclipse-workspace\SistemaDeCalificaciones\Notas.csv
		String ruta = scan.nextLine();
		//se corrobora que la ruta ingresada por el ususario sea la que pide el programa
		File file = new File("promedios.txt");
		String path = file.getAbsolutePath();
		if(path.equals(ruta)) {
			FileWriter flwriter = null;
			try {
				//se escribe el txt
				flwriter = new FileWriter("promedios.txt");
				BufferedWriter bfwriter = new BufferedWriter(flwriter);
				for(Texto estudiante : textoProm) {
					bfwriter.write("Alumno: " + estudiante.getRut() + " - " + estudiante.getNombre() + "\n \t Materias " + estudiante.getNombreMateria().getNombre() + " - Promedio: " + estudiante.getPromedio() + "\n");
				}
				//cierre a la escritura
				bfwriter.close();
				System.out.println("Datos Exportados Correctamente");
				System.out.println("-----------------------------------------------------------");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(flwriter != null) {
					try {
						flwriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Ruta Incorrecta");
		}
	}
	//metood crear alumno
	public static List<Alumno> crearAlumno(List<Alumno> alumno){
		Scanner scan = new Scanner(System.in);
		System.out.println("---------------------------------------------Crear Alumno");
		ArrayList<Alumno> alumnoNuevo = new ArrayList<Alumno>();
		String rut;
		int a = 0;
		//se corrobora que el rut no exista previamente
		do {
			System.out.print("Ingrese Rut: ");
			rut = scan.nextLine();
			for(int i = 0; i < alumno.size(); i++) {
				if(alumno.get(i).getRut().contains(rut)) {
					a = 1;
					System.out.println("Rut ya Existe");
				} else {
					a = 2;
				}
			}
		} while (a == 1);
		
		//se solicitan los datos
		System.out.print("Ingrese Nombre: ");
		String nombre = scan.nextLine();
		System.out.print("Ingrese Apellido: ");
		String apellido = scan.nextLine();
		System.out.print("Ingrese Direccion: ");
		String direccion = scan.nextLine();
		//se ingresan los datos
		alumnoNuevo.add(new Alumno(rut, nombre, apellido, direccion, null));
		System.out.println("-----------------------------------------------------------");
		//retorna array alumno nuevo
		return alumnoNuevo;
	}
	//metodo agregar materia
	public static void agregarMateria(List<Alumno> alumno, List<MateriaEnum> materias) {
		System.out.println("-----------------------------------Agregar Materia");
		Scanner scan = new Scanner(System.in);
		String respuesta = null;
		
		System.out.print("Ingrese Rut del Alumno: ");
		String rut = scan.nextLine();
		//se itera alumno par a ingresar materias
		for(int i = 0; i < alumno.size(); i++) {
			if(alumno.get(i).getRut().equals(rut)) {
				System.out.println("Hola " + alumno.get(i).getNombre());
				ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
				Materia nuevaMateria = new Materia();
				Materia nuevaMateria1 = new Materia();
				Materia nuevaMateria2 = new Materia();
				Materia nuevaMateria3 = new Materia();
				String a = null;
				do {
					System.out.println("1. MATEMATICAS");
					System.out.println("2. LENGUAJE");
					System.out.println("3. CIENCIAS");
					System.out.println("4. HISTORIA");
					System.out.println("Selecciona una materia: ");
					int op = Integer.parseInt(scan.nextLine());
					//se selecciona la asignatura a ingresar
					switch (op) {
						case 1:
							 
							
							nuevaMateria.setNombre(MateriaEnum.MATEMATICAS);
							listaMaterias.add(nuevaMateria);
							System.out.println("Desea Agregar otra Materia");
							a = scan.nextLine();
							break;
						case 2:
							nuevaMateria1.setNombre(MateriaEnum.LENGUAJE);
							listaMaterias.add(nuevaMateria1);
							System.out.println("Desea Agregar otra Materia");
							a = scan.nextLine();
							break;
						case 3:
							nuevaMateria2.setNombre(MateriaEnum.CIENCIAS);
							listaMaterias.add(nuevaMateria2);
							System.out.println("Desea Agregar otra Materia");
							a = scan.nextLine();
							break;
						case 4:
							nuevaMateria3.setNombre(MateriaEnum.HISTORIA);
							listaMaterias.add(nuevaMateria3);
							System.out.println("Desea Agregar otra Materia");
							a = scan.nextLine();
							break;
					}
				} while (a.equals("s"));
				ArrayList<Materia> m2 = new ArrayList<Materia>();
				m2.addAll(listaMaterias);
				//se agregan las materias a el arreglo alumno
				alumno.get(i).setMaterias(m2);
				System.out.println("Materia Agregada");
			}
		}
	}
	//metodo agregar notas
	public static void agregarNotaPasoUno(List<Alumno> alumno, List<MateriaEnum> materias) throws IOException{
		double notas = 0;
		String a = null;
		int selec = 0;
		//lista que acumula las notas que quieren ingresar
		List<Double> listaNotas = new ArrayList<Double>();
		System.out.println("----------------------------------------Agregar Nota");
		Scanner scan = new Scanner(System.in);
		//se ingresa el rut del alumno que se quiere trabajar
		System.out.print("Ingresa Rut del Alumno: ");
		String rut = scan.nextLine();
		//se muestran las asignaturas que tiene el alumno
		System.out.println("\n");
		System.out.println("El Alumno  tiene las siguientes materias asignadas:");
		
		for(int i = 0; i < alumno.size(); i++) {
			//se revisa que el rut corresponda a un alumni
			if(alumno.get(i).getRut().equals(rut)) {
				if(alumno.get(i).getMaterias1() == null) {
					System.out.println("El Alumno No Tiene Materias Agregadas Aun \nPrimero Agregue una Materia");
					iniciarMenu();
				} else {
					alumno.get(i).getMaterias();
				}
				//seleccionamos la materia a la que queremos agregar notas
				System.out.println("Seleccionar Materia: ");
				selec = Integer.parseInt(scan.nextLine());
				//cada alumno tendra un orden distinto de notas
				//aca comienza la logica de agregar notas
				if(selec == 1) {
					do {
						//reviso la materia
						alumno.get(i).getMaterias1().get(0);
						System.out.print("Ingrese notas: ");
						notas = Double.parseDouble(scan.nextLine());
						//las notas ingresadas a esta materia se acumulan en la lista de notas
						listaNotas.add(notas);
						//se pregunta si desea agregar otra nota
						//el ciclo se repite mientras quiera agregar otra nota a la materia 1
						System.out.println("Desea Agregar otra Nota a " + alumno.get(i).getMaterias1().get(0).getNombre());
						System.out.println("Ingrese Y o N");
						a = scan.nextLine();
					} while (a.equals("Y"));
					//cuando termine de agregar notas estas se agregan a la materia seleccionada
					alumno.get(i).getMaterias1().get(0).setNotas(listaNotas);
					System.out.println("Nota agregara a " + alumno.get(i).getMaterias1().get(0));
				}
				//la misma logica continua repitiendose para las demas asignaturas
				if(selec == 2) {
					do {
						alumno.get(i).getMaterias1().get(1);
						System.out.println("Ingrese notas");
						notas = Double.parseDouble(scan.nextLine());
						listaNotas.add(notas);
						System.out.println("Desea Agregar otra Nota a " + alumno.get(i).getMaterias1().get(1).getNombre());
						System.out.println("Ingrese Y o N");
						a = scan.nextLine();
					} while (a.equals("Y"));
					alumno.get(i).getMaterias1().get(1).setNotas(listaNotas);
					System.out.println("Nota agregara a " + alumno.get(i).getMaterias1().get(1));
				}
				if(selec == 3) {
					do {
						alumno.get(i).getMaterias1().get(2);
						System.out.println("Ingrese notas");
						notas = Double.parseDouble(scan.nextLine());
						listaNotas.add(notas);
						System.out.println("Desea Agregar otra Nota a " + alumno.get(i).getMaterias1().get(2).getNombre());
						System.out.println("Ingrese Y o N");
						a = scan.nextLine();
					} while (a.equals("Y"));
					alumno.get(i).getMaterias1().get(2).setNotas(listaNotas);
					System.out.println("Nota agregara a " + alumno.get(i).getMaterias1().get(2));
				}
				if(selec == 4) {
					do {
						alumno.get(i).getMaterias1().get(3);
						System.out.println("Ingrese notas");
						notas = Double.parseDouble(scan.nextLine());
						listaNotas.add(notas);
						System.out.println("Desea Agregar otra Nota a " + alumno.get(i).getMaterias1().get(3).getNombre());
						System.out.println("Ingrese Y o N");
						a = scan.nextLine();
					} while (a.equals("Y"));
					alumno.get(i).getMaterias1().get(3).setNotas(listaNotas);
					System.out.println("Nota agregara a " + alumno.get(i).getMaterias1().get(3));
					System.out.println("---------------------------------------------------------------");
				}
			}
		}
	}
	//metodo listar alumnos
	@SuppressWarnings({"unchecked", "unlikely-arg-type"})
	public static void listarAlumno(List<Alumno> alumno) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> al = new ArrayList<String>();
		Map<String, Alumno> listar = new HashMap<>();
		//se iteran los alumnos del array alumno y se pasan al map
		for(int i = 0; i < alumno.size(); i++) {
			if(listar.containsValue(alumno.get(i).getRut())) {
				//no hace nada
			} else {
				String rut = alumno.get(i).getRut();
				listar.put(rut, alumno.get(i));
			}
		}
		System.out.println("----------------------------------------------------Listar Alumnos");
		System.out.println(listar.values());
		System.out.println("----------------------------------------------------------------");
	}
	//metodo iniciar menu
	public static int iniciarMenu() throws IOException {
		List<Alumno> alumno = new ArrayList<Alumno>();
		ArrayList<Notas> notas = new ArrayList<Notas>();
		Set<String> rutUnicos = new HashSet<>();
		List<Texto> textoProm = new ArrayList<Texto>();
		
		List<MateriaEnum> materias = null;
		
		Scanner scan = new Scanner(System.in);
		int opcion = 0;
		String res = null;
		//se selecciona la opcion indicada
		do {
			System.out.println("1. Crear Alumnos");
			System.out.println("2. Listar Alumnos");
			System.out.println("3. Agregar Materias");
			System.out.println("4. Agregar Notas");
			System.out.println("5. Cargar Datos");
			System.out.println("6. Exportar Datos");
			System.out.println("7. Salir");
			System.out.println("Selección:");
			res = scan.nextLine();
			if(res != null) {
				StringUtils.isNumeric(res);
				opcion = Integer.parseInt(res);
				switch (opcion) {
				 	case 1:
				 		alumno.addAll(Menu.crearAlumno(alumno));
				 		break;
				 	case 2:
				 		Menu.listarAlumno(alumno);
				 		break;
				 	case 3:
				 		Menu.agregarMateria(alumno, materias);
				 		break;
				 	case 4:
				 		Menu.agregarNotaPasoUno(alumno, materias);
				 		break;
				 	case 5:
				 		notas = Menu.cargarDatos();
				 		rutUnicos = Menu.modificarRut(notas);
				 		alumno = Menu.importarAlumno(notas, rutUnicos);
				 		break;
				 	case 6:
				 		textoProm = Menu.promedioAlumno(alumno);
				 		Menu.exportarDatos(textoProm);
				 		break;
				}
			}
		} while (opcion != 7);
		if(res == null) {}
		return 0;
	}
}