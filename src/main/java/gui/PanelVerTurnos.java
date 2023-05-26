package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import basico.Paciente;
import basico.Turno;
import exceptions.ServicioException;
import service.TurnoService;

public class PanelVerTurnos extends JPanel implements ActionListener {

	private JButton botonLiberarTurno;
	private JButton botonCancel;
	private PanelListaTurnos panelListaTurnos;
	
	private PanelManager panelManager;
	
	private Paciente paciente;
	
	private Boolean datosCargados= false;		//Lo necesito??
	
	private TurnoService turnoServ;
	
	/**
	 * Create the panel.
	 */
	public PanelVerTurnos(PanelManager manager) {
		panelManager= manager;
		armarPanel();

	}
	
	private void armarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblOperacion = new JLabel("Mis turnos");
		lblOperacion.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblOperacion);
		
		panelListaTurnos= new PanelListaTurnos();
		add(panelListaTurnos);
		
		JPanel panelBotones= new JPanel();
		botonLiberarTurno= new JButton("Cancelar turno");
		botonLiberarTurno.addActionListener(this);
		
		botonCancel= new JButton("Cancelar");
		botonCancel.addActionListener(this);
		
		panelBotones.add(botonLiberarTurno);
		panelBotones.add(botonCancel);
		
		add(panelBotones);
	}
	
	public void actionPerformed(ActionEvent e) {
		int filaSeleccionada= panelListaTurnos.getTablaTurnos().getSelectedRow();
		
		if (e.getSource()==botonLiberarTurno) {
			
			if (filaSeleccionada >=0) {
				try {
					Turno turno= panelListaTurnos.getModelo().getContenido().get(filaSeleccionada);
					turnoServ.liberarTurnoPaciente(turno.getNroTurno(), turno.getFechaDesde());
					panelManager.mostrarOperExitosa();
					limpiarFormulario();
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay ningún turno seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (e.getSource()==botonCancel) {
			limpiarFormulario();
			datosCargados= false;
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
			panelListaTurnos.getModelo().setContenido(turnoServ.obtenerTurnosPaciente(paciente));
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
