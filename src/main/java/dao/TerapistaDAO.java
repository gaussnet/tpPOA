package dao;

import java.util.List;

import basico.Terapista;
import exceptions.DAOException;

public interface TerapistaDAO {
	public void crearTerapista(Terapista unTerapista) throws DAOException;
	
	public List<String> listarTerapistas() throws DAOException;
	
	public int obtenerIDTerapista(String nombre, String apellido)  throws DAOException;
	
	public Terapista obtenerTerapista(int idTerapista) throws DAOException;
	
	//public void borrarTerapista(Terapista unTerapista) throws DAOException;
	
	//public void modificarTerapista(Terapista unTerapista) throws DAOException;
}
