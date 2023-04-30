package basico;

public class Usuario {

	private int dni;
	private String nombre;
	private String apellido;
	private String password;
	
	public Usuario() {
		
	}
	
	public Usuario(int dni, String nombre, String apellido, String password) {
		this();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
