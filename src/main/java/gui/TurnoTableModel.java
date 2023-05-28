package gui;

import javax.swing.table.AbstractTableModel;

import basico.Turno;
import utilidades.FechaUtil;
import basico.Paciente;
import basico.Terapista;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class TurnoTableModel extends AbstractTableModel {
	
	private static final int COLUMNA_FECHA_DESDE=0;
	//private static final int COLUMNA_FECHA_HASTA=1;
	private static final int COLUMNA_PACIENTE=1;
	private static final int COLUMNA_TERAPISTA=2;
	private static final int COLUMNA_TOMADO=3;
	private static final int COLUMNA_NRO_TURNO=4;
	
	private String[] nombresColumnas= {"Fecha y hora", "Paciente", "Terapista", "Tomado", "Nro turno"};
	//private Class[] tiposColumnas= {String.class, Paciente.class, Terapista.class, boolean.class, int.class};
	private Class[] tiposColumnas= {String.class, Paciente.class, Terapista.class, String.class, int.class};
	
	private List<Turno> contenido;
	
	public TurnoTableModel() {
		contenido= new ArrayList<Turno>();
	}

	public TurnoTableModel(List<Turno> contenidoIni) {
		contenido= contenidoIni;
	}
	
	public int getColumnCount() {
		return nombresColumnas.length;
	}
	
	public int getRowCount() {
		return contenido.size();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Turno t= contenido.get(rowIndex);
		
		Object result= null;
		switch(columnIndex) {
		case COLUMNA_FECHA_DESDE:
			result= FechaUtil.getFechaMostrable(t.getFechaDesde());
			break;
		//case COLUMNA_FECHA_HASTA:
			//result= FechaUtil.getFechaMostrable(t.getFechaHasta());
			//break;
		case COLUMNA_PACIENTE:
			result= t.getAsignadoA();
			break;
		case COLUMNA_TERAPISTA:
			result= t.getTerapista();
			break;
		case COLUMNA_TOMADO:
			if(t.getTomado()) {
				result= "Sí";
			} else {
				result= "No";
			}
			//result= t.getTomado();
			break;
		case COLUMNA_NRO_TURNO:
			result= t.getNroTurno();
			break;
		default:
			result= new String("");
		}
		
		return result;
	}
	
	public String getColumnName(int col) {
		return nombresColumnas[col];
	}
	
	public Class getColumnClass(int col) {
		return tiposColumnas[col];
	}
	
	public List<Turno> getContenido() {
		return contenido;
	}
	
	public void setContenido(List<Turno> contenido) {
		this.contenido= contenido;
	}
	
}
