package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelAdmin extends JPanel {

	private PanelManager panelManager;
	
	private JPanel panelOpcionesUsuarios;
	private JPanel panelOpcionesTurnos;
	private JPanel panelOpcionesTerapistas;
	private JPanel panelSalir;
	
	private JButton botonSalir;
	
	private JButton botonCrearUsuario;
	private JButton botonModificarUsuario;
	private JButton botonBorrarUsuario;
	
	private JButton botonCrearTurno;
	private JButton botonModificarTurno;
	private JButton botonBorrarTurno;
	
	private JButton botonCrearTerapista;
	private JButton botonModificarTerapista;
	private JButton botonBorrarTerapista;
	
	public PanelAdmin(PanelManager manager ) {
		this.panelManager= manager;
		armarPanel();
	}
	
	public void armarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTabbedPane panelFichas= new JTabbedPane();
		
		panelOpcionesUsuarios= new JPanel();
		botonCrearUsuario= new JButton("Crear usuario");
		botonModificarUsuario= new JButton("Modificar usuario");
		botonBorrarUsuario= new JButton("Borrar ususario");
		//botonListarClientes= new JButton("Listar clientes");
		
		panelOpcionesUsuarios.add(botonCrearUsuario);
		panelOpcionesUsuarios.add(botonModificarUsuario);
		panelOpcionesUsuarios.add(botonBorrarUsuario);
		
		panelOpcionesTurnos= new JPanel();
		botonCrearTurno= new JButton("Crear turno");
		botonModificarTurno= new JButton("Modificar turno");
		botonBorrarTurno= new JButton("Borrar turno");
		
		panelOpcionesTurnos.add(botonCrearTurno);
		panelOpcionesTurnos.add(botonModificarTurno);
		panelOpcionesTurnos.add(botonBorrarTurno);
		
		panelOpcionesTerapistas= new JPanel();
		botonCrearTerapista= new JButton("Crear terapista");
		botonModificarTerapista= new JButton("Modificar terapista");
		botonBorrarTerapista= new JButton("Borrar terapista");
		
		panelOpcionesTerapistas.add(botonCrearTerapista);
		panelOpcionesTerapistas.add(botonModificarTerapista);
		panelOpcionesTerapistas.add(botonBorrarTerapista);
		
		panelSalir= new JPanel();
		botonSalir= new JButton("Cerrar sesión");
		
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelLogin();
			}
		});
		
		botonCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelAltaUsuario();
			}
		});
		
		botonModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelModificacionUsuario();
			}
		});
		
		botonBorrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelBajaUsuario();
			}
		});
		//TODO Crear los actionListener restantes
		
		panelSalir.add(botonSalir);
		
		panelFichas.addTab("Usuarios", null, panelOpcionesUsuarios, "Panel Usuarios");
		panelFichas.addTab("Turnos", null, panelOpcionesTurnos, "Panel Turnos");
		panelFichas.addTab("Terapistas", null, panelOpcionesTerapistas, "Panel Terapistas");
		
		this.add(panelFichas);
		this.add(panelSalir);
		
		
		
	}
}
