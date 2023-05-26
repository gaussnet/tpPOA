package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import exceptions.ServicioException;
import service.TerapistaService;
import service.TurnoService;
import utilidades.FechaUtil;

import javax.swing.JComboBox;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JButton;

public class PanelAltaTurno extends JPanel implements ActionListener {

	private JButton botonOk;
	private JButton botonCancel;
	private JComboBox terapistaComboBox;
	private JDateChooser diaChooser;
	private JComboBox horaInicioComboBox;
	private JComboBox minutoInicioComboBox;
	
	private PanelManager panelManager;
	
	private String[] seleccion;
	private TerapistaService terapistaServ;
	private TurnoService turnoServ;
	
	/**
	 * Create the panel.
	 */
	public PanelAltaTurno(PanelManager manager) {
		this.panelManager= manager;
		armarPanel();

	}
	
	//se cambia public por private 25/05
	private void armarPanel() {
		setLayout(null);
		this.setPreferredSize(new Dimension(330, 300));
		
		JLabel operqcionLbl = new JLabel("Alta turno");
		operqcionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		operqcionLbl.setBounds(10, 11, 272, 14);
		add(operqcionLbl);
		
		JLabel terapistaLbl = new JLabel("Terapista:");
		terapistaLbl.setBounds(10, 52, 64, 14);
		add(terapistaLbl);
		
		diaChooser= new JDateChooser();
		diaChooser.setDateFormatString("dd MMM y");
		diaChooser.setBounds(87, 88,150, 22);
		add(diaChooser);
		
		JLabel horaInicioLbl = new JLabel("Hora:");
		horaInicioLbl.setBounds(10, 125, 38, 14);
		add(horaInicioLbl);
		
		terapistaComboBox = new JComboBox();
		terapistaComboBox.setBounds(84, 48, 153, 22);
		
		add(terapistaComboBox);
		
		terapistaComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evento) {
				if (evento.getStateChange()== ItemEvent.SELECTED) {
					seleccion= terapistaComboBox.getSelectedItem().toString().split(" ");
					
				}	
			}
		});
		
		horaInicioComboBox = new JComboBox(FechaUtil.getHoras());
		horaInicioComboBox.setBounds(52, 121, 79, 22);
		horaInicioComboBox.setMaximumRowCount(5);
		add(horaInicioComboBox);
		
		minutoInicioComboBox = new JComboBox(FechaUtil.getMinutos());
		
		minutoInicioComboBox.setBounds(191, 121, 79, 22);
		add(minutoInicioComboBox);
		
		JLabel minutoInicioLbl = new JLabel("Minuto:");
		minutoInicioLbl.setBounds(146, 125, 46, 14);
		add(minutoInicioLbl);
		
		botonOk = new JButton("Aceptar");
		botonOk.setBounds(24, 207, 89, 23);
		botonOk.addActionListener(this);
		add(botonOk);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.setBounds(168, 207, 89, 23);
		botonCancel.addActionListener(this);
		add(botonCancel);
		
		JLabel fechaLbl = new JLabel("Fecha:");
		fechaLbl.setBounds(10, 89, 52, 14);
		add(fechaLbl);
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonOk) {
			turnoServ= new TurnoService();
			
			try {
				
				turnoServ.crearTurno(FechaUtil.armarFechaYHora(diaChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), Integer.parseInt(horaInicioComboBox.getSelectedItem().toString()), 
						Integer.parseInt(minutoInicioComboBox.getSelectedItem().toString())), seleccion[0], seleccion[1]);
				
				limpiarFormulario();
				panelManager.mostrarOperExitosa();
				panelManager.mostrarPanelAdmin();
				
			} catch (ServicioException es) {
				panelManager.mostrarError(es);
			} catch( NullPointerException es) {
				panelManager.mostrarError("Debe seleccionar una fecha");
			}
			
		} else if (e.getSource()==botonCancel) {
			limpiarFormulario();
			panelManager.mostrarPanelAdmin();
		}
		
	}
	
	//se cambia public por private 25/05
	private void limpiarFormulario() {
		//terapistaComboBox.setSelectedIndex(0);
		terapistaComboBox.removeAllItems();
		diaChooser.setDate(null);
		horaInicioComboBox.setSelectedIndex(0);
		minutoInicioComboBox.setSelectedIndex(0);
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
}
