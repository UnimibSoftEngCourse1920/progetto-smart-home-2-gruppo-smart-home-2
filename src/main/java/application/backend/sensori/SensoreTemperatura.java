package application.backend.sensori;

import application.backend.ElementoProgrammabile;

public class SensoreTemperatura extends ElementoProgrammabile {

	public String stato;
	public double temperaturaCorrente;
	public double temperaturaDesiderata;
	public static double temperaturaDefault= 17;
	
	public SensoreTemperatura() {
		this.stato = "off";
		this.temperaturaCorrente = this.temperaturaDefault;
		this.temperaturaDesiderata = this.temperaturaDefault;
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

	public void aggiornaStato() {
		if(this.temperaturaCorrente == this.temperaturaDesiderata)
			this.stato = "Ottimo";
		else if (this.temperaturaCorrente > this.temperaturaDesiderata)
			this.stato ="Raffredda";
		else if (this.temperaturaCorrente < this.temperaturaDesiderata)
			this.stato ="Riscalda";
	}
	
	public void aumentaTemperatura() {
		this.temperaturaCorrente = this.temperaturaCorrente + 0.10;
		//this.stato = "Misurazione";
	}
	
	public void diminusiciTemperatura() {
		this.temperaturaCorrente = this.temperaturaCorrente - 0.10;
		//this.stato = "Misurazione";
	}
}
