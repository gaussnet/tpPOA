package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelListaTurnos extends JPanel {

	private JTable tablaTurnos;
	private TurnoTableModel modelo;
	private JScrollPane scrollPane;
	
	public PanelListaTurnos() {
		armarPanel();
	}
	
	public void armarPanel() {
		modelo= new TurnoTableModel();
		tablaTurnos = new JTable(modelo);
		scrollPane= new JScrollPane(tablaTurnos);
		this.add(scrollPane);
	}
	
	public TurnoTableModel getModelo() {
		return modelo;
	}

	public void setModelo(TurnoTableModel modelo) {
		this.modelo = modelo;
	}

	public JTable getTablaTurnos() {
		return tablaTurnos;
	}

	public void setTablaCuentas(JTable tablaTurnos) {
		this.tablaTurnos = tablaTurnos;
	}
}
