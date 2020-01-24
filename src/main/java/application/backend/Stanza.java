package application.backend;

import java.util.*;

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
	private Timer timerEventi;
	private Timer timerTemperatura;
	
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
	
	public void addSensoreTemperatura() {
		if(this.sensoreTemperatura == null)
			this.sensoreTemperatura = new SensoreTemperatura(this);
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
	
	public Lampada getLampada(int id) {
		for(Lampada lampada : getLampade()) {
			if(lampada.getId() == id)
				return lampada;
		}
		return null;
	}
	
	public Finestra getFinestra(int id) {
		for(Finestra finestra : getFinestre()) {
			if(finestra.getId() == id)
				return finestra;
		}
		return null;
	}
	
	public Tapparella getTapparella(int id) {
		for(Finestra finestra : getFinestre()) {
			if(finestra.getTapparella().getId() == id)
				return finestra.getTapparella();
		}
		return null;
	}
	
	public void startTimerEventi() {
		timerEventi = new Timer();
		if(this.sensoreGas != null)
			timerEventi.schedule(this.sensoreGas , 10000, 10000 );
		if(this.radar != null)
			timerEventi.schedule(this.sensoreGas , 10000, 10000 );
		for (Finestra f: finestre) {
			timerEventi.schedule(f.getSensore() , 10000, 10000 );
		}
	}
	
	public void stopTimerEventi() {
		timerEventi.cancel();
	}
	
	public void accendiTermostato() {
		this.sensoreTemperatura.on();
		this.timerTemperatura = new Timer();
		this.timerTemperatura.schedule(this.sensoreTemperatura, 0, 10000);
	}
	
	public void spegniTermostato() {
		this.sensoreTemperatura.off();
		this.timerTemperatura.cancel();
	}
}
