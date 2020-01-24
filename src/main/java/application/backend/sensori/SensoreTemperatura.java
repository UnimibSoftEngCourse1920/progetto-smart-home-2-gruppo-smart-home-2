package application.backend.sensori;

import java.util.*;

public class SensoreTemperatura extends TimerTask {

	private String stato;
	private double temperaturaCorrente;
	private double temperaturaDesiderata;
	private static final double TEMPERATURADEFAULT= 17;
	
	public SensoreTemperatura() {
		this.stato = "off";
		this.temperaturaCorrente = SensoreTemperatura.TEMPERATURADEFAULT;
		this.temperaturaDesiderata = SensoreTemperatura.TEMPERATURADEFAULT;
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
		this.stato = "off";
	}
	
	public void on() {
		this.stato = "Misurazione";
	}
	
	public void setTemperaturaDesiderata(double temperaturaDesiderata) {
		this.temperaturaDesiderata = temperaturaDesiderata;
	}
	
	public void aumentaTemperatura() {
		this.temperaturaCorrente = this.temperaturaCorrente + 0.10;
	}
	
	public void diminuisciTemperatura() {
		this.temperaturaCorrente = this.temperaturaCorrente - 0.10;
	}

	@Override
	public void run() {
		this.stato = "Misurazione";
		if(this.temperaturaCorrente == this.temperaturaDesiderata)
			this.stato = "Ottimo";
		else if (this.temperaturaCorrente > this.temperaturaDesiderata) {
			this.stato ="Raffredda";
			this.diminuisciTemperatura();
			}
		else if (this.temperaturaCorrente < this.temperaturaDesiderata) {
			this.stato ="Riscalda";
			this.aumentaTemperatura();
		}
	}
}
