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
		emergenza = true;
		if(isAttivo()) {
			if(s instanceof SensoreGas) {
				this.notificaSensore();
				casa.getMain().getPanelAllarme().setLabelGas();
				}
			else  {
				if(s instanceof SensoreFinestra) 
					casa.getMain().getPanelAllarme().setLabelEffrazione();
				if(s instanceof Radar) {
					casa.getMain().getPanelAllarme().setLabelMovimenti();
				}
				for (Stanza stanza: casa.getStanze()) 
					stanza.stopTimerEventi();
			}
		}
	}
	
	public void notificaSensore() {
		for (Stanza stanza: casa.getStanze()) {
			stanza.stopTimerEventi();
			if(stanza.getSensoreGas()!= null && stanza.getSensoreGas().getFuga()) {
				for (Finestra f : stanza.getFinestre())
					if(!f.isAperta())
						f.cambiaStato();
			}
		}
	}
	
	public void terminaEmergenza() {
		this.emergenza = false;
		for (Stanza stanza: casa.getStanze()) {
			if(stanza.getSensoreGas()!= null && stanza.getSensoreGas().getFuga()) {
				stanza.getSensoreGas().cambiaStato();
				casa.getMain().getPanelAllarme().setLabelGas();
			}
			if(stanza.getRadar() != null && stanza.getRadar().getMovimento()) {
				stanza.getRadar().cambiaStato();
				casa.getMain().getPanelAllarme().setLabelMovimenti();
			}
			if(stanza.getFinestre() != null) {
				for (Finestra f : stanza.getFinestre())
					if (f.getSensore().getEffrazione())
						f.cambiaStato();
				casa.getMain().getPanelAllarme().setLabelEffrazione();
			}
			stanza.startTimerEventi();
		}
	}
	
	public boolean isAttivo() {
		return this.isAttivo;
	}
	
	public boolean isEmergenza() {
		return emergenza;
	}

	public void accendi() {
			this.isAttivo = true;
	}
	
	public void spegni() {
			this.isAttivo = false;
	}
}
