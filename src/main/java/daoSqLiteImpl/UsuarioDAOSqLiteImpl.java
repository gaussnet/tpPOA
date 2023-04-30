package daoSqLiteImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import basico.Usuario;
import dao.UsuarioDAO;
import daoSqLiteImpl.DAOSqLiteImpl;
import exceptions.DAOException;

public class UsuarioDAOSqLiteImpl extends DAOSqLiteImpl implements UsuarioDAO {

	public Usuario autenticarUsuario(int unDni, String unPassword) throws DAOException {
		
		Usuario resultado=null;
		
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM usuario WHERE dni= ? and password= ?");
			
			ps.setInt(1, unDni);
			ps.setString(2, unPassword);
			
			ResultSet rs= ps.executeQuery();
			
			if (rs.next()) {
				resultado= contruirUsuario(rs);
			} else {
				throw new DAOException("Usuario o contraseña no válida");
			}
		} catch (SQLException e){
			throw new DAOException("Usuario no encontrado");	
		} finally {
			cerrarConexion();
		}
		return resultado;
		
	}
	
	public Usuario contruirUsuario(ResultSet rs) throws SQLException {
		
		Usuario usuario= new Usuario();
		usuario.setDni(rs.getInt("dni"));
    	usuario.setNombre(rs.getString("nombre"));
    	usuario.setApellido(rs.getString("apellido"));
    	usuario.setPassword(rs.getString("Password"));
    	//usuario.setRol(rs.getString("rol"));
    	
    	return usuario;
	}
}
