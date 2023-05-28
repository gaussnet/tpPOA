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
	private JButton botonHistorialTurnos;
	
	private JButton botonCrearTerapista;
	private JButton botonModificarTerapista;
	private JButton botonBorrarTerapista;
	
	public PanelAdmin(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();
	}
	
	private void armarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTabbedPane panelFichas= new JTabbedPane();
		
		panelOpcionesUsuarios= new JPanel();
		botonCrearUsuario= new JButton("Crear usuario");
		botonModificarUsuario= new JButton("Modificar usuario");
		botonBorrarUsuario= new JButton("Borrar ususario");
		
		panelOpcionesUsuarios.add(botonCrearUsuario);
		panelOpcionesUsuarios.add(botonModificarUsuario);
		panelOpcionesUsuarios.add(botonBorrarUsuario);
		
		panelOpcionesTurnos= new JPanel();
		botonCrearTurno= new JButton("Crear turno");
		botonModificarTurno= new JButton("Gestionar turnos");
		botonHistorialTurnos= new JButton("Historial turnos");
		
		panelOpcionesTurnos.add(botonCrearTurno);
		panelOpcionesTurnos.add(botonModificarTurno);
		panelOpcionesTurnos.add(botonHistorialTurnos);
		
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
		
		botonCrearTerapista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelAltaTerapista();
			}
		});
		
		botonBorrarTerapista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelBajaTerapista();
			}
		});
		
		botonModificarTerapista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelModificacionTerapista();
			}
		});
		
		botonCrearTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelAltaTurno();
			}
		});
		
		botonModificarTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelBMTurno();
			}
		});
		
		botonHistorialTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManager.mostrarPanelHistorialTurnos();
			}
		});
		
		panelSalir.add(botonSalir);
		
		panelFichas.addTab("Usuarios", null, panelOpcionesUsuarios, "Panel Usuarios");
		panelFichas.addTab("Turnos", null, panelOpcionesTurnos, "Panel Turnos");
		panelFichas.addTab("Terapistas", null, panelOpcionesTerapistas, "Panel Terapistas");
		
		this.add(panelFichas);
		this.add(panelSalir);
		
	}
}
