package application.backend.sensori;

public class Radar extends Sensore {
	private boolean rilevatoMovimento;
	
	public Radar(Allarme a) {
		super(a);
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
		double casuale = Math.random();
		System.out.println(casuale);
		if(casuale >= 0.5) {
			System.out.println(this);
			this.rilevatoMovimento= true;
			super.getAllarme().notifica(this);
		}
	}
} 
