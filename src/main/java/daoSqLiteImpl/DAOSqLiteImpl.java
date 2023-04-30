package daoSqLiteImpl;

import java.sql.Connection;
import java.sql.SQLException;

import basico.DBManager;
import exceptions.DAOException;
import exceptions.DBException;

public class DAOSqLiteImpl {

private static Connection conexion;
	
	public void conectar() throws DAOException{
		try {
			conexion= DBManager.connect();
		} catch (DBException e){
			throw new DAOException("Error al iniciar conexión");
		}
	}
	
	public void hacerRollback(String message) throws DAOException{
		try {
            conexion.rollback();
            
            throw new DAOException(message);
            
        } catch (SQLException e1) {
        	throw new DAOException("Error al hacer rollback");
        }
	}
	
	public void cerrarConexion() throws DAOException {
		try {
            conexion.close();
        } catch (SQLException e1) {
        	throw new DAOException("Error al cerrar conexión");
        }
	}

	
	public static Connection getConexion() {
		return conexion;
	}

	public static void setConexion(Connection c) {
		DAOSqLiteImpl.conexion = c;
	}
}
