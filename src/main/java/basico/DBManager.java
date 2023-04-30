package basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.DBException;

public class DBManager {
	
	private static final String DB_BASE_URL= "jdbc:sqlite:";
	private static final String DB_NAME= "centro.db";
	
	
	public static Connection connect() throws DBException {
		
		Connection c = null;
		
		try {
			String url= DB_BASE_URL+DB_NAME;
			c = DriverManager.getConnection(url);
			c.setAutoCommit(false);
		} catch(SQLException e) {
			throw new DBException("Error de conexión");
		}
		
		return c;
	}

}
