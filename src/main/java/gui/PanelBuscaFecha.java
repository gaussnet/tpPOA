package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class PanelBuscaFecha extends JPanel {
	
	//private JLabel fechasLbl;
	private JLabel inicioLbl;
	private JLabel finLbl;
	private JDateChooser diaChooserInicio;
	private JDateChooser diaChooserFin;
	private JButton botonBuscar;
	//private PanelManager panelManager;

	public PanelBuscaFecha() {
		armarPanel();
	}
	
	public void armarPanel() {
		
		inicioLbl = new JLabel("Inicio:");
		inicioLbl.setHorizontalAlignment(SwingConstants.LEFT);
		//inicioLbl.setBounds(10, 63, 62, 14);
		add(inicioLbl);
		
		diaChooserInicio= new JDateChooser();
		diaChooserInicio.setDateFormatString("dd MMM y");
		
		add(diaChooserInicio);
		
		finLbl = new JLabel("Fin:");
		finLbl.setHorizontalAlignment(SwingConstants.LEFT);
		//finLbl.setBounds(170, 63, 73, 14);
		add(finLbl);
		
		diaChooserFin= new JDateChooser();
		diaChooserFin.setDateFormatString("dd MMM y");
		
		add(diaChooserFin);
		
		botonBuscar = new JButton("Buscar");
		//botonBuscar.setBounds(330, 87, 89, 23);
		//botonBuscar.addActionListener(this);
		add(botonBuscar);
	}

	public JDateChooser getDiaChooserInicio() {
		return diaChooserInicio;
	}

	public void setDiaChooserInicio(JDateChooser diaChooserInicio) {
		this.diaChooserInicio = diaChooserInicio;
	}

	public JDateChooser getDiaChooserFin() {
		return diaChooserFin;
	}

	public void setDiaChooserFin(JDateChooser diaChooserFin) {
		this.diaChooserFin = diaChooserFin;
	}

	public JButton getBotonBuscar() {
		return botonBuscar;
	}

	public void setBotonBuscar(JButton botonBuscar) {
		this.botonBuscar = botonBuscar;
	}
	
	
}
