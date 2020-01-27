package application.backend.sensori;

import application.backend.dominio.*;
import application.controllers.*;
import application.frontend.support.Alert;

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
		emergenza = true;
		System.out.println("Chiamo il gentodigiotto");
		if(s instanceof SensoreGas) {
			this.notificaSensore((SensoreGas)s);
			}
		else if(isAttivo()) {
			for (Stanza stanza: casa.getStanze()) 
				stanza.stopTimerEventi();
		}
	}
	
	public void notificaSensore(SensoreGas s) {
		for (Stanza stanza: casa.getStanze()) {
			stanza.stopTimerEventi();
			if(stanza.getSensoreGas() == s)
				for (Finestra f:stanza.getFinestre())
					if(!f.isAperta())
						f.cambiaStato();
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
			//System.out.println("Non puoi spegnere l'allarme durante un'emergenza");
			(new Alert()).errore("Non puoi spegnere l'allarme durante un'emergenza", "Errore");
		else
			this.isAttivo = false;
	}
}
