package dao;

import java.util.List;

//import basico.Terapista;
import basico.Turno;
import exceptions.DAOException;

public interface TurnoDAO {
	
	public void crearTurno(String fechaDesde, String fechaHasta, String nombreTerapista, String apellidoTerapista) throws DAOException;
	
	public List<Turno> obtenerTurnos(String fechaDesde, String fechaHasta) throws DAOException;
	
	public Turno obtenerTurno(int nroTurno) throws DAOException;
	
	public List<Turno> obtenerTurnosTerapista(String nombreTerapista, String apellidoTerapista, String fechaInicioString, String fechaFinString) throws DAOException;
	
	public List<Turno> obtenerTurnosTerapista(String nombreTerapista, String apellidoTerapista, String fechaInicioString) throws DAOException;
	
	public List<Turno> obtenerTurnosTerapista(String nombreTerapista, String apellidoTerapista) throws DAOException;
	
	public List<Turno> obtenerTurnosPaciente(int dniPaciente, String fechaDesde) throws DAOException;
	
	public List<Turno> obtenerTurnosPaciente(int dniPaciente) throws DAOException;
	
	public void borrarTurno(int nroTurno) throws DAOException;
	
	public void liberarTurno(int nroTurno) throws DAOException;
	
	public void marcarTomado(int nroTurno) throws DAOException;
	
	public void asignarTurno(int nroTurno, int dniPaciente) throws DAOException;
	
	public List<Turno> obtenerTurnosHistoricoTomadoPaciente(int dniPaciente, String fechaDesde, String fechaHasta) throws DAOException;

}
