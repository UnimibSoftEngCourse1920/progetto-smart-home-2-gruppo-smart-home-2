package application.backend.dominio;

import application.backend.sensori.SensoreFinestra;

public class Finestra {
	private int id;
	private boolean isAperta;
	private Tapparella tapparella;
	private SensoreFinestra sensore;
	
	public Finestra(Tapparella tapparella, SensoreFinestra sensore) {
		this.id = tapparella.getId();
		this.isAperta = false;
		this.tapparella = tapparella;
		this.sensore = sensore;
	}
	
	public void riallocaSensore() {
		this.sensore = new SensoreFinestra(this.sensore.getAllarme());
	}
	
	public boolean isAperta() {
		return this.isAperta;
	}
	
	public void cambiaStato() {
		if(isAperta())
			this.isAperta = false;
		else this.isAperta = true;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Tapparella getTapparella() {
		return this.tapparella;
	}

	public SensoreFinestra getSensore() {
		return sensore;
	}
	
	
}
