package service;

import java.util.List;

import basico.Terapista;
import dao.TerapistaDAO;
import daoSqLiteImpl.TerapistaDAOSqLiteImpl;
import exceptions.DAOException;
import exceptions.ServicioException;

public class TerapistaService {

	public void crearTerapista(Terapista t) throws ServicioException {
		
		TerapistaDAO dao= new TerapistaDAOSqLiteImpl();
		try {
			dao.crearTerapista(t);
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
		
	}
	
	public List<String> listarTerapistas() throws ServicioException {
		TerapistaDAO dao= new TerapistaDAOSqLiteImpl();
		try {
			return dao.listarTerapistas();
		} catch (DAOException e) {
            throw new ServicioException(e);
        }
	}
}
