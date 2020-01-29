package application.backend.sensori;

import java.util.TimerTask;

public abstract class Sensore extends TimerTask{
	private Allarme allarme;
	
	public Sensore(Allarme allarme) {
		this.allarme = allarme;
	}

	public Allarme getAllarme() {
		return this.allarme;
	}
}