package application.backend.sensori;

public class SensoreGas extends Sensore {
	private boolean rilevataFuga;
	
	public boolean getFuga() {
		return this.rilevataFuga;
	}
	
	//metodo simulazione	
	public void rilevaFuga(boolean casuale) {
	}
}
