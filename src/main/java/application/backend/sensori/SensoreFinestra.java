package application.backend.sensori;

public class SensoreFinestra extends Sensore {
	private boolean rilevataEffrazione;
	
	public SensoreFinestra() {
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
		//System.out.println(casuale);
		if(casuale == 1) {
			this.rilevataEffrazione= true;
			super.getAllarme().notifica(this);
		}
	}
}
