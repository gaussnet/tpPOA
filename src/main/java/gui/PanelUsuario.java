package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class PanelUsuario extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelUsuario() {
		setLayout(null);
		
		JLabel lblTítulo = new JLabel("Bienvenido");
		lblTítulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTítulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTítulo.setBounds(10, 11, 430, 14);
		add(lblTítulo);
		
		JButton btnNuevoTurno = new JButton("Nuevo turno");
		btnNuevoTurno.setBounds(26, 68, 108, 23);
		add(btnNuevoTurno);
		
		JButton btnVerTurnos = new JButton("Ver turnos");
		btnVerTurnos.setBounds(176, 68, 89, 23);
		add(btnVerTurnos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(193, 240, 89, 23);
		add(btnSalir);
		
		JButton btnHistorial = new JButton("Historial");
		btnHistorial.setBounds(327, 68, 89, 23);
		add(btnHistorial);
		
		JButton btnCancelarTurno = new JButton("Cancelar turno");
		btnCancelarTurno.setBounds(176, 166, 106, 23);
		add(btnCancelarTurno);

	}
}
