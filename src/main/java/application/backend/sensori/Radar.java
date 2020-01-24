package application.backend.sensori;

public class Radar extends Sensore {
	private boolean rilevatoMovimento;
	
	public Radar() {
		this.rilevatoMovimento = false;
	}

	public boolean getMovimento() {
		return rilevatoMovimento;
	}
	
	public void cambiaStato() {
		if(this.getMovimento())
			this.rilevatoMovimento = false;
		else
			this.rilevatoMovimento = true;
		
	}

	@Override
	public void run() {
		double casuale = (int)(Math.random()*10);
		if(casuale == 1) {
			this.rilevatoMovimento= true;
			super.getAllarme().notifica();
		}
	}
} 
