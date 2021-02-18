package Modulo1.SistemaDeCalificaciones.Modelos;

import java.util.List;

public class Alumno {
	
	private String rut;
	private String nombre;
	private String apellido;
	private String direccion;
	private List<Materia> materias;
	
	public Alumno(String rut, String nombre, String apellido, String direccion, List<Materia> materias) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.materias = materias;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Materia> getMaterias() {
		for(int i = 0; i < materias.size(); i++) {
			System.out.println(i + 1 + ". " + materias.get(i).getNombre());
		}
		return materias;
	}

	public List<Materia> getMaterias1() {
		return materias;
	}
	
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	@Override
	public String toString() {
		return "Datos Alumno \n \t RUT: " + rut + "\n \t Nombre: " + nombre + " \n \t Apellido: " + apellido + "\n \t Direccion: " + direccion
				+ " \n" + materias ; 
	}
}
