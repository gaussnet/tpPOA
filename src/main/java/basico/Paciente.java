package basico;

public class Paciente extends Usuario{
	
	private String patologia;

	public Paciente() {
		
	}
	
	public Paciente(String patologia) {
		this();
		this.patologia = patologia;
	}

	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}
	
	
	
	

}
