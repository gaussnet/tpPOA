package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import basico.Paciente;
import basico.UsuarioInt;
import exceptions.ServicioException;
import service.UsuarioService;

public class PanelBajaUsuario extends JPanel implements ActionListener{

	private JTextField dniTxt;
	private JTextField nombreTxt;
	private JTextField apellidoTxt;
	private JPasswordField passwordTxt;
	private JTextField patologiaTxt;
	private JLabel patologiaLbl;
	private JButton botonOk;
	private JButton botonCancel;
	private JButton botonBuscar;
	
	private UsuarioService usuarioServ;
	private UsuarioInt usuario;
	
	private PanelManager panelManager;
	
	private Boolean datosCargados= false;
	private JTextField rolTxt;
	/**
	 * Create the panel.
	 */
	public PanelBajaUsuario(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();
	}
	
	public void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(449, 300));
		
		JLabel lblOperacion = new JLabel("Eliminar usuario");
		lblOperacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperacion.setBounds(10, 11, 430, 14);
		add(lblOperacion);
		
		JLabel dniLbl = new JLabel("DNI:");
		dniLbl.setBounds(10, 61, 46, 14);
		add(dniLbl);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(95, 58, 86, 20);
		add(dniTxt);
		dniTxt.setColumns(10);
		
		botonBuscar = new JButton("Buscar");
		botonBuscar.setBounds(227, 57, 89, 23);
		botonBuscar.addActionListener(this);
		add(botonBuscar);
		
		JLabel apellidoLbl = new JLabel("Apellido:");
		apellidoLbl.setBounds(10, 123, 72, 14);
		add(apellidoLbl);
		
		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setBounds(10, 148, 72, 14);
		add(passwordLbl);
		
		JLabel rolLbl = new JLabel("Rol:");
		rolLbl.setBounds(10, 176, 46, 14);
		add(rolLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setBounds(10, 98, 72, 14);
		add(nombreLbl);
		
		patologiaLbl = new JLabel("Patología:");
		patologiaLbl.setBounds(10, 205, 72, 14);
		patologiaLbl.setVisible(false);
		add(patologiaLbl);
		
		botonOk = new JButton("Aceptar");
		botonOk.setBounds(92, 253, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(227, 253, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(95, 95, 86, 20);
		nombreTxt.setEditable(false);
		add(nombreTxt);
		nombreTxt.setColumns(10);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(95, 120, 86, 20);
		apellidoTxt.setEditable(false);
		add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(95, 145, 86, 20);
		passwordTxt.setEditable(false);
		add(passwordTxt);
		
		patologiaTxt = new JTextField();
		patologiaTxt.setBounds(95, 202, 86, 20);
		patologiaTxt.setVisible(false);
		patologiaTxt.setEditable(false);
		add(patologiaTxt);
		patologiaTxt.setColumns(10);
		
		JLabel instruccionLbl = new JLabel("Ingrese DNI a buscar");
		instruccionLbl.setBounds(10, 36, 157, 14);
		add(instruccionLbl);
		
		rolTxt = new JTextField();
		rolTxt.setBounds(95, 173, 86, 20);
		add(rolTxt);
		rolTxt.setColumns(10);
		rolTxt.setEditable(false);

	}
	
	public void actionPerformed(ActionEvent e) {
		this.setUsuarioServ(new UsuarioService());
		if (e.getSource()== botonOk) {
			if (this.getDatosCargados()) {
				int resultado= JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere borrar el usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
				if (resultado==JOptionPane.YES_OPTION) {
					try {
						this.getUsuarioServ().eliminarUsuario(Integer.parseInt(dniTxt.getText()));
						panelManager.mostrarOperExitosa();
						limpiarFormulario();
						this.setDatosCargados(false);
						panelManager.mostrarPanelAdmin();
					} catch (ServicioException es) {
						panelManager.mostrarError(es);
					} 
				}
				
			} else {
				JOptionPane.showMessageDialog(this, "No hay ning�n cliente cargado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (e.getSource()== botonCancel) {
			limpiarFormulario();
			this.setDatosCargados(false);
			panelManager.mostrarPanelAdmin();
		} else if (e.getSource()== botonBuscar) {
			buscar();
		}
		
	}
	
	public void limpiarFormulario() {
		dniTxt.setText("");
		nombreTxt.setText("");
		apellidoTxt.setText("");
		passwordTxt.setText("");
		patologiaTxt.setText("");
		rolTxt.setText("");
		
		patologiaLbl.setVisible(false);
		patologiaTxt.setVisible(false);
		
	}
	
	public void llenarFormulario(UsuarioInt usu) {
		dniTxt.setText(String.valueOf(usu.getDni()));
		nombreTxt.setText(usu.getNombre());
		apellidoTxt.setText(usu.getApellido());
		passwordTxt.setText(usu.getPassword());
		
		if(usu instanceof Paciente) {
			patologiaTxt.setText(usu.getPatologia());
			rolTxt.setText("Paciente");
			patologiaLbl.setVisible(true);
			patologiaTxt.setVisible(true);
			
		} else {
			rolTxt.setText("Administrador");
			patologiaLbl.setVisible(false);
			patologiaTxt.setVisible(false);
		}
		
	}
	
	public void buscar() {
		try {
			this.setUsuario(this.getUsuarioServ().buscarUsuario(Integer.parseInt(dniTxt.getText())));
			llenarFormulario(this.getUsuario());
			datosCargados= true;
		} catch (ServicioException es) {
			panelManager.mostrarError(es);
			limpiarFormulario();
		} catch (java.lang.NumberFormatException es) {
			panelManager.mostrarError("DNI: Tiene que ingresar un número");
			limpiarFormulario();
		}
	}

	public UsuarioService getUsuarioServ() {
		return usuarioServ;
	}

	public void setUsuarioServ(UsuarioService usuarioServ) {
		this.usuarioServ = usuarioServ;
	}

	public UsuarioInt getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioInt usuario) {
		this.usuario = usuario;
	}

	public Boolean getDatosCargados() {
		return datosCargados;
	}

	public void setDatosCargados(Boolean datosCargados) {
		this.datosCargados = datosCargados;
	}
	
	

}
