package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import basico.Terapista;
import exceptions.ServicioException;
import service.TerapistaService;

public class PanelModificacionTerapista extends JPanel implements ActionListener {

	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	
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
		nombreLbl.setBounds(27, 60, 67, 14);
		add(nombreLbl);
		
		JLabel apellidoLbl = new JLabel("Apellido:");
		apellidoLbl.setBounds(27, 98, 67, 14);
		add(apellidoLbl);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(104, 57, 86, 20);
		add(nombreTxt);
		nombreTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(104, 95, 86, 20);
		add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		botonOk = new JButton("Aceptar");
		botonOk.setBounds(52, 160, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(187, 160, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
		
		botonBuscar = new JButton("Buscar");
		botonBuscar.setBounds(213, 75, 89, 23);
		botonBuscar.addActionListener(this);
		add(botonBuscar);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonOk) {
			if(nombreTxt.getText().isEmpty() || apellidoTxt.getText().isEmpty() || datosCargados == false) {
				panelManager.mostrarError("Datos incompletos");
			} else {
				terapistaServ= new TerapistaService();
				
				try {
					terapistaServ.modificarTerapista(idTerapista, nombreTxt.getText(), apellidoTxt.getText());
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
		try {
			idTerapista= terapistaServ.buscarTerapista(nombreTxt.getText(), apellidoTxt.getText());
			datosCargados= true;
			JOptionPane.showMessageDialog(this, "Terapista validado", "Resultado", JOptionPane.INFORMATION_MESSAGE);

		} catch (ServicioException es) {
			panelManager.mostrarError(es);
		}
	}
	
	private void limpiarFormulario() {
		nombreTxt.setText("");
		apellidoTxt.setText("");
	}

}
