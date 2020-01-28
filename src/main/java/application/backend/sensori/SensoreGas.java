package application.backend.sensori;

public class SensoreGas extends Sensore {
	private boolean rilevataFuga;
	
	public SensoreGas() {
		this.rilevataFuga =false;
	}

	public boolean getFuga() {
		return this.rilevataFuga;
	}

	public void cambiaStato() {
		if(getFuga())
			this.rilevataFuga = false;
		else
			this.rilevataFuga = true;
		
	}
	
	@Override
	public void run() {
		double casuale = Math.random();
		System.out.println(casuale);
		if(casuale == 1) {
			this.rilevataFuga= true;
			super.getAllarme().notifica(this);
		}
	}
}
