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

import javax.swing.JButton;

public class PanelAltaTerapista extends JPanel implements ActionListener {
	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	
	JButton botonOk;
	JButton botonCancel;

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
	
	//se cambia public por private 25/05
	private void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(410, 242));
		
		JLabel operacionLbl = new JLabel("Alta terapista");
		operacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		operacionLbl.setBounds(10, 11, 390, 14);
		add(operacionLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setBounds(137, 60, 67, 14);
		add(nombreLbl);
		
		JLabel apellidoLbl = new JLabel("Apellido:");
		apellidoLbl.setBounds(137, 98, 67, 14);
		add(apellidoLbl);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(214, 57, 86, 20);
		add(nombreTxt);
		nombreTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(214, 95, 86, 20);
		add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		botonOk = new JButton("Aceptar");
		botonOk.setBounds(101, 160, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(236, 160, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonOk) {
			if(nombreTxt.getText().isEmpty() || apellidoTxt.getText().isEmpty()) {
				panelManager.mostrarError("Datos incompletos");
				
			} else {
				terapistaServ= new TerapistaService();
				
				try {
					this.setTerapista(new Terapista(nombreTxt.getText(), apellidoTxt.getText()));
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
	
	//se cambia public por private 25/05
	private void limpiarFormulario() {
		nombreTxt.setText("");
		apellidoTxt.setText("");
	}

	public Terapista getTerapista() {
		return terapista;
	}

	public void setTerapista(Terapista terapista) {
		this.terapista = terapista;
	}
	
	
}
