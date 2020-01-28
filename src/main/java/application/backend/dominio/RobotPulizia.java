package application.backend.dominio;

import java.util.ArrayList;
import java.util.TimerTask;

import application.controllers.ControllerCasa;


public class RobotPulizia extends TimerTask{

	private boolean isInFunzione;
	private Stanza base;
	private Stanza posizione;
	private ControllerCasa casa;
	private ArrayList<Stanza> mappa;
	
	public RobotPulizia(Stanza base, ControllerCasa casa) {
		this.isInFunzione = false;
		this.base = base;
		this.posizione = base;
		this.casa = casa;
		mappa = (ArrayList<Stanza>) casa.getStanze();
	}
	
	public Stanza getBase() {
		return base;
	}
	
	public Stanza getPosizione() {
		return posizione;
	}
	
	public boolean isInFunzione() {
		return isInFunzione;
	}
	
	public void cambiaStato() {
		if(isInFunzione()) {
			this.isInFunzione = false;
			this.posizione = this.base;
		}
		else 
			this.isInFunzione = true;
	}

	@Override
	public void run() {
		if((mappa.isEmpty()) || (mappa.size() == 1 && mappa.get(0) == base)) {
			this.cambiaStato();
			mappa = (ArrayList<Stanza>) casa.getStanze();
			casa.spegniRobot();
		}
		else
			if(mappa.get(0) != base) 
					this.posizione = mappa.get(0);
				mappa.remove(0);
	}
}
