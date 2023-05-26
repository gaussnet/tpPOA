package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import basico.Paciente;

public class PanelManager {

	private JFrame frame;
	private PanelLogin pantallaLogin;
	private PanelAdmin pantallaAdmin;
	private PanelAltaUsuario pantallaAltaUsuario;
	private PanelModificacionUsuario pantallaModificacionUsuario;
	private PanelBajaUsuario pantallaBajaUsuario;
	private PanelAltaTerapista pantallaAltaTerapista;
	private PanelAltaTurno pantallaAltaTurno;
	private PanelBMTurno pantallaBMTurno;
	private PanelHistorialTurnos pantallaHistorialTurnos;
	
	private PanelUsuario pantallaUsuario;
	private PanelSolicitarTurno pantallaSolicitarTurno;
	private PanelVerTurnos pantallaVerTurnos;
	
	public void armarManager() {
		
		frame= new JFrame("Centro terapéutico");
		//frame.setBounds(100,100,500,500);
		//frame.setSize(new Dimension(300, 300));
		frame.setLocationRelativeTo(null);
		//frame.setSize(10000, 10000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pantallaLogin= new PanelLogin(this);
		pantallaAdmin= new PanelAdmin(this);
		pantallaAltaUsuario= new PanelAltaUsuario(this);
		pantallaModificacionUsuario= new PanelModificacionUsuario(this);
		pantallaBajaUsuario= new PanelBajaUsuario(this);
		pantallaAltaTerapista= new PanelAltaTerapista(this);
		pantallaAltaTurno= new PanelAltaTurno(this);
		pantallaBMTurno= new PanelBMTurno(this);
		pantallaHistorialTurnos= new PanelHistorialTurnos(this);
		
		pantallaUsuario= new PanelUsuario(this);
		pantallaSolicitarTurno= new PanelSolicitarTurno(this);
		pantallaVerTurnos= new PanelVerTurnos(this);
		
		
	}
	
	public void showFrame() {
		frame.setVisible(true);
		
	}
	
	
	public void mostrarPanelLogin() {
		mostrarPanel(pantallaLogin);
		
	}
	
	public void mostrarPanelAdmin() {
		mostrarPanel(pantallaAdmin);
	}
	
	public void mostrarPanelAltaUsuario() {
		mostrarPanel(pantallaAltaUsuario);
	}
	
	public void mostrarPanelModificacionUsuario() {
		mostrarPanel(pantallaModificacionUsuario);
	}
	
	public void mostrarPanelBajaUsuario() {
		mostrarPanel(pantallaBajaUsuario);
	}
	
	public void mostrarPanelAltaTerapista() {
		mostrarPanel(pantallaAltaTerapista);
	}
	
	public void mostrarPanelAltaTurno() {
		pantallaAltaTurno.llenarComboTerapista();
		mostrarPanel(pantallaAltaTurno);
	}
	
	public void mostrarPanelBMTurno() {
		mostrarPanel(pantallaBMTurno);
	}
	
	public void mostrarPanelHistorialTurnos() {
		mostrarPanel(pantallaHistorialTurnos);
	}
	
	public void mostrarPanelUsuario(Paciente unPaciente) {
		pantallaUsuario.setPaciente(unPaciente);
		mostrarPanel(pantallaUsuario);
	}
	
	public void mostrarPanelSolicitarTurno(Paciente unPaciente) {
		pantallaSolicitarTurno.setPaciente(unPaciente);
		pantallaSolicitarTurno.llenarComboTerapista();
		mostrarPanel(pantallaSolicitarTurno);
	}
	
	public void mostrarPanelVerTurnos(Paciente paciente) {
		pantallaVerTurnos.setPaciente(paciente);
		pantallaVerTurnos.llenarListaTurnos(paciente);
		mostrarPanel(pantallaVerTurnos);
	}
	
	public void mostrarSalir() {
		int resultado= JOptionPane.showConfirmDialog(frame, "¿Está seguro que quiere salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (resultado==JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public void mostrarOperExitosa() {
		JOptionPane.showMessageDialog(frame, "Operación exitosa", "Resultado", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarError(Exception e) {
		JOptionPane.showMessageDialog(frame, e.getCause().getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarError(String message) {
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarPanel(JPanel unPanel) {
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(unPanel);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		frame.pack();
	}
}
