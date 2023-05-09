package service;

import basico.Administrador;
import basico.Paciente;
import basico.Usuario;
import basico.UsuarioInt;
import dao.UsuarioDAO;
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
        try {
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
    	//Usuario usu;
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
