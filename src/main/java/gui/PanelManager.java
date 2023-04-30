package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelManager {

	private JFrame frame;
	private PanelLogin pantallaLogin;
	
	public void armarManager() {
		
		frame= new JFrame("Centro terapéutico");
		//frame.setBounds(100,100,500,500);
		//frame.setSize(new Dimension(300, 300));
		frame.setLocationRelativeTo(null);
		//frame.setSize(10000, 10000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pantallaLogin= new PanelLogin(this);
		
		
	}
	
	public void showFrame() {
		frame.setVisible(true);
		
	}
	
	
	public void mostrarPanelLogin() {
		mostrarPanel(pantallaLogin);
		
	}
	
	public void mostrarSalir() {
		int resultado= JOptionPane.showConfirmDialog(frame, "¿Está seguro que quiere salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if (resultado==JOptionPane.YES_OPTION) {
			System.exit(0);
		}
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
