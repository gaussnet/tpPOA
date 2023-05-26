package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import basico.Paciente;
import basico.Turno;
import exceptions.ServicioException;
import service.TerapistaService;
import service.TurnoService;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PanelSolicitarTurno extends JPanel implements ActionListener {

	private PanelListaTurnos panelListaTurnos;
	private JPanel panelBuscaTerapista;
	private JComboBox terapistaComboBox;
	private JButton botonBuscar;
	
	private JPanel panelBotones;
	private JButton botonSolicitar;
	private JButton botonCancel;
	
	private String[] seleccion;
	
	private List<Turno> listaTurnos;
	
	private PanelManager panelManager;
	
	private TerapistaService terapistaServ;
	private TurnoService turnoServ;
	
	private Paciente paciente;
	
	private Boolean datosCargados= false;
	
	/**
	 * Create the panel.
	 */
	public PanelSolicitarTurno(PanelManager manager) {
		panelManager= manager;
		//setLayout(null);
		armarPanel();
		
	}
	
	//se cambia public por private 25/05
	private void armarPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblOperacion = new JLabel("Solicitud nuevo turno");
		lblOperacion.setHorizontalAlignment(SwingConstants.CENTER);
		//lblOperacion.setBounds(10, 11, 430, 14);
		add(lblOperacion);
		
		panelBuscaTerapista= new JPanel();
		
		JLabel lblTerapista = new JLabel("Terapista:");
		//lblTerapista.setBounds(10, 11, 430, 14);
		panelBuscaTerapista.add(lblTerapista);
		
		terapistaComboBox = new JComboBox();
		panelBuscaTerapista.add(terapistaComboBox);
		
		terapistaComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evento) {
				if (evento.getStateChange()== ItemEvent.SELECTED) {
					seleccion= terapistaComboBox.getSelectedItem().toString().split(" ");
					
				}	
			}
		});
		
		botonBuscar= new JButton("Buscar");
		botonBuscar.addActionListener(this);
		panelBuscaTerapista.add(botonBuscar);
		
		
		
		//panelBuscaTerapista= new PanelBuscaTerapista();
		add(panelBuscaTerapista);
		
		panelListaTurnos= new PanelListaTurnos();
		add(panelListaTurnos);
		
		JPanel panelBotones= new JPanel();
		botonSolicitar= new JButton("Solicitar");
		botonSolicitar.addActionListener(this);
		
		botonCancel= new JButton("Cancelar");
		botonCancel.addActionListener(this);
		
		panelBotones.add(botonSolicitar);
		panelBotones.add(botonCancel);
		
		add(panelBotones);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		int filaSeleccionada= panelListaTurnos.getTablaTurnos().getSelectedRow();
		if (e.getSource()==botonSolicitar) {
			//System.out.println("Boton solicitar");
			if (datosCargados & filaSeleccionada >=0) {
				turnoServ= new TurnoService();
				
				try {
					Turno turno= panelListaTurnos.getModelo().getContenido().get(filaSeleccionada);
					turnoServ.asignarTurno(turno.getNroTurno(), paciente);
					panelManager.mostrarOperExitosa();
					limpiarFormulario();
					llenarComboTerapista();
				} catch (ServicioException es) {
					panelManager.mostrarError(es);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay ningún turno seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if(e.getSource()==botonBuscar) {
			
			turnoServ= new TurnoService();
			
			try {
				listaTurnos= turnoServ.buscarTurnoTerapista(seleccion[0], seleccion[1]);
				
				panelListaTurnos.getModelo().setContenido(listaTurnos);
				panelListaTurnos.getModelo().fireTableDataChanged();
				datosCargados= true;
			} catch (ServicioException es) {
				panelManager.mostrarError(es);
			}
		} else if (e.getSource()==botonCancel) {
			limpiarFormulario();
			datosCargados= false;
			panelManager.mostrarPanelUsuario(paciente);
		}
	}
	
	//se cambia public por private 25/05
	private void limpiarFormulario() {
		//terapistaComboBox.setSelectedIndex(0);
		terapistaComboBox.removeAllItems();
		panelListaTurnos.getModelo().getContenido().clear();
		panelListaTurnos.getModelo().fireTableDataChanged();
		
		//diaChooser.setDate(null);
		//horaInicioComboBox.setSelectedIndex(0);
		//minutoInicioComboBox.setSelectedIndex(0);
	}
	
	public void llenarComboTerapista() {
		terapistaServ= new TerapistaService();
		
		try {
			List<String> terapistas= terapistaServ.listarTerapistas();
			
			for(int i= 0; i< terapistas.size(); i++) {
				terapistaComboBox.addItem(terapistas.get(i));
			}
		} catch (ServicioException es) {
			panelManager.mostrarError(es);
		}
		
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
}
