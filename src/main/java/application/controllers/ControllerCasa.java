package application.controllers;

import application.backend.dominio.*;
import application.backend.sensori.*;
import java.util.*;

public class ControllerCasa {
	private ArrayList<Stanza> stanze ;
	private RobotPulizia robot ;
	private Allarme allarme;
	
	public ControllerCasa() {
		this.stanze = new ArrayList<>(); 
		this.robot = null;
		this.allarme = Allarme.getInstance();
	}

	//DA CONTROLLARE E MODIFICARE
	public void muoviRobot(Stanza s) {
		if(this.robot != null)
			this.robot.muovi(s);
	}
	
	public void addRobot(Stanza s) {
		this.robot = new RobotPulizia(s);
	}
	
	public void addStanza(Stanza s) {
		this.stanze.add(s);
	}
	
	public void cambiaStatoAllarme() {
		if(this.allarme != null) {
			if(this.allarme.isAttivo())
				this.allarme.spegni();
			else 
				this.allarme.accendi();
		}
	}
	
	public void cambiaStatoLavatrice(String nome, int id) {
		Stanza s = getStanza(nome);
		
		if(s!= null && s.getLavatrice() != null && s.getLavatrice().getId() == id)
			s.getLavatrice().cambiaStato();
	}
	
	public void cambiaStatoLavastoviglie(String nome, int id) {
		Stanza s = getStanza(nome);
		
		if(s!= null && s.getLavastoviglie() != null && s.getLavastoviglie().getId() == id)
			s.getLavatrice().cambiaStato();
	}
	
	public void cambiaStatoLampada(String nome, int id) {
		Stanza s = getStanza(nome);
		Lampada l;
		
		if(s != null) {
			l = s.getLampada(id);
			if(l != null)
				l.cambiaStato();
		}
	}
	
	public void cambiaStatoFinestra(String nome, int id) {
		Stanza s = getStanza(nome);
		Finestra f;
		if(s != null) {
			f = s.getFinestra(id);
			if(f != null && !s.getSensoreGas().getFuga())
				f.cambiaStato();
			if(s.getSensoreGas().getFuga())
				System.out.println("c'� una fuga, cazzo fai?");
		}
	}
	
	public void cambiaStatoTapparella(String nome, int id) {
		Stanza s = getStanza(nome);
		Tapparella t;
		
		if(s != null) {
			t = s.getTapparella(id);
			if(t != null && !s.getSensoreGas().getFuga())
				t.cambiaStato();
			if(s.getSensoreGas().getFuga())
				System.out.println("c'� una fuga, cazzo fai?");
		}
	}
	
	public Stanza getStanza(String nome) {
		for(Stanza stanza : getStanze()) {
			if(stanza.getNome().equals(nome))
				return stanza;
		}
		return null;
	}
	
	public List<Stanza> getStanze() {
		return this.stanze;
	}
	
	public String cercaElemento() {
		return null;
	}
	
	public void cambiaTempDesiderata(String nome, double temp) {
		Stanza stanza = getStanza(nome);
		SensoreTemperatura s;
		
		if(stanza != null) {
			s = stanza.getSensoreTemperatura();
			if(s != null)
				s.setTemperaturaDesiderata(temp);
		}
	}
	//------------------------------------------------------------------
	public List<Object> getAllOggettiStanza(Stanza stanza) {
		if(stanza != null) {
			ArrayList<Object> allOggettiStanza = new ArrayList<>();
			allOggettiStanza.addAll(stanza.getLampade());
			allOggettiStanza.addAll(stanza.getFinestre());
			allOggettiStanza.add(stanza.getLavastoviglie());
			allOggettiStanza.add(stanza.getLavatrice());
			return allOggettiStanza;
		}
		else return Collections.emptyList();
	} 
}