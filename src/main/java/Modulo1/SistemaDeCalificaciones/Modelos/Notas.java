package Modulo1.SistemaDeCalificaciones.Modelos;

public class Notas {

	private String rut;
	private String nombre;
	private String materias;
	private String notas;
	
	/**
	 * @param rut
	 * @param nombre
	 * @param materias
	 * @param notas
	 */
	
	public Notas(String rut, String nombre, String materias, String notas) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.materias = materias;
		this.notas = notas;
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

	public String getMaterias() {
		return materias;
	}

	public void setMaterias(String materias) {
		this.materias = materias;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "Notas [rut=" + rut + ", nombre=" + nombre + ", materias=" + materias + ", notas=" + notas + "]";
	}
	
}
