package daoSqLiteImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import basico.Terapista;
import dao.TerapistaDAO;
import exceptions.DAOException;

public class TerapistaDAOSqLiteImpl extends DAOSqLiteImpl implements TerapistaDAO{

	public void crearTerapista(Terapista unTerapista) throws DAOException {
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("INSERT INTO terapista (nombre, apellido) VALUES (?,?)");
			
			ps.setString(1, unTerapista.getNombre());
			ps.setString(2, unTerapista.getApellido());
			
			ps.executeUpdate();
			
            getConexion().commit();
			
		} catch(SQLException e) {
			hacerRollback("Error al crear terapista");
		} finally {
            cerrarConexion();
		}
	}
	
	public List<String> listarTerapistas() throws DAOException {
		String nombre;
		String sql = "SELECT * FROM terapista";
		
		List<String> lista = new ArrayList<>();
		conectar();
		
		try {
            Statement s = getConexion().createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {	
            	nombre= rs.getString("nombre") + " " + rs.getString("apellido");
            	lista.add(nombre);
            }
        } catch (SQLException e) {
        	throw new DAOException("Error al listar terapistas");
        } finally {
            cerrarConexion();
        }
        return lista;
		
	}
	
	public int obtenerIDTerapista(String nombre, String apellido)  throws DAOException {
		int resultado = 0;
		
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM terapista WHERE nombre= ? and apellido= ?");
        	ps.setString(1, nombre);
			ps.setString(2,  apellido);
        	
			ResultSet rs= ps.executeQuery();

            if (rs.next()) {	
            	resultado= rs.getInt("id_terapista");
            } else {
            	throw new DAOException("No existe el terapista");
            }
			
		} catch (SQLException e){
			hacerRollback("Error al obtener terapista");	
		} finally {
			cerrarConexion();
		}
		
		return resultado;
	}
	
	public Terapista obtenerTerapista(int idTerapista) throws DAOException {
		Terapista resultado;
		
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM terapista WHERE id_terapista= ?");
        	ps.setInt(1, idTerapista);
			
			ResultSet rs= ps.executeQuery();

            if (rs.next()) {	
            	resultado= construirTerapista(rs);
            } else {
            	throw new DAOException("No existe el terapista");
            }
			
		} catch (SQLException e) {
        	throw new DAOException("Error al buscar terapista");
        } finally {
            cerrarConexion();
        }
		
        return resultado;
	}
	
	public void borrarTerapista(String nombre, String apellido) throws DAOException {
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("DELETE FROM terapista where nombre= ? and apellido= ?");
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			
			ps.executeUpdate();
			
			getConexion().commit();
		} catch (SQLException e){
			hacerRollback("Error al borrar terapista");
		} finally {
			cerrarConexion();
		}
	}
	
	public void modificarTerapista(int idTerapista, String nombre, String apellido) throws DAOException {
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("UPDATE terapista SET nombre= ?, apellido= ? where id_terapista= ?");
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setInt(3, idTerapista);
			
			ps.executeUpdate();
			
			getConexion().commit();
		} catch (SQLException e){
			hacerRollback("Error al modificar terapista");
		} finally {
			cerrarConexion();
		}
	}
	
	public Terapista construirTerapista(ResultSet rs) throws SQLException {
		Terapista terapista= new Terapista();
		
		terapista.setNombre(rs.getString("nombre"));
		terapista.setApellido(rs.getString("apellido"));
		
		return terapista;
	}
}
