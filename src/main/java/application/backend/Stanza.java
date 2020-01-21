package application.backend;

import java.util.ArrayList;

import application.backend.sensori.*; 

public class Stanza {
	private ArrayList<Lampada> lampade = new ArrayList<Lampada>(); 
	private ArrayList<Finestra> finestre = new ArrayList<Finestra>(); 
	private SensoreGas sensoreGas;
	private Radar radar;
	private SensoreTemperatura sensoreTemperatura;
	private Lavatrice lavatrice;
	private Lavastoviglie lavastoviglie;
	private String nome;
	
	public Stanza(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void addLampada(Lampada l) {
		this.lampade.add(l);
	}
	
	public void addFinestra(Finestra f) {
		this.finestre.add(f);
	}
	
	public void addSensoreGas(SensoreGas s) {
		if(this.sensoreGas == null)
			this.sensoreGas = s;
	}
	
	public void addRadar(Radar r) {
		if(this.radar == null)
			this.radar = r;
	}
	
	public void addSensoreTemperatura(SensoreTemperatura s) {
		if(this.sensoreTemperatura == null)
			this.sensoreTemperatura = s;
	}
	
	public void addLavatrice(Lavatrice l) {
		if(this.lavatrice == null)
			this.lavatrice = l;
	}
	
	public void addLavastoviglie(Lavastoviglie l) {
		if(this.lavastoviglie == null) 
			this.lavastoviglie = l;
	}

	public ArrayList<Lampada> getLampade() {
		return lampade;
	}

	public ArrayList<Finestra> getFinestre() {
		return finestre;
	}

	public SensoreGas getSensoreGas() {
		return sensoreGas;
	}

	public Radar getRadar() {
		return radar;
	}

	public SensoreTemperatura getSensoreTemperatura() {
		return sensoreTemperatura;
	}

	public Lavatrice getLavatrice() {
		return lavatrice;
	}

	public Lavastoviglie getLavastoviglie() {
		return lavastoviglie;
	}
}
