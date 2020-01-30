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
		mappa = (ArrayList<Stanza>) casa.getStanzeClone(base);
		
		/*for(int i = 0; i < mappa.size(); i++) {
			System.out.println(mappa.get(i).getNome());
		}*/
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
		//System.out.println(isInFunzione());
		if(isInFunzione()) {
			this.isInFunzione = false;
			this.posizione = this.base;
		}
		else 
			this.isInFunzione = true;
	}

	@Override
	public void run() {
		if((mappa.isEmpty())) {
			//System.out.println(isInFunzione());
			mappa = (ArrayList<Stanza>) casa.getStanzeClone(base);
			
			
			casa.spegniRobot();
		}
		else {
			//System.out.println(this.posizione.getNome());
			this.posizione = mappa.get(0);
			mappa.remove(0);
		}
	}
}
