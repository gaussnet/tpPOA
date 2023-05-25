package dao;

import basico.Administrador;
import basico.Paciente;
import basico.Usuario;
import basico.UsuarioInt;
import exceptions.DAOException;

public interface UsuarioDAO {

	//public void crearUsuario(Usuario unUsuario) throws DAOException;
	
	public void crearPaciente(Paciente unPaciente) throws DAOException;
	
	public void crearAdmin(Administrador unAdmin) throws DAOException;
	
	public void borrarUsuario(int unDni) throws DAOException;
	
	public void modificarUsuario(UsuarioInt unUsuario) throws DAOException;
	
	public int autenticarUsuario(int unDni, String unPassword) throws DAOException;
	
	//public Paciente obtenerPaciente(int unDni, String unPassword) throws DAOException;
	 
	public UsuarioInt obtenerUsuario(int unDni) throws DAOException;
}
