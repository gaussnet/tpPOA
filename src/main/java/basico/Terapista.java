package basico;

public class Terapista {
	private String nombre;
	private String apellido;
	private String turno;
	
	public Terapista() {
		
	}
	
	public Terapista(String nombre, String apellido, String turno) {
		this();
		this.nombre = nombre;
		this.apellido = apellido;
		this.turno= turno;
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
	
	

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String toString() {
		return nombre + " " + apellido;
	}

}
