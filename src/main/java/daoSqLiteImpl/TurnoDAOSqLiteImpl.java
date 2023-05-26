package daoSqLiteImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import basico.Paciente;
import basico.Terapista;
import basico.Turno;
import dao.TurnoDAO;
import exceptions.DAOException;
import utilidades.FechaUtil;

public class TurnoDAOSqLiteImpl  extends DAOSqLiteImpl implements TurnoDAO{

	public void crearTurno(String fechaDesde, String fechaHasta, String nombreTerapista, String apellidoTerapista) throws DAOException{
		
		//conectar();
		
		try {
			
			TerapistaDAOSqLiteImpl terapistaDAOSqLiteImpl = new TerapistaDAOSqLiteImpl();
			int idTerapista= terapistaDAOSqLiteImpl.obtenerIDTerapista(nombreTerapista, apellidoTerapista);
			
			conectar();
			
			PreparedStatement ps= getConexion().prepareStatement("INSERT INTO turno (fechaDesde, fechaHasta, terapista, tomado) VALUES (?,?,?,?)");
			
			ps.setString(1, fechaDesde);
			ps.setString(2, fechaHasta);
			ps.setInt(3, idTerapista);
			ps.setInt(4, 0);
			
			ps.executeUpdate();
			
            getConexion().commit();
		} catch(SQLException e) {
			//e.printStackTrace();
			hacerRollback("Error al crear turno");
		} finally {
            cerrarConexion();
		}
	}
	
	public List<Turno> obtenerTurnos(String fechaDesde, String fechaHasta) throws DAOException {
		List<Turno> lista= new ArrayList<>();
		Turno turno;
		//String sql = "SELECT * FROM turno where date(fechaDesde) >= ? and date(fechaHasta) <= ?";
		
		conectar();
		
		try {
			//A la fecha que recibo le saco la hora
			String fechaDesdeTruncada= fechaDesde.split("T")[0];
			String fechaHastaTruncada= fechaHasta.split("T")[0];
			//System.out.println(fechaDesdeTruncada + " " + fechaHastaTruncada);
			
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM turno where date(fechaDesde) >= ? and date(fechaHasta) <= ?");
			
			//ps.setString(1, fechaDesde);
			//ps.setString(2, fechaHasta);
			ps.setString(1, fechaDesdeTruncada);
			ps.setString(2, fechaHastaTruncada);
			
			ResultSet rs= ps.executeQuery();
			
			while (rs.next()) {
				turno= construirTurno(rs);
				lista.add(turno);
			}
			
		} catch (SQLException e) {
        	throw new DAOException("Error al listar turnos");
        } finally {
            cerrarConexion();
        }
		
		return lista;
		
	}
	
	public List<Turno> obtenerTurnosTerapista(String nombreTerapista, String apellidoTerapista, String fechaDesde, String fechaHasta) throws DAOException {
		List<Turno> lista= new ArrayList<>();
		Turno turno;
		
		//conectar();
		
		try {
			TerapistaDAOSqLiteImpl terapistaDAOSqLiteImpl = new TerapistaDAOSqLiteImpl();
			int idTerapista= terapistaDAOSqLiteImpl.obtenerIDTerapista(nombreTerapista, apellidoTerapista);
			
			conectar();
			
			String fechaDesdeTruncada= fechaDesde.split("T")[0];
			String fechaHastaTruncada= fechaHasta.split("T")[0];
			
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM turno where date(fechaDesde) >= ? and date(fechaHasta) <= ? and terapista = ? and paciente is null");
			
			ps.setString(1, fechaDesdeTruncada);
			ps.setString(2, fechaHastaTruncada);
			ps.setInt(3, idTerapista);
			
			ResultSet rs= ps.executeQuery();
			
			while (rs.next()) {
				turno= construirTurno(rs);
				lista.add(turno);
			}
			
		}  catch (SQLException e) {
        	throw new DAOException("Error al listar turnos");
        } finally {
            cerrarConexion();
        }
		
		return lista;
	}
	
	public List<Turno> obtenerTurnosPaciente(int dniPaciente, String fechaDesde) throws DAOException {
		List<Turno> lista= new ArrayList<>();
		Turno turno;
		
		try {
			conectar();
			String fechaDesdeTruncada= fechaDesde.split("T")[0];
			
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM turno where date(fechaDesde) >= ? and paciente = ?");
			ps.setString(1, fechaDesdeTruncada);
			ps.setInt(2, dniPaciente);
			
			ResultSet rs= ps.executeQuery();
			
			while (rs.next()) {
				turno= construirTurno(rs);
				lista.add(turno);
			}
			
		} catch (SQLException e) {
        	throw new DAOException("Error al listar turnos");
        } finally {
            cerrarConexion();
        }
		
		return lista;
		
	}
	
