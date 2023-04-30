package basico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DBException;

public class PrincipalPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Connection c= null;
		
		try {
			c= DBManager.connect();
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		
		try {
			PreparedStatement ps= c.prepareStatement("select * from Usuario");
			ResultSet rs= ps.executeQuery();
			
			while (rs.next()) {
                
                System.out.print("\t" + rs.getInt("dni"));
                System.out.print("\t" + rs.getString("nombre"));
                System.out.print("\t" + rs.getString("apellido"));
                System.out.print("\t"+ rs.getString("password"));
                System.out.print("\t"+ rs.getString("patologia"));
                System.out.println("\t" + rs.getInt("rol"));
                System.out.println();
                
             
			}
		} catch(SQLException e) {
			
		}
		*/
		
		
	}

}
