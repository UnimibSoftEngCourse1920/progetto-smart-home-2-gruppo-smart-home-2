package application.controllers;

import application.backend.dominio.*;
import application.backend.sensori.*;

import application.frontend.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class ControllerCasa {
	private MainJFrame main;
	private ArrayList<Stanza> stanze ;
	private RobotPulizia robot ;
	private Allarme allarme;
	private Timer timer= new Timer();
	
	public ControllerCasa(MainJFrame main) {
		this.main = main;
		this.stanze = new ArrayList<>(); 
		this.robot = null;
		this.allarme = Allarme.getInstance();
		
		creazioneCasa();
	}

	public void accendiRobot() {
		timer.schedule(robot, 30000, 30000);
	}
	
	public void spegniRobot() {
		timer.cancel();
	}
	
	public void addRobot(Stanza s) {
		this.robot = new RobotPulizia(s,this);
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
	
	public void cambiaStatoLavatrice(String nome) {
		Stanza s = getStanza(nome);
		Lavatrice e;
		if(s!= null) {
			 e = s.getLavatrice();
			if(e != null)
				e.cambiaStato();
		}
	}
	
	public void cambiaStatoLavastoviglie(String nome) {
		Stanza s = getStanza(nome);
		Lavastoviglie e;
		if(s!= null) {
			 e = s.getLavastoviglie();
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
		@SuppressWarnings("unchecked")
		List<Stanza> clone = (List<Stanza>)this.stanze.clone();
		return clone;
	}
	
	public RobotPulizia getRobot() {
		return this.robot;
	}
	
	public Allarme getAllarme() {
		return this.allarme;
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

			if(!stanza.getLampade().isEmpty())
				allOggettiStanza.addAll(stanza.getLampade());
			if(!stanza.getFinestre().isEmpty())
				allOggettiStanza.addAll(stanza.getFinestre());
			if(stanza.getLavatrice() != null)
				allOggettiStanza.add(stanza.getLavatrice());
			if(stanza.getLavastoviglie() != null)
				allOggettiStanza.add(stanza.getLavastoviglie());

			return allOggettiStanza;
		}
		else return Collections.emptyList();
	} 
	
	public String[] getNomiStanze() {
		String[] nomiStanze = new String[15];
		List<Stanza> stanzeTemp = getStanze();
		//System.out.println(stanzeTemp.size());
		
		for(int i = 0; i < stanzeTemp.size(); i++) {
			//System.out.println(stanzetemp.get(i).getNome());
			
			nomiStanze[i] = stanzeTemp.get(i).getNome();
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
		Finestra finestraCucina = new Finestra(new Tapparella(1), sensoreFinestraCucina);
		SensoreTemperatura sensoreTemperaturaCucina = new SensoreTemperatura(cucina);
		Lampada lampadaCucina = new Lampada(1);
		Lavastoviglie lavastoviglieCucina = new Lavastoviglie(1);
		
		
		cucina.addLampada(lampadaCucina);
		cucina.addFinestra(finestraCucina);
		cucina.addSensoreTemperatura();
		
		//CAMERA MATRIMONIALE-----------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraCameraMatrimoniale = new SensoreFinestra();
		Lampada lampadaCameraMatrimoniale = new Lampada(1);
		Finestra finestraCameraMatrimoniale = new Finestra(new Tapparella(1), sensoreFinestraCameraMatrimoniale);
		
		cameraMatrimoniale.addLampada(lampadaCameraMatrimoniale);
		cameraMatrimoniale.addFinestra(finestraCameraMatrimoniale);
	}
}