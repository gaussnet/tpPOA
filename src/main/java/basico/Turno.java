package basico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Turno {
	private int nroTurno;
	private LocalDateTime fechaDesde;
	private LocalDateTime fechaHasta;
	private Paciente asignadoA;
	private Terapista terapista;
	private Boolean tomado= false;
	
	public Turno() {
		
	}
	
	public Turno(LocalDateTime fechaDesde, LocalDateTime fechaHasta, Paciente asigandoA, Terapista terapista,
			Boolean tomado) {
		this();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.asignadoA = asigandoA;
		this.terapista = terapista;
		this.tomado = tomado;
	}
	
	/*
	public List<Object> obtenerValores() {
		List<Object> valores= new ArrayList<>();
		valores.add(nroTurno);
		valores.add(fechaDesde);
		valores.add(fechaHasta);
		valores.add(asignadoA);
		valores.add(terapista);
		valores.add(tomado);
		
		return valores;
	}
	*/

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

	public Paciente getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(Paciente asignadoA) {
		this.asignadoA = asignadoA;
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
