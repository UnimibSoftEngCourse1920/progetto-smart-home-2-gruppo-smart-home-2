package application.controllers;

import application.backend.dominio.*;
import application.backend.sensori.*;

import application.frontend.*;
import application.frontend.views.StanzeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ControllerCasa {
	private JLayeredPane panelPrincipale;
	private ArrayList<Stanza> stanze ;
	private RobotPulizia robot ;
	private Allarme allarme;
	
	public ControllerCasa(JLayeredPane panelPrincipale) {
		this.panelPrincipale = panelPrincipale;
		this.stanze = new ArrayList<>(); 
		this.robot = null;
		this.allarme = Allarme.getInstance();
		creazioneCasa();
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
	
	public void cambiaStatoElemento(String nome, int id) {
		Stanza s = getStanza(nome);
		ElementoProgrammabile e;
		if(s!= null) {
			 e = s.getElemento(id);
			if(e != null)
				e.cambiaStato();
		}
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
				System.out.println("c'è una fuga, cazzo fai?");
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
				System.out.println("c'è una fuga, cazzo fai?");
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

			if(stanza.getLampade().size() != 0)
				allOggettiStanza.addAll(stanza.getLampade());
			if(stanza.getFinestre().size() != 0)
				allOggettiStanza.addAll(stanza.getFinestre());
			if(stanza.getElementi().size() != 0)
				allOggettiStanza.add(stanza.getElementi());

			return allOggettiStanza;
		}
		else return Collections.emptyList();
	} 
	
	public String[] getNomiStanze() {
		String[] nomiStanze = new String[15];
		List<Stanza> stanze = getStanze();
		//System.out.println(stanze.size());
		
		for(int i = 0; i < stanze.size(); i++) {
			//System.out.println(stanze.get(i).getNome());
			
			nomiStanze[i] = stanze.get(i).getNome();
		}
		
		return nomiStanze;
	}
	
	
	public void creazioneCasa() {
		Stanza cameraMatrimoniale = new Stanza("Camera Matrimoniale");
		Stanza cucina = new Stanza("Cucina");
		addStanza(cameraMatrimoniale);
		addStanza(cucina);
		
		//CUCINA-----------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraCucina = new SensoreFinestra();
		Lampada lampadaCucina = new Lampada(1);
		cucina.addLampada(lampadaCucina);
		Finestra finestraCucina = new Finestra(new Tapparella(1), sensoreFinestraCucina);
		cucina.addFinestra(finestraCucina);
		
		//CAMERA MATRIMONIALE-----------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraCameraMatrimoniale = new SensoreFinestra();
		Lampada lampadaCameraMatrimoniale = new Lampada(1);
		cameraMatrimoniale.addLampada(lampadaCameraMatrimoniale);
		Finestra finestraCameraMatrimoniale = new Finestra(new Tapparella(1), sensoreFinestraCameraMatrimoniale);
		cameraMatrimoniale.addFinestra(finestraCameraMatrimoniale);
	}
}