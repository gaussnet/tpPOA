package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import basico.Usuario;
import exceptions.ServicioException;
import service.UsuarioService;

import javax.swing.JButton;

public class PanelLogin extends JPanel implements ActionListener {
	private JLabel operacionLbl;
	private JLabel usuarioLbl;
	private JTextField usuarioTxt;
	private JLabel passwordLbl;
	private JPasswordField passwordTxt;
	private JButton botonOK;
	private JButton botonCancel;
	
	private PanelManager panelManager;
	
	private UsuarioService usuarioServ;
	//private Usuario usuarioLogin;
	int rol;

	/**
	 * Create the panel.
	 */
	public PanelLogin(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();

	}
	
	public void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(449, 300));
		//this.setSize(new Dimension(300, 300));
		
		usuarioLbl = new JLabel("Usuario:");
		usuarioLbl.setBounds(128, 92, 68, 14);
		add(usuarioLbl);
		
		passwordLbl = new JLabel("Contraseña:");
		passwordLbl.setBounds(128, 141, 83, 14);
		add(passwordLbl);
		
		usuarioTxt = new JTextField();
		usuarioTxt.setBounds(221, 89, 86, 20);
		add(usuarioTxt);
		usuarioTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(221, 138, 86, 20);
		add(passwordTxt);
		passwordTxt.setColumns(10);
		
		operacionLbl = new JLabel("Ingrese DNI y contraseña");
		operacionLbl.setBounds(139, 35, 153, 14);
		add(operacionLbl);
		
		botonOK = new JButton("Aceptar");
		botonOK.setBounds(128, 228, 89, 23);
		botonOK.addActionListener(this);
		add(botonOK);
		
		botonCancel = new JButton("Salir");
		botonCancel.setBounds(246, 228, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonOK) {
			usuarioServ= new UsuarioService();
			try {
				rol= usuarioServ.autenticarUsuario(Integer.parseInt(usuarioTxt.getText()), String.format("%s", new String(passwordTxt.getPassword())));
				
				if(rol== 1) {
					panelManager.mostrarPanelAdmin();
				} else if(rol == 2) {
					System.out.println("Es un paciente");
					//TODO
					
				} else {
					panelManager.mostrarError("Usuario no habilitado");
				}
				
			} catch(ServicioException es) {
				panelManager.mostrarError(es);
			} catch (java.lang.NumberFormatException es) {
					panelManager.mostrarError("DNI: Tiene que ingresar un número");
			}
			
			limpiarFormulario();
		} else if (e.getSource()==botonCancel) {
			panelManager.mostrarSalir();
		}
	}

	public void limpiarFormulario() {
		this.getUsuarioTxt().setText("");
		this.getPasswordTxt().setText("");
	}
	
	public JTextField getUsuarioTxt() {
		return usuarioTxt;
	}

	public void setUsuarioTxt(JTextField usuarioTxt) {
		this.usuarioTxt = usuarioTxt;
	}

	public JTextField getPasswordTxt() {
		return passwordTxt;
	}

	public void setPasswordTxt(JPasswordField passwordTxt) {
		this.passwordTxt = passwordTxt;
	}
	
	
}
