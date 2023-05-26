package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import basico.Turno;
import exceptions.ServicioException;
import service.TurnoService;
import service.UsuarioService;
import utilidades.Exportador;
import basico.UsuarioInt;
import basico.Paciente;

public class PanelHistorialTurnos extends JPanel implements ActionListener {

	private PanelManager panelManager;
	private JTextField pacienteTxt;
	private JButton botonBuscar;
	private JButton botonExportar;
	private JButton botonCancel;
	
	private List<Turno> listaTurnos;
	
	private PanelListaTurnos panelListaTurnos;
	
	private UsuarioService usuarioServ; 
	private TurnoService turnoServ;
	
	private Boolean datosCargados= false;
	
	/**
	 * Create the panel.
	 */
	public PanelHistorialTurnos(PanelManager manager) {
		panelManager= manager;
		armarPanel();
	}
	
	private void armarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel operacionLbl = new JLabel("Historial de turnos");
		operacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(operacionLbl);
		
		JPanel panelPaciente= new JPanel();
		
		JLabel dniPacienteLbl= new JLabel("DNI paciente:");
		
		pacienteTxt= new JTextField();
		pacienteTxt.setColumns(10);
		
		botonBuscar= new JButton("Buscar");
		botonBuscar.addActionListener(this);
		
		panelPaciente.add(dniPacienteLbl);
		panelPaciente.add(pacienteTxt);
		panelPaciente.add(botonBuscar);
		
		add(panelPaciente);
		
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
		//int filaSeleccionada= panelListaTurnos.getTablaTurnos().getSelectedRow();
		
		if (e.getSource()== botonBuscar) {
			if(pacienteTxt.getText().isEmpty()) {
				panelManager.mostrarError("Debe ingresar DNI");
			} else {
				usuarioServ= new UsuarioService();
				turnoServ= new TurnoService();
				
				try {
					Paciente paciente= (Paciente) usuarioServ.buscarUsuario(Integer.parseInt(pacienteTxt.getText()));
					listaTurnos= turnoServ.obtenerTurnosHistoricoTomadoPaciente(paciente);
					
					panelListaTurnos.getModelo().setContenido(listaTurnos);
					panelListaTurnos.getModelo().fireTableDataChanged();
					datosCargados= true;
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
			}
			
		} else if(e.getSource()==botonExportar) {
			if (datosCargados) {
				try {
					Exportador.exportarHistorial(listaTurnos);
					panelManager.mostrarOperExitosa();
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay turnos listados", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if(e.getSource()==botonCancel) {
			limpiarFormulario();
			datosCargados= false;
			panelManager.mostrarPanelAdmin();
		}
		
	}
	
	private void limpiarFormulario() {
		pacienteTxt.setText("");
		panelListaTurnos.getModelo().getContenido().clear();
		panelListaTurnos.getModelo().fireTableDataChanged();
	}

	public JTextField getPacienteTxt() {
		return pacienteTxt;
	}

	public void setPacienteTxt(JTextField pacienteTxt) {
		this.pacienteTxt = pacienteTxt;
	}
	
	

}
