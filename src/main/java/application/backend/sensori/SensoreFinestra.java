package application.backend.sensori;

import application.backend.Finestra;
import application.backend.observers.Observer;

public class SensoreFinestra extends Sensore {
	private boolean rilevataEffrazione;
	private Finestra finestra;
	
	public boolean getEffrazione() {
		return this.rilevataEffrazione;
	}
	
	//metodo simulazione	
	public void rilevaEffrazione(boolean casuale) {
	}
}
