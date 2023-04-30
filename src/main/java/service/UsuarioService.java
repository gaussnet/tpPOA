package service;

import basico.Usuario;
import dao.UsuarioDAO;
import daoSqLiteImpl.UsuarioDAOSqLiteImpl;
import exceptions.DAOException;
import exceptions.ServicioException;

public class UsuarioService {
	
	public Usuario autenticarUsuario(int dni, String password) throws ServicioException {
    	Usuario usu;
    	UsuarioDAO dao=  new UsuarioDAOSqLiteImpl();
    	
    	try {
    		usu= dao.autenticarUsuario(dni, password);
    		
    	} catch (DAOException e) {
    		throw new ServicioException(e);
    	}
    	return usu;
    }

}
