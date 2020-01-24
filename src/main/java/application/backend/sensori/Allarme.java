package application.backend.sensori;

import application.backend.dominio.*;
import application.controllers.*;

public class Allarme {
	private static Allarme istance = null;
	private ControllerCasa casa;
	private boolean isAttivo = false;
	private boolean emergenza = false;
	
	private Allarme() {}
	
	public static Allarme getInstance() {
		if(istance == null) 
			istance = new Allarme();
		return istance;
	}
	
	public void aggiungiCasa(ControllerCasa casa) {
		if(this.casa == null)
			this.casa=casa;
	}
	
	public void notifica(Sensore s) {
		if(s instanceof SensoreGas) {
			System.out.println("Chiamo il gentodigiotto");
			emergenza = true;
			for (Stanza stanza: casa.getStanze()) {
				stanza.stopTimerEventi();
				if(stanza.getSensoreGas() == s)
					for (Finestra f:stanza.getFinestre())
						if(!f.isAperta())
							f.cambiaStato();
				}
			}
		else if(isAttivo()) {
			emergenza = true;
			System.out.println("Chiamo il gentodigiotto");
			for (Stanza stanza: casa.getStanze()) 
				stanza.stopTimerEventi();
		}
	}
	
	public void terminaEmergenza() {
		for (Stanza stanza: casa.getStanze()) {
			if(stanza.getSensoreGas().getFuga())
				stanza.getSensoreGas().cambiaStato();
			if(stanza.getRadar().getMovimento())
				stanza.getRadar().cambiaStato();
			if(stanza.getFinestre() != null)
				for (Finestra f : stanza.getFinestre())
					if (f.getSensore().getEffrazione())
						f.cambiaStato();
			stanza.startTimerEventi();
		}
	}
	
	public boolean isAttivo() {
		return this.isAttivo;
	}
	
	public void accendi() {
			this.isAttivo = true;
	}
	
	public void spegni() {
		if(emergenza)
			System.out.println("Non puoi spegnere l'allarme durante un'emergenza");
		else
			this.isAttivo = false;
	}
}
