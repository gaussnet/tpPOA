package dao;

import basico.Usuario;
import exceptions.DAOException;

public interface UsuarioDAO {

	//public void crearUsuario(Usuario unUsuario) throws DAOException;
	
	//public void borrarUsuario(int unDni) throws DAOException;
	
	//public void modificarUsuario(Usuario unUsuario) throws DAOException;
	
	public Usuario autenticarUsuario(int unDni, String unPassword) throws DAOException;
	 
	//public Usuario obtenerUsuario(int unDni) throws DAOException;
}
