package service;

import java.util.List;

import basico.Terapista;
import basico.Turno;
import dao.TerapistaDAO;
import dao.TurnoDAO;
import daoSqLiteImpl.TerapistaDAOSqLiteImpl;
import daoSqLiteImpl.TurnoDAOSqLiteImpl;
import exceptions.DAOException;
import exceptions.ServicioException;

public class TerapistaService {

	public void crearTerapista(Terapista t) throws ServicioException {
		
		TerapistaDAO dao= new TerapistaDAOSqLiteImpl();
		try {
			dao.crearTerapista(t);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
		
	}
	
	public void eliminarTerapista(String nombre, String apellido) throws ServicioException {
		TerapistaDAO dao= new TerapistaDAOSqLiteImpl();
		TurnoDAO turnoDao= new TurnoDAOSqLiteImpl();
        List<Turno> listaTurnos;
		
		try {
			if(dao.obtenerIDTerapista(nombre, apellido) == 0) {
				throw new ServicioException("El terapista no existe");
			} 
			
			listaTurnos= turnoDao.obtenerTurnosTerapista(nombre, apellido);
			
			if(listaTurnos.size() > 0) {
        		Exception ex= new Exception("El terapista tiene turnos asignados");
        		throw new ServicioException(ex);
        	}
			
			dao.borrarTerapista(nombre, apellido);
			
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
	
	public void modificarTerapista(int idTerapista, String nombre, String apellido) throws ServicioException {
		TerapistaDAO dao= new TerapistaDAOSqLiteImpl();
		
		try {
			dao.modificarTerapista(idTerapista, nombre, apellido);
			
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
	
	public int buscarTerapista(String nombre, String apellido) throws ServicioException {
		TerapistaDAO dao= new TerapistaDAOSqLiteImpl();
		int idTerapista;
		try {
			idTerapista= dao.obtenerIDTerapista(nombre, apellido);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
		
		return idTerapista;
	}
	
	public List<String> listarTerapistas() throws ServicioException {
		TerapistaDAO dao= new TerapistaDAOSqLiteImpl();
		try {
			return dao.listarTerapistas();
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
}
