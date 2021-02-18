package Modulo1.SistemaDeCalificaciones.Modelos;

public class Texto {
	
	private String rut;
	private String nombre;
	private Materia nombreMateria;
	private String promedio;
	
	public Texto(String rut, String nombre, Materia nombreMateria, String promedio) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.nombreMateria = nombreMateria;
		this.promedio = promedio;
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
		nombre = nombre;
	}

	public Materia getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		nombreMateria = nombreMateria;
	}

	public String getPromedio() {
		return promedio;
	}

	public void setPromedio(String promedio) {
		nombre = promedio;
	}

	@Override
	public String toString() {
		return "Texto [rut=" + rut + ", Nombre=" + nombre + ", NombreMateria=" + nombreMateria + ", Promedio="
				+ promedio + "]";
	}
}
