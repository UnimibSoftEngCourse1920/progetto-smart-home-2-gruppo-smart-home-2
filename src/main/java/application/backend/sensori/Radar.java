package application.backend.sensori;

public class Radar extends Sensore {
	private boolean rilevatoMovimento;

	public boolean isRilevatoMovimento() {
		return rilevatoMovimento;
	}
	
	public void cambiaStato() {
		if(this.isRilevatoMovimento())
			this.rilevatoMovimento = false;
		else
			this.rilevatoMovimento = true;
		
	}
	
	public void rilevaMovimento(boolean casuale) {
	}
} 
