package basico;

public class Administrador extends Usuario implements UsuarioInt{

	public Administrador() {
		super();
		
	}

	public Administrador(int dni, String nombre, String apellido, String password) {
		super(dni, nombre, apellido, password);
		
	}
	
	public String getPatologia() {
		return null;
	}
	
	public void setPatologia(String patologia) {
		
	}

	
}
