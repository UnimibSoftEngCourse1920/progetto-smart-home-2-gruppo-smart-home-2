package application.backend.sensori;

public class SensoreFinestra extends Sensore {
	private boolean rilevataEffrazione;
	
	public SensoreFinestra(Allarme a) {
		super(a);
		this.rilevataEffrazione = false;
	}

	public boolean getEffrazione() {
		return this.rilevataEffrazione;
	}
	
	public void cambiaStato() {
		if(this.getEffrazione())
			this.rilevataEffrazione = false;
		else
			this.rilevataEffrazione = true;
		
	}

	@Override
	public void run() {
		double casuale = Math.random();
		if(casuale >= 0.9) {
			this.rilevataEffrazione= true;
			super.getAllarme().notifica(this);
		}
	}
}
