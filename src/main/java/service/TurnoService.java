package service;

import exceptions.DAOException;
import exceptions.ServicioException;
import utilidades.FechaUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
//import java.time.ZoneId;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import basico.Paciente;
import basico.Terapista;
import basico.Turno;
import dao.TurnoDAO;
import daoSqLiteImpl.TurnoDAOSqLiteImpl;

public class TurnoService {
	
	public void crearTurno(LocalDateTime fechaInicio, String nombreTerapista, String apellidoTerapista) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		
		try {
			
			LocalDateTime fechaActual= LocalDateTime.now();
			
			if(fechaInicio.isBefore(fechaActual)) {
				Exception ex= new Exception("La fecha debe ser posterior a la actual");
				throw new ServicioException(ex);
			}
			
			if(FechaUtil.esDiaHabil(fechaInicio)) {
				String fechaDesdeString= FechaUtil.localDateTimeToString(fechaInicio);
				String fechaHastaString= FechaUtil.localDateTimeToString(fechaInicio.plusMinutes(30));
				
				dao.crearTurno(fechaDesdeString, fechaHastaString, nombreTerapista, apellidoTerapista);
			} else {
				Exception ex= new Exception("Es un día no habil");
				throw new ServicioException(ex);
			}
			 
			
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}

	public List<Turno> buscarTurnos(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		List<Turno> listaTurnos;
		
		try {
			
			String fechaInicioString= FechaUtil.localDateTimeToString(fechaInicio);
			String fechaFinString= FechaUtil.localDateTimeToString(fechaFin);
	
			listaTurnos= dao.obtenerTurnos(fechaInicioString, fechaFinString);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
		
		return listaTurnos;
	}
	
	public List<Turno> buscarTurnoTerapista(String nombreTerapista, String apellidoTerapista) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		List<Turno> listaTurnos;
		
		try {
			String fechaInicioString= FechaUtil.localDateTimeToString(LocalDateTime.now());
			String fechaFinString= FechaUtil.localDateTimeToString(FechaUtil.obtenerUltimoDiaSemana());
			
			//System.out.println(fechaInicioString + " " + fechaFinString);
			listaTurnos= dao.obtenerTurnosTerapista(nombreTerapista, apellidoTerapista, fechaInicioString, fechaFinString);
			
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
		
		return listaTurnos;
	}
	
	public void eliminarTurno(int nroTurno) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		
		try {
			dao.borrarTurno(nroTurno);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
	
	//Por administrador
	public void liberarTurno(int nroTurno, LocalDateTime fechaInicio) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		
		try {
			LocalDateTime fechaActual= LocalDateTime.now();
			
			if(fechaInicio.isBefore(fechaActual.minusHours(6))) {
				Exception ex= new Exception("El turno es anterior a la fecha actual");
				throw new ServicioException(ex);
			}
			dao.liberarTurno(nroTurno);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
	
	public void marcarTomado(int nroTurno, LocalDateTime fechaInicio) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		
		try {
			LocalDateTime fechaActual= LocalDateTime.now();
			
			if(fechaInicio.isAfter(fechaActual.plusHours(6))) {
				Exception ex= new Exception("El turno es posterior a la fecha actual");
				throw new ServicioException(ex);
			}
			
			dao.marcarTomado(nroTurno);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
	
	public void asignarTurno(int nroTurno, Paciente paciente) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		List<Turno> listaTurnos;
		
		LocalDateTime fechaActual= LocalDateTime.now();
		String fechaActualString= FechaUtil.localDateTimeToString(fechaActual);
		
		try {
			
			listaTurnos= dao.obtenerTurnosPaciente(paciente.getDni(), fechaActualString);
			
			if(listaTurnos.size() >= 2) {
				Exception ex= new Exception("No puede reservar mas de dos turnos");
				throw new ServicioException(ex);
			}
			
			//dao.asignarTurno(nroTurno, paciente.getNombre(), paciente.getApellido());
			dao.asignarTurno(nroTurno, paciente.getDni());
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
	
	public List<Turno> obtenerTurnosPaciente(Paciente paciente) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		List<Turno> listaTurnos;
		
		LocalDateTime fechaActual= LocalDateTime.now();
		String fechaActualString= FechaUtil.localDateTimeToString(fechaActual);
		
		try {
			listaTurnos= dao.obtenerTurnosPaciente(paciente.getDni(), fechaActualString);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
		
		return listaTurnos;
	}
	
	//Por paciente
	public void liberarTurnoPaciente(int nroTurno, LocalDateTime fechaInicio) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		
		try {
			LocalDateTime fechaActual= LocalDateTime.now();
			
			if(fechaInicio.getDayOfMonth() == fechaActual.getDayOfMonth()) {
				Exception ex= new Exception("No puede cancelar un turno el mismo día");
				throw new ServicioException(ex);
			}
			dao.liberarTurno(nroTurno);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
	
	public List<Turno> obtenerTurnosHistoricoTomadoPaciente(Paciente paciente) throws ServicioException {
		TurnoDAO dao= new TurnoDAOSqLiteImpl();
		List<Turno> listaTurnos;
		
		LocalDateTime fechaActual= LocalDateTime.now();
		String fechaActualString= FechaUtil.localDateTimeToString(fechaActual);
		
		LocalDateTime fechaInicio= fechaActual.minusMonths(1);
		String fechaInicioString= FechaUtil.localDateTimeToString(fechaInicio);
		
		try {
			listaTurnos= dao.obtenerTurnosHistoricoTomadoPaciente(paciente.getDni(), fechaInicioString, fechaActualString);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
		
		return listaTurnos;
	}
	
	/*
	public void exportarHistorial(List<Turno> listaTurnos) throws ServicioException {
		Workbook libro= new XSSFWorkbook();
		Sheet hoja= libro.createSheet("Historial turnos");
		
		try {
			OutputStream output= new FileOutputStream("historicoTurnos.xlsx");
			libro.write(output);
			
			libro.close();
			output.close();
		} catch (IOException e) {
			//Exception e= new Exception("No se pudo exportar el historial");
            throw new ServicioException(e);
        }
	}
	*/
}
