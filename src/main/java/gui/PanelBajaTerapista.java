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

public class PanelBajaTerapista extends JPanel implements ActionListener {

	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	
	private JButton botonOk;
	private JButton botonCancel;

	private PanelManager panelManager;
	
	private TerapistaService terapistaServ;
	
	private Terapista terapista;
	
	
	/**
	 * Create the panel.
	 */
	public PanelBajaTerapista(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();
	}
	
	private void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(256, 217));
		
		JLabel operacionLbl = new JLabel("Baja terapista");
		operacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		operacionLbl.setBounds(10, 11, 236, 14);
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
		botonOk.setBounds(10, 160, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(145, 160, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==botonOk) {
			if(nombreTxt.getText().isEmpty() || apellidoTxt.getText().isEmpty()) {
				panelManager.mostrarError("Datos incompletos");
			} else {
				int resultado= JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere borrar el terapista?", "Confirmación", JOptionPane.YES_NO_OPTION);
				if (resultado==JOptionPane.YES_OPTION) {
					terapistaServ= new TerapistaService();
					
					try {
						terapistaServ.eliminarTerapista(nombreTxt.getText(), apellidoTxt.getText());
						panelManager.mostrarOperExitosa();
						panelManager.mostrarPanelAdmin();
						limpiarFormulario();
					} catch (ServicioException es) {
						panelManager.mostrarError(es);
					}
				}
				
			}
		} else if(e.getSource()==botonCancel) {
			limpiarFormulario();
			panelManager.mostrarPanelAdmin();
		}
	}
	
	private void limpiarFormulario() {
		nombreTxt.setText("");
		apellidoTxt.setText("");
	}
	
}