	public void borrarTurno(int nroTurno) throws DAOException {
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("DELETE FROM turno where id_turno= ?");
			ps.setInt(1, nroTurno);
			
			ps.executeUpdate();
			
			getConexion().commit();
		} catch (SQLException e){
			//e.printStackTrace();
			hacerRollback("Error al borrar turno");
		} finally {
			cerrarConexion();
		}
	}
	
	public void liberarTurno(int nroTurno) throws DAOException {
		//Turno turno;
		
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM turno where id_turno= ?");
			ps.setInt(1, nroTurno);
			
			ResultSet rs= ps.executeQuery();
			
			if (rs.next()) {
				//turno= construirTurno(rs);
				if(rs.getInt("paciente") == 0) {
					throw new DAOException("El turno no está asignado");
				} else {
					PreparedStatement psUpdate= getConexion().prepareStatement("UPDATE turno SET paciente= ? where id_turno= ?");
					//psUpdate.setInt(1, 0);
					psUpdate.setNull(1, 0);
					psUpdate.setInt(2,  nroTurno);
					
					psUpdate.executeUpdate();
					
					getConexion().commit();
				}
			} else {
            	throw new DAOException("No existe el turno");
            }
			
		} catch (SQLException e){
			//e.printStackTrace();
			hacerRollback("Error al liberar turno");
		} finally {
			cerrarConexion();
		}
	}
	
	public void marcarTomado(int nroTurno) throws DAOException {
		conectar();
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM turno where id_turno= ?");
			ps.setInt(1, nroTurno);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("paciente") == 0) {
					throw new DAOException("El turno no está asignado");
				} else {
					if(rs.getInt("tomado") == 1) {
						throw new DAOException("El turno ya está tomado");
					} else {
						PreparedStatement psUpdate= getConexion().prepareStatement("UPDATE turno SET tomado= ? where id_turno= ?");
						psUpdate.setInt(1, 1);
						psUpdate.setInt(2,  nroTurno);
						
						psUpdate.executeUpdate();
						
						getConexion().commit();
					}
				}
				
			} else {
            	throw new DAOException("No existe el turno");
            }
			
		} catch (SQLException e){
			//e.printStackTrace();
			hacerRollback("Error al marcar turno");
		} finally {
			cerrarConexion();
		}
	}
	
	public void asignarTurno(int nroTurno, int dniPaciente) throws DAOException {
		
		try {
			//UsuarioDAOSqLiteImpl usuarioDAOSqLiteImpl= new UsuarioDAOSqLiteImpl();
			
			//int dniPaciente= usuarioDAOSqLiteImpl.obtenerDNIPaciente(nombrePaciente, apellidoPaciente);
			
			conectar();
			
			PreparedStatement psUpdate= getConexion().prepareStatement("UPDATE turno SET paciente= ? where id_turno= ?");
			//psUpdate.setInt(1, 0);
			psUpdate.setInt(1, dniPaciente);
			psUpdate.setInt(2,  nroTurno);
			
			psUpdate.executeUpdate();
			
			getConexion().commit();
			
		} catch(SQLException e) {
			//e.printStackTrace();
			hacerRollback("Error al crear turno");
		} finally {
            cerrarConexion();
		}
	}
	
	public List<Turno> obtenerTurnosHistoricoTomadoPaciente(int dniPaciente, String fechaDesde, String fechaHasta) throws DAOException {
		List<Turno> lista= new ArrayList<>();
		Turno turno;
		
		try {
			conectar();
			String fechaDesdeTruncada= fechaDesde.split("T")[0];
			String fechaHastaTruncada= fechaHasta.split("T")[0];
			
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM turno where date(fechaDesde) >= ? and date(fechaHasta) <= ? and paciente = ? and tomado = 1");
			ps.setString(1, fechaDesdeTruncada);
			ps.setString(2, fechaHastaTruncada);
			ps.setInt(3, dniPaciente);
			
			ResultSet rs= ps.executeQuery();
			
			while (rs.next()) {
				turno= construirTurno(rs);
				lista.add(turno);
			}
			
		} catch (SQLException e) {
        	throw new DAOException("Error al listar turnos");
        } finally {
            cerrarConexion();
        }
		
		return lista;
		
	}
	
	//Se cambio public por private 25/05/2023
	private Turno construirTurno(ResultSet rs) throws SQLException, DAOException {
		Turno turno= new Turno();
		Paciente paciente= null;
		Terapista terapista= null;
		
		turno.setNroTurno(rs.getInt("id_turno"));
		turno.setFechaDesde(FechaUtil.stringToLocalDateTime(rs.getString("fechaDesde")));
		turno.setFechaHasta(FechaUtil.stringToLocalDateTime(rs.getString("fechaHasta")));
		
		if(rs.getInt("tomado") == 0) {
			turno.setTomado(false);
		} else {
			turno.setTomado(true);
		}
		
		
		try {
			if(rs.getInt("paciente") == 0) {
				turno.setAsignadoA(null);
			} else {
				PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM usuario WHERE dni= ?");
	        	ps.setInt(1, rs.getInt("paciente"));
				
				ResultSet rsPaciente= ps.executeQuery();

	            if (rsPaciente.next()) {	
	            	//paciente= contruirUsuario(rs);
	            	paciente= new Paciente();
	            	paciente.setDni(rsPaciente.getInt("dni"));
	            	paciente.setNombre(rsPaciente.getString("nombre"));
	            	paciente.setApellido(rsPaciente.getString("apellido"));
	            	paciente.setPassword(rsPaciente.getString("Password"));
	            	paciente.setPatologia(rsPaciente.getString("patologia"));
	            	turno.setAsignadoA(paciente);
	            } else {
	            	throw new DAOException("No existe el usuario");
	            }
			}
        	
        } catch (SQLException e) {
        	throw new DAOException("Error al buscar usuario");
        }
		
		
		try {
			PreparedStatement ps= getConexion().prepareStatement("SELECT * FROM terapista WHERE id_terapista= ?");
        	ps.setInt(1, rs.getInt("terapista"));
			
			ResultSet rsTerapista= ps.executeQuery();

            if (rsTerapista.next()) {	
            	terapista= new Terapista();
            	terapista.setNombre(rsTerapista.getString("nombre"));
        		terapista.setApellido(rsTerapista.getString("apellido"));
        		turno.setTerapista(terapista);
            } else {
            	throw new DAOException("No existe el terapista");
            }
			
		} catch (SQLException e) {
        	throw new DAOException("Error al buscar terapista");
		}
		
		return turno;
		
	}
	
}
