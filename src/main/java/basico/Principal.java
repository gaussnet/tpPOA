package basico;

import gui.PanelManager;

public class Principal {

	private PanelManager manager;
	
	public static void main(String[] args) {

		Principal ppal= new Principal();
		ppal.iniciarManager();
		ppal.showFrame();

	}
	
	public void iniciarManager() {
		manager= new PanelManager();
		manager.armarManager();
		
		manager.mostrarPanelLogin();
		
	}
	
	public void showFrame() {
		manager.showFrame();
		
	}

}
