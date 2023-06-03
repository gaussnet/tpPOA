package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import basico.Terapista;
import exceptions.ServicioException;
import service.TerapistaService;
import utilidades.FechaUtil;


public class PanelModificacionTerapista extends JPanel implements ActionListener {

	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	
	private JRadioButton mananaRadioButton;
	private JRadioButton tardeRadioButton;
	private JRadioButton nocheRadioButton;
	
	private JButton botonOk;
	private JButton botonCancel;
	private JButton botonBuscar;

	private PanelManager panelManager;
	
	private TerapistaService terapistaServ;
	
	private Terapista terapista;
	private int idTerapista;
	
	private Boolean datosCargados= false;

	
	/**
	 * Create the panel.
	 */
	public PanelModificacionTerapista(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();
	}
	
	private void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(324, 217));
		
		JLabel operacionLbl = new JLabel("Modificación terapista");
		operacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		operacionLbl.setBounds(10, 11, 304, 14);
		add(operacionLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setBounds(10, 60, 67, 14);
		add(nombreLbl);
		
		JLabel apellidoLbl = new JLabel("Apellido:");
		apellidoLbl.setBounds(10, 98, 67, 14);
		add(apellidoLbl);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(62, 57, 86, 20);
		add(nombreTxt);
		nombreTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(62, 95, 86, 20);
		add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		JLabel turnoLbl = new JLabel("Turno:");
		turnoLbl.setBounds(10, 136, 46, 14);
		add(turnoLbl);
		
		mananaRadioButton = new JRadioButton("Mañana");
		mananaRadioButton.setBounds(62, 132, 74, 23);
		mananaRadioButton.addActionListener(this);
		add(mananaRadioButton);
		
		tardeRadioButton = new JRadioButton("Tarde");
		tardeRadioButton.setBounds(133, 132, 60, 23);
		tardeRadioButton.addActionListener(this);
		add(tardeRadioButton);
		
		nocheRadioButton = new JRadioButton("Noche");
		nocheRadioButton.setBounds(195, 132, 74, 23);
		nocheRadioButton.addActionListener(this);
		add(nocheRadioButton);
		
		ButtonGroup grupoTerapista= new ButtonGroup();
		grupoTerapista.add(mananaRadioButton);
		grupoTerapista.add(tardeRadioButton);
		grupoTerapista.add(nocheRadioButton);
		
		botonOk = new JButton("Aceptar");
		botonOk.setBounds(62, 183, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(197, 183, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
		
		botonBuscar = new JButton("Buscar");
		botonBuscar.setBounds(213, 75, 89, 23);
		botonBuscar.addActionListener(this);
		add(botonBuscar);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonOk) {
			if(nombreTxt.getText().isEmpty() || apellidoTxt.getText().isEmpty() || (!mananaRadioButton.isSelected() && !tardeRadioButton.isSelected() && !nocheRadioButton.isSelected()) || datosCargados == false) {
				panelManager.mostrarError("Datos incompletos");
			} else {
				terapistaServ= new TerapistaService();
				
				try {
					String turnoSelected= null
							;
					if(mananaRadioButton.isSelected()) {
						turnoSelected= "m";
					} else if(tardeRadioButton.isSelected()) {
						turnoSelected= "t";
					} else if(nocheRadioButton.isSelected()) {
						turnoSelected= "n";
					} else {
						panelManager.mostrarError("Turno inexistente");
					}
					
					terapistaServ.modificarTerapista(idTerapista, nombreTxt.getText(), apellidoTxt.getText(), turnoSelected);
					limpiarFormulario();
					datosCargados= false;
					panelManager.mostrarOperExitosa();
					panelManager.mostrarPanelAdmin();
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
			}
		} else if(e.getSource()==botonBuscar) {
			buscar();
		} else if(e.getSource()==botonCancel) {
			limpiarFormulario();
			datosCargados= false;
			panelManager.mostrarPanelAdmin();
		}
	}
	
	private void buscar() {
		terapistaServ= new TerapistaService();
		String turnoTerapista= null;
		
		try {
			idTerapista= terapistaServ.buscarTerapista(nombreTxt.getText(), apellidoTxt.getText());
			turnoTerapista= terapistaServ.obtenerTurnoTerapista(nombreTxt.getText(), apellidoTxt.getText());
			if(turnoTerapista.equals("m")) {
				mananaRadioButton.setSelected(true);
				tardeRadioButton.setSelected(false);
				nocheRadioButton.setSelected(false);
			} else if(turnoTerapista.equals("t")) {
				mananaRadioButton.setSelected(false);
				tardeRadioButton.setSelected(true);
				nocheRadioButton.setSelected(false);
			} else if(turnoTerapista.equals("n")) {
				mananaRadioButton.setSelected(false);
				tardeRadioButton.setSelected(false);
				nocheRadioButton.setSelected(true);
			} else {
				panelManager.mostrarError("Turno no existe");
			}
			
			datosCargados= true;
			JOptionPane.showMessageDialog(this, "Terapista validado", "Resultado", JOptionPane.INFORMATION_MESSAGE);

		} catch (ServicioException es) {
			panelManager.mostrarError(es);
		}
	}
	
	private void limpiarFormulario() {
		nombreTxt.setText("");
		apellidoTxt.setText("");
		mananaRadioButton.setSelected(false);
		tardeRadioButton.setSelected(false);
		nocheRadioButton.setSelected(false);
	}

}
