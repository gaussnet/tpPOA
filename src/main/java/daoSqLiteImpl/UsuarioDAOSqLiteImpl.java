package daoSqLiteImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import basico.Administrador;
import basico.Paciente;
import basico.Usuario;
import basico.UsuarioInt;
import dao.UsuarioDAO;
import daoSqLiteImpl.DAOSqLiteImpl;
import exceptions.DAOException;

public class UsuarioDAOSqLiteImpl extends DAOSqLiteImpl implements UsuarioDAO {
	
	public void crearPaciente(Paciente unPaciente) throws DAOException {
		
		conectar();
		
		try {
			
			PreparedStatement ps= getConexion().prepareStatement("INSERT INTO usuario (dni, nombre, apellido, password, patologia, rol) VALUES (?,?,?,?,?,2)");
			ps.setInt(1, unPaciente.getDni());
			ps.setString(2, unPaciente.getNombre());
			ps.setString(3, unPaciente.getApellido());
			ps.setString(4, unPaciente.getPassword());
			ps.setString(5, unPaciente.getPatologia());
			
			ps.executeUpdate();
			
            getConexion().commit();
		} catch(SQLException e) {
			hacerRollback("Error al crear usuario");
		} finally {
            cerrarConexion();
		}
		
	}
	
	public void crearAdmin(Administrador unAdmin) throws DAOException {
		
		conectar();
		
		try {
			
			PreparedStatement ps= getConexion().prepareStatement("INSERT INTO usuario (dni, nombre, apellido, password, rol) VALUES (?,?,?,?,1)");
			ps.setInt(1, unAdmin.getDni());
			ps.setString(2, unAdmin.getNombre());
			ps.setString(3, unAdmin.getApellido());
			ps.setString(4, unAdmin.getPassword());
			
			ps.executeUpdate();
			
            getConexion().commit();
		} catch(SQLException e) {
			hacerRollback("Error al crear usuario");
		} finally {
            cerrarConexion();
		}
		
	}
	
	public void borrarUsuario(int unDni) throws DAOException {
		
		conectar();
		
		try {
			
			PreparedStatement ps= getConexion().prepareStatement("DELETE FROM usuario where dni= ?");
			ps.setInt(1, unDni);
			
			ps.executeUpdate();
			
			getConexion().commit();
		} catch (SQLException e){
			hacerRollback("Error al borrar usuario");
		} finally {
			cerrarConexion();
		}
	}
	
	public UsuarioInt obtenerUsuario(int unDni) throws DAOException {
		//UsuarioInt resultado = new Usuario();
		UsuarioInt resultado;
       
		conectar();
        
        try {
        	PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM usuario WHERE dni= ?");
        	ps.setInt(1, unDni);
			
			ResultSet rs= ps.executeQuery();

            if (rs.next()) {	
            	resultado= contruirUsuario(rs);
            } else {
            	throw new DAOException("No existe el usuario");
            }
        } catch (SQLException e) {
        	throw new DAOException("Error al buscar usuario");
        } finally {
            cerrarConexion();
        }
        return resultado;
	}
	
	public int obtenerDNIPaciente(String nombre, String apellido) throws DAOException {
		int resultado;
		
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM usuario WHERE nombre= ? and apellido= ?");
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			
			ResultSet rs= ps.executeQuery();
			
			 if (rs.next()) {	
	            	UsuarioInt paciente= contruirUsuario(rs);
	            	resultado= paciente.getDni();
	            } else {
	            	throw new DAOException("No existe el usuario");
	            }
			
		} catch (SQLException e) {
        	throw new DAOException("Error al buscar paciente");
        } finally {
            cerrarConexion();
        }
        return resultado;
	}
	
	public void modificarUsuario(UsuarioInt unUsuario) throws DAOException{
		
		conectar();
		
		try {
			if(unUsuario instanceof Paciente) {
				PreparedStatement ps= getConexion().prepareStatement("UPDATE usuario SET nombre= ?, apellido= ?, password= ?, patologia= ? where dni= ?");
				
				ps.setString(1, unUsuario.getNombre());
				ps.setString(2, unUsuario.getApellido());
				ps.setString(3, unUsuario.getPassword());
				ps.setString(4, unUsuario.getPatologia());
				ps.setInt(5, unUsuario.getDni());
				
				ps.executeUpdate();
			} else if(unUsuario instanceof Administrador) {
				PreparedStatement ps= getConexion().prepareStatement("UPDATE usuario SET nombre= ?, apellido= ?, password= ? where dni= ?");
				
				ps.setString(1, unUsuario.getNombre());
				ps.setString(2, unUsuario.getApellido());
				ps.setString(3, unUsuario.getPassword());
				ps.setInt(4, unUsuario.getDni());
				
				ps.executeUpdate();
			}
			
			
			
            getConexion().commit();
		} catch (SQLException e){
			hacerRollback("Error al modificar usuario");	
		} finally {
			cerrarConexion();
		}
		
	}

	public int autenticarUsuario(int unDni, String unPassword) throws DAOException {
		
		//Usuario resultado=null;
		//UsuarioInt resultado;			se usa???
		
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM usuario WHERE dni= ? and password= ?");
			
			ps.setInt(1, unDni);
			ps.setString(2, unPassword);
			
			ResultSet rs= ps.executeQuery();
			
			if (rs.next()) {
				//resultado= contruirUsuario(rs);
				return rs.getInt("rol");
			} else {
				throw new DAOException("Usuario o contraseña no válida");
			}
		} catch (SQLException e){
			throw new DAOException("Usuario no encontrado");	
		} finally {
			cerrarConexion();
		}
		//return resultado;
		
	}
	
	public UsuarioInt contruirUsuario(ResultSet rs) throws SQLException {
		
		UsuarioInt usuario = null;
		
		if (rs.getInt("rol") == 1) {
			usuario= new Administrador();
		} else if(rs.getInt("rol") == 2) {
			usuario= new Paciente();
			usuario.setPatologia(rs.getString("patologia"));
		}
		
		usuario.setDni(rs.getInt("dni"));
    	usuario.setNombre(rs.getString("nombre"));
    	usuario.setApellido(rs.getString("apellido"));
    	usuario.setPassword(rs.getString("Password"));
    	//usuario.setRol(rs.getString("rol"));
    	
    	return usuario;
	}
}
