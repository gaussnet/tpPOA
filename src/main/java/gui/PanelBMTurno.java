package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import basico.Turno;
import exceptions.ServicioException;
import service.TurnoService;
import utilidades.FechaUtil;

import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class PanelBMTurno extends JPanel implements ActionListener {

	private PanelManager panelManager;
	private JLabel fechasLbl;
	
	private PanelListaTurnos panelListaTurnos;
	private JButton botonLiberar;
	private JButton botonTomado;
	private JButton botonCancel;
	private JButton botonBorrar;
	
	private PanelBuscaFecha panelBuscaFecha; 
	
	private TurnoService turnoServ;
	private List<Turno> listaTurnos;
	
	private Boolean datosCargados= false;
	
	/**
	 * Create the panel.
	 */
	public PanelBMTurno(PanelManager manager) {
		panelManager= manager;
		armarPanel();

	}
	

	private void armarPanel() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel operacionLbl = new JLabel("Gestión de turnos");
		operacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(operacionLbl);
		
		JPanel panelRangoFecha= new JPanel();
		
		fechasLbl = new JLabel("Seleccione el intervalo de fechas a buscar:");
		fechasLbl.setHorizontalAlignment(SwingConstants.LEFT);
		panelRangoFecha.add(fechasLbl);
		
		add(panelRangoFecha);
		
		panelBuscaFecha= new PanelBuscaFecha();
		panelBuscaFecha.getBotonBuscar().addActionListener(this);
		add(panelBuscaFecha);
		
		panelListaTurnos= new PanelListaTurnos();
		this.add(panelListaTurnos);
		
		JPanel panelBotones= new JPanel();
		
		
		botonLiberar = new JButton("Liberar Turno");
		botonLiberar.addActionListener(this);
		
		botonTomado= new JButton("Marcar tomado");
		botonTomado.addActionListener(this);
		
		botonBorrar = new JButton("Borrar");
		botonBorrar.addActionListener(this);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.addActionListener(this);
		
		panelBotones.add(botonLiberar);
		panelBotones.add(botonTomado);
		panelBotones.add(botonBorrar);
		panelBotones.add(botonCancel);
		
		add(panelBotones);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		int filaSeleccionada= this.getPanelListaTurnos().getTablaTurnos().getSelectedRow();
		
		if (e.getSource()== panelBuscaFecha.getBotonBuscar()) {
			llenarTabla();
		} else if(e.getSource()==botonLiberar) {
			
			if (datosCargados & filaSeleccionada >=0) {
				try {
					Turno turno= panelListaTurnos.getModelo().getContenido().get(filaSeleccionada);
					turnoServ.liberarTurno(turno.getNroTurno(), turno.getFechaDesde());
					panelManager.mostrarOperExitosa();
					llenarTabla();						//Refresca la tabla luego del cambio
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay ningún turno seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if(e.getSource()==botonTomado) {
			if (datosCargados & filaSeleccionada >=0) {
				try {
					Turno turno= panelListaTurnos.getModelo().getContenido().get(filaSeleccionada);
					turnoServ.marcarTomado(turno.getNroTurno(), turno.getFechaDesde());
					panelManager.mostrarOperExitosa();
					llenarTabla();						//Refresca la tabla luego del cambio
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay ningún turno seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if(e.getSource()==botonBorrar) {
			if (datosCargados & filaSeleccionada >=0) {
				int resultado= JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere borrar el turno?", "Confirmación", JOptionPane.YES_NO_OPTION);
				if (resultado==JOptionPane.YES_OPTION) {
					try {
						Turno turno= panelListaTurnos.getModelo().getContenido().get(filaSeleccionada);
						turnoServ.eliminarTurno(turno.getNroTurno());
						panelManager.mostrarOperExitosa();
						llenarTabla();
						
					} catch (ServicioException es) {
						panelManager.mostrarError(es);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay ningún turno seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if(e.getSource()==botonCancel) {
			limpiarFormulario();
			panelManager.mostrarPanelAdmin();
		}
	}
	
	private void llenarTabla() {
		turnoServ= new TurnoService();
		try {
			
			LocalDateTime fechaInicio= FechaUtil.armarFechaYHora(panelBuscaFecha.getDiaChooserInicio().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), 0, 0);
			LocalDateTime fechaFin= FechaUtil.armarFechaYHora(panelBuscaFecha.getDiaChooserFin().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), 23, 59);
			
			listaTurnos= turnoServ.buscarTurnos(fechaInicio, fechaFin);
			
			panelListaTurnos.getModelo().setContenido(listaTurnos);
			panelListaTurnos.getModelo().fireTableDataChanged();
			datosCargados= true;

		} catch (ServicioException es) {
			panelManager.mostrarError(es);
		} catch( NullPointerException es) {
			panelManager.mostrarError("Debe seleccionar una fecha");
		}
	}
	

	private void limpiarFormulario() {
		panelBuscaFecha.getDiaChooserInicio().setDate(null);
		panelBuscaFecha.getDiaChooserFin().setDate(null);
		
		panelListaTurnos.getModelo().getContenido().clear();
		panelListaTurnos.getModelo().fireTableDataChanged();
		datosCargados= false;
		
	}


	private PanelListaTurnos getPanelListaTurnos() {
		return panelListaTurnos;
	}

	
	private void setPanelListaTurnos(PanelListaTurnos panelListaTurnos) {
		this.panelListaTurnos = panelListaTurnos;
	}

	public JButton getBotonLiberar() {
		return botonLiberar;
	}

	public void setBotonLiberar(JButton botonLiberar) {
		this.botonLiberar = botonLiberar;
	}

	public JButton getBotonCancel() {
		return botonCancel;
	}

	public void setBotonCancel(JButton botonCancel) {
		this.botonCancel = botonCancel;
	}

	public JButton getBotonBorrar() {
		return botonBorrar;
	}

	public void setBotonBorrar(JButton botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	public PanelBuscaFecha getPanelBuscaFecha() {
		return panelBuscaFecha;
	}

	public void setPanelBuscaFecha(PanelBuscaFecha panelBuscaFecha) {
		this.panelBuscaFecha = panelBuscaFecha;
	}
	
}
