package service;

import java.util.List;

import basico.Administrador;
import basico.Paciente;
import basico.Turno;
//import basico.Usuario;
import basico.UsuarioInt;
import dao.UsuarioDAO;
import dao.TurnoDAO;
import daoSqLiteImpl.TurnoDAOSqLiteImpl;
import daoSqLiteImpl.UsuarioDAOSqLiteImpl;
import exceptions.DAOException;
import exceptions.ServicioException;

public class UsuarioService {
	
	public void crearPaciente(Paciente p) throws ServicioException {
        UsuarioDAO dao = new UsuarioDAOSqLiteImpl();
        try {
            dao.crearPaciente(p);
        } catch (DAOException e) {
            throw new ServicioException(e);
        }
    }
	
	public void crearAdmin(Administrador unAdmin) throws ServicioException {
        UsuarioDAO dao = new UsuarioDAOSqLiteImpl();
        try {
            dao.crearAdmin(unAdmin);
        } catch (DAOException e) {
            throw new ServicioException(e);
        }
    }
	
	public void actualizarUsuario(UsuarioInt u) throws ServicioException {
        UsuarioDAO dao = new UsuarioDAOSqLiteImpl();
        try {
        	dao.modificarUsuario(u);
        } catch (DAOException e) {
        	throw new ServicioException(e);
        }
        
    }
	
	public void eliminarUsuario(int dni) throws ServicioException {
        UsuarioDAO dao = new UsuarioDAOSqLiteImpl();
        TurnoDAO turnoDao= new TurnoDAOSqLiteImpl();
        List<Turno> listaTurnos;
        
        try {
        	listaTurnos= turnoDao.obtenerTurnosPaciente(dni);
        	if(listaTurnos.size() > 0) {
        		Exception ex= new Exception("El usuario tiene turnos asignados");
        		throw new ServicioException(ex);
        	}
        	
        	dao.borrarUsuario(dni);
        	
        } catch (DAOException e) {
        	throw new ServicioException(e);
        }
        
    }
	
	public UsuarioInt buscarUsuario(int dni) throws ServicioException {
    	UsuarioInt usu;
    	UsuarioDAO dao=  new UsuarioDAOSqLiteImpl();
    	
    	try {
    		usu=dao.obtenerUsuario(dni);
    	} catch (DAOException e) {
    		throw new ServicioException(e);
    	}
    	return usu;
    }
	
	public int autenticarUsuario(int dni, String password) throws ServicioException {
		int rol;
		
    	UsuarioDAO dao=  new UsuarioDAOSqLiteImpl();
    	
    	try {
    		rol= dao.autenticarUsuario(dni, password);
    		
    	} catch (DAOException e) {
    		throw new ServicioException(e);
    	}
    	return rol;
    }

}
