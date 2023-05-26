package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import basico.Paciente;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUsuario extends JPanel {

	private PanelManager panelManager;
	
	private Paciente paciente;
	
	/**
	 * Create the panel.
	 */
	public PanelUsuario(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();
		
	}
	
	//se cambia public por private 25/05
	private void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(323, 256));
		
		JLabel lblTítulo = new JLabel("Bienvenido");
		lblTítulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTítulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTítulo.setBounds(10, 11, 303, 14);
		add(lblTítulo);
		
		JButton btnNuevoTurno = new JButton("Solicitar turno");
		btnNuevoTurno.setBounds(20, 56, 120, 23);
		add(btnNuevoTurno);
		
		btnNuevoTurno.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelSolicitarTurno(paciente);
			}
		});
		
		JButton btnVerTurnos = new JButton("Ver turnos");
		btnVerTurnos.setBounds(178, 56, 120, 23);
		add(btnVerTurnos);
		
		btnVerTurnos.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelVerTurnos(paciente);
			}
		});
		
		JButton btnHistorial = new JButton("Historial");
		btnHistorial.setBounds(103, 119, 120, 23);
		add(btnHistorial);
		
		JButton btnSalir = new JButton("Cerrar sesión");
		btnSalir.setBounds(103, 197, 120, 23);
		add(btnSalir);
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelLogin();
			}
		});
		
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
}
