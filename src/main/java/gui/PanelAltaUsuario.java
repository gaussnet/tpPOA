package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

import basico.Administrador;
import basico.Paciente;
import basico.Usuario;
import basico.UsuarioInt;
import exceptions.ServicioException;
import service.UsuarioService;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class PanelAltaUsuario extends JPanel implements ActionListener {
	private JTextField dniTxt;
	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	private JPasswordField passwordTxt;
	private JTextField patologiaTxt;
	private JRadioButton pacienteRadioButton;
	private JRadioButton adminRadioButton;
	private ButtonGroup grupoRol;
	private JLabel patologiaLbl;
	private JButton botonOk;
	private JButton botonCancel;
	private PanelManager panelManager;
	
	private UsuarioService usuarioServ;
	
	private UsuarioInt usuario;
	

	/**
	 * Create the panel.
	 */
	public PanelAltaUsuario(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();
		
	}
	
	private void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(449, 300));
		
		JLabel lblOperacion = new JLabel("Alta usuario", SwingConstants.CENTER);
		lblOperacion.setBounds(10, 11, 430, 14);
		add(lblOperacion);
		
		JLabel dniLbl = new JLabel("DNI:");
		dniLbl.setBounds(10, 47, 46, 14);
		add(dniLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setBounds(10, 72, 76, 14);
		add(nombreLbl);
		
		JLabel apellidoLbl = new JLabel("Apellido:");
		apellidoLbl.setBounds(10, 97, 76, 14);
		add(apellidoLbl);
		
		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setBounds(10, 122, 76, 14);
		add(passwordLbl);
		
		JLabel rolLbl = new JLabel("Rol:");
		rolLbl.setBounds(10, 147, 46, 14);
		add(rolLbl);
		
		pacienteRadioButton = new JRadioButton("Paciente");
		pacienteRadioButton.setBounds(77, 143, 109, 23);
		pacienteRadioButton.addActionListener(this);
		add(pacienteRadioButton);
		
		adminRadioButton = new JRadioButton("Administrador");
		adminRadioButton.setBounds(188, 143, 109, 23);
		adminRadioButton.addActionListener(this);
		add(adminRadioButton);
		
		grupoRol= new ButtonGroup();
		grupoRol.add(pacienteRadioButton);
		grupoRol.add(adminRadioButton);
		
		patologiaLbl = new JLabel("Patología:");
		patologiaLbl.setBounds(10, 172, 76, 14);
		patologiaLbl.setVisible(false);
		add(patologiaLbl);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(84, 44, 86, 20);
		add(dniTxt);
		dniTxt.setColumns(10);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(84, 69, 86, 20);
		add(nombreTxt);
		nombreTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(84, 94, 86, 20);
		add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(84, 119, 86, 20);
		add(passwordTxt);
		passwordTxt.setColumns(10);
		
		patologiaTxt = new JTextField();
		patologiaTxt.setBounds(87, 169, 86, 20);
		patologiaTxt.setVisible(false);
		add(patologiaTxt);
		patologiaTxt.setColumns(10);
		
		botonOk = new JButton("Aceptar");
		botonOk.setBounds(98, 231, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(216, 231, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(pacienteRadioButton.isSelected()) {
			patologiaLbl.setVisible(true);
			patologiaTxt.setVisible(true);
		}
		
		if(adminRadioButton.isSelected()) {
			patologiaLbl.setVisible(false);
			patologiaTxt.setVisible(false);
		}
		
		if (e.getSource()==botonOk) {
			
			usuarioServ= new UsuarioService();
			
			try {
				if(dniTxt.getText().isEmpty() || nombreTxt.getText().isEmpty() || apellidoTxt.getText().isEmpty() || passwordTxt.getPassword().length == 0 || 
						(!pacienteRadioButton.isSelected() && !adminRadioButton.isSelected())) {
					panelManager.mostrarError("Datos incompletos");
				} else {
					if(pacienteRadioButton.isSelected()) {
						this.setUsuario(new Paciente(Integer.parseInt(this.getDniTxt().getText()), this.getNombreTxt().getText(), 
								this.getApellidoTxt().getText(), String.format("%s", new String(this.getPasswordTxt().getPassword())), this.getPatologiaTxt().getText()));	
						
						usuarioServ.crearPaciente((Paciente)usuario);
						
					} else if (adminRadioButton.isSelected()) {
						this.setUsuario(new Administrador(Integer.parseInt(this.getDniTxt().getText()), this.getNombreTxt().getText(), 
								this.getApellidoTxt().getText(), String.format("%s", new String(this.getPasswordTxt().getPassword()))));
						
						usuarioServ.crearAdmin((Administrador)usuario);
					}
					
					limpiarFormulario();
					panelManager.mostrarOperExitosa();
					panelManager.mostrarPanelAdmin();
				}
				
			} catch (ServicioException es) {
				panelManager.mostrarError(es);
			} catch (java.lang.NumberFormatException es) {
				panelManager.mostrarError("DNI: Tiene que ingresar un número");
			}
			
		} else if (e.getSource()==botonCancel) {
			limpiarFormulario();
			panelManager.mostrarPanelAdmin();
		}
		
		
	}
	

	private void limpiarFormulario() {
		dniTxt.setText("");
		nombreTxt.setText("");
		apellidoTxt.setText("");
		passwordTxt.setText("");
		patologiaTxt.setText("");
		
		patologiaLbl.setVisible(false);
		patologiaTxt.setVisible(false);
		
		pacienteRadioButton.setSelected(false);
		adminRadioButton.setSelected(false);
		
	}
	

	public JTextField getDniTxt() {
		return dniTxt;
	}

	public void setDniTxt(JTextField dniTxt) {
		this.dniTxt = dniTxt;
	}

	public JTextField getNombreTxt() {
		return nombreTxt;
	}

	public void setNombreTxt(JTextField nombreTxt) {
		this.nombreTxt = nombreTxt;
	}

	public JTextField getApellidoTxt() {
		return apellidoTxt;
	}

	public void setApellidoTxt(JTextField apellidoTxt) {
		this.apellidoTxt = apellidoTxt;
	}

	public JPasswordField getPasswordTxt() {
		return passwordTxt;
	}

	public void setPasswordTxt(JPasswordField passwordTxt) {
		this.passwordTxt = passwordTxt;
	}

	public JTextField getPatologiaTxt() {
		return patologiaTxt;
	}

	public void setPatologiaTxt(JTextField patologiaTxt) {
		this.patologiaTxt = patologiaTxt;
	}

	public UsuarioInt getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioInt usuario) {
		this.usuario = usuario;
	}
	
}
