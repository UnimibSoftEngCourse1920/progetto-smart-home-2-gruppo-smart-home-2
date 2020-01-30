package application.backend.sensori;

import java.util.*;

import application.backend.dominio.*;
import application.frontend.support.Alert;

public class SensoreTemperatura extends TimerTask {

	private String stato;
	private double temperaturaCorrente;
	private double temperaturaDesiderata;
	public static final double TEMPERATURADEFAULT= 17;
	private static final String MISURAZIONE = "Misurazione";
	private Stanza stanza;
	
	public SensoreTemperatura(Stanza stanza) {
		this.stato = "Spento";
		this.temperaturaCorrente = SensoreTemperatura.TEMPERATURADEFAULT;
		this.temperaturaDesiderata = SensoreTemperatura.TEMPERATURADEFAULT;
		this.stanza = stanza;
	}

	public String getStato() {
		return stato;
	}
	
	public double getTemperaturaCorrente() {
		return temperaturaCorrente;
	}
	
	public double getTemperaturaDesiderata() {
		return temperaturaDesiderata;
	}
	
	public void off() {
		this.stato = "Spento";
	}
	
	public void on() {
		this.stato = MISURAZIONE;
	}
	
	public void cambiaStato() {
		if(stato.equals(MISURAZIONE))
			off();
		else
			on();
	}
	
	public void setTemperaturaDesiderata(double temperaturaDesiderata) {
		if(temperaturaDesiderata < 28 && temperaturaDesiderata > 8)
			this.temperaturaDesiderata = temperaturaDesiderata;
		else
			(new Alert()).errore("La temperatura impostata deve essere tra gli 8 e i 28 gradi, estremi esclusi", "Errore");
	}
	
	public void aumentaTemperatura() {
		this.temperaturaCorrente = this.temperaturaCorrente + 0.10;
	}
	
	public void diminuisciTemperatura() {
		if(this.temperaturaCorrente > 5)
			this.temperaturaCorrente = this.temperaturaCorrente - 0.10;
	}

	@Override
	public void run() {
		this.stato = MISURAZIONE;
		for(Finestra f: stanza.getFinestre())
			if(f.isAperta())
				this.diminuisciTemperatura();
		if(this.temperaturaCorrente == this.temperaturaDesiderata) {
			this.stato = "Ottimo";
		}
		else {
			if (this.temperaturaCorrente > this.temperaturaDesiderata) {
				this.stato ="Raffredda";
				this.diminuisciTemperatura();
			}
			else {
				this.stato ="Riscalda";
				this.aumentaTemperatura();
			}
		}
	}
	
	public Stanza getStanza() {
		return this.stanza;
	}
}
