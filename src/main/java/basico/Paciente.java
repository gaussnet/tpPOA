package basico;

public class Paciente extends Usuario implements UsuarioInt{
	
	private String patologia;

	public Paciente() {
		
	}
	
	public Paciente(int dni, String nombre, String apellido, String password, String patologia) {
		super(dni, nombre, apellido, password);
		this.patologia = patologia;
	}

	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}
	
	
	
	

}
