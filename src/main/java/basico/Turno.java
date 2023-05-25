package basico;

import java.time.LocalDateTime;

public class Turno {
	private int nroTurno;
	private LocalDateTime fechaDesde;
	private LocalDateTime fechaHasta;
	private Paciente asigandoA;
	private Terapista terapista;
	private Boolean tomado= false;
	
	public Turno() {
		
	}
	
	public Turno(LocalDateTime fechaDesde, LocalDateTime fechaHasta, Paciente asigandoA, Terapista terapista,
			Boolean tomado) {
		this();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.asigandoA = asigandoA;
		this.terapista = terapista;
		this.tomado = tomado;
	}
	
	

	public int getNroTurno() {
		return nroTurno;
	}

	public void setNroTurno(int nroTurno) {
		this.nroTurno = nroTurno;
	}

	public LocalDateTime getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(LocalDateTime fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public LocalDateTime getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDateTime fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Paciente getAsigandoA() {
		return asigandoA;
	}

	public void setAsigandoA(Paciente asigandoA) {
		this.asigandoA = asigandoA;
	}

	public Terapista getTerapista() {
		return terapista;
	}

	public void setTerapista(Terapista terapista) {
		this.terapista = terapista;
	}

	public Boolean getTomado() {
		return tomado;
	}

	public void setTomado(Boolean tomado) {
		this.tomado = tomado;
	}
	
}
