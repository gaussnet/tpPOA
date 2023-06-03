package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import basico.Terapista;
import exceptions.ServicioException;
import service.TerapistaService;

import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;


public class PanelAltaTerapista extends JPanel implements ActionListener {
	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	
	private JRadioButton mananaRadioButton;
	private JRadioButton tardeRadioButton;
	private JRadioButton nocheRadioButton;
	
	
	private JButton botonOk;
	private JButton botonCancel;

	private PanelManager panelManager;
	
	private TerapistaService terapistaServ;
	
	private Terapista terapista;
	
	/**
	 * Create the panel.
	 */
	public PanelAltaTerapista(PanelManager manager) {
		
		this.panelManager= manager;
		armarPanel();
		
	}
	
	private void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(283, 242));
		
		JLabel operacionLbl = new JLabel("Alta terapista");
		operacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		operacionLbl.setBounds(10, 11, 263, 14);
		add(operacionLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setBounds(10, 60, 67, 14);
		add(nombreLbl);
		
		JLabel apellidoLbl = new JLabel("Apellido:");
		apellidoLbl.setBounds(10, 98, 67, 14);
		add(apellidoLbl);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(87, 57, 86, 20);
		add(nombreTxt);
		nombreTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(87, 95, 86, 20);
		add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		botonOk = new JButton("Aceptar");
		botonOk.setBounds(27, 191, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(160, 191, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
		
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

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonOk) {
			if(nombreTxt.getText().isEmpty() || apellidoTxt.getText().isEmpty() || (!mananaRadioButton.isSelected() && !tardeRadioButton.isSelected() && !nocheRadioButton.isSelected())) {
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
					
					this.setTerapista(new Terapista(nombreTxt.getText(), apellidoTxt.getText(), turnoSelected));
					terapistaServ.crearTerapista(terapista);
					
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
				
				limpiarFormulario();
				panelManager.mostrarOperExitosa();
				panelManager.mostrarPanelAdmin();
			}
			
			
		} else if(e.getSource()== botonCancel) {
			limpiarFormulario();
			panelManager.mostrarPanelAdmin();
		}
	}
	
	private void limpiarFormulario() {
		nombreTxt.setText("");
		apellidoTxt.setText("");
		mananaRadioButton.setSelected(false);
		tardeRadioButton.setSelected(false);
		nocheRadioButton.setSelected(false);
	}

	public Terapista getTerapista() {
		return terapista;
	}

	public void setTerapista(Terapista terapista) {
		this.terapista = terapista;
	}
}
