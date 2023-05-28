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

	public PanelBuscaFecha() {
		armarPanel();
	}
	

	private void armarPanel() {
		
		inicioLbl = new JLabel("Inicio:");
		inicioLbl.setHorizontalAlignment(SwingConstants.LEFT);

		add(inicioLbl);
		
		diaChooserInicio= new JDateChooser();
		diaChooserInicio.setDateFormatString("dd MMM y");
		
		add(diaChooserInicio);
		
		finLbl = new JLabel("Fin:");
		finLbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		add(finLbl);
		
		diaChooserFin= new JDateChooser();
		diaChooserFin.setDateFormatString("dd MMM y");
		
		add(diaChooserFin);
		
		botonBuscar = new JButton("Buscar");

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
