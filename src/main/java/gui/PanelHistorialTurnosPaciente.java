package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import basico.Paciente;
import basico.Turno;
import exceptions.ExportadorException;
import exceptions.ServicioException;
import service.TurnoService;
import utilidades.Exportador;

public class PanelHistorialTurnosPaciente extends JPanel implements ActionListener {

	private PanelManager panelManager;
	
	private JButton botonExportar;
	private JButton botonCancel;
	
	private List<Turno> listaTurnos;
	
	private PanelListaTurnos panelListaTurnos;
	
	private Paciente paciente;
	
	private TurnoService turnoServ;
	
	/**
	 * Create the panel.
	 */
	public PanelHistorialTurnosPaciente(PanelManager manager) {
		panelManager= manager;
		armarPanel();
		
	}
	
	private void armarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel operacionLbl = new JLabel("Historial de turnos");
		operacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(operacionLbl);
		
		panelListaTurnos= new PanelListaTurnos();
		this.add(panelListaTurnos);
		
		JPanel panelBotones= new JPanel();
		
		botonExportar= new JButton("Exportar");
		botonExportar.addActionListener(this);
		botonCancel= new JButton("Cancelar");
		botonCancel.addActionListener(this);
		
		panelBotones.add(botonExportar);
		panelBotones.add(botonCancel);
		
		add(panelBotones);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botonExportar) {
			try {
				if(listaTurnos.size() > 0) {
					Exportador.exportarHistorial(listaTurnos);
				} else {
					panelManager.mostrarError("No hay turnos cargados");
				}
				
			} catch (ExportadorException es) {
				panelManager.mostrarError(es);
			}
		} else if(e.getSource()==botonCancel) {
			limpiarFormulario();
			panelManager.mostrarPanelUsuario(paciente);
		}
	}
	
	private void limpiarFormulario() {
		panelListaTurnos.getModelo().getContenido().clear();
		panelListaTurnos.getModelo().fireTableDataChanged();
	}
	
	public void llenarListaTurnos(Paciente paciente) {
		turnoServ= new TurnoService();
		
		try {
			listaTurnos= turnoServ.obtenerTurnosHistoricoTomadoPaciente(paciente);
			
			panelListaTurnos.getModelo().setContenido(listaTurnos);
			panelListaTurnos.getModelo().fireTableDataChanged();
		} catch (ServicioException es) {
			panelManager.mostrarError(es);
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
}
