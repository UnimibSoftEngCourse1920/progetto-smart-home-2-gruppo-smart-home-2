package application.backend.sensori;

public class Radar extends Sensore {
	public boolean rilevatoMovimento;

	public boolean isRilevatoMovimento() {
		return rilevatoMovimento;
	}
	
	public void cambiaStato() {
		if(this.isRilevatoMovimento())
			this.rilevatoMovimento = false;
		else
			this.rilevatoMovimento = true;
		
	}
}
