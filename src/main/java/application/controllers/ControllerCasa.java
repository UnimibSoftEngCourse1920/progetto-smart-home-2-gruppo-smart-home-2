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
		robot.cambiaStato();
		timer.schedule(robot, 30000, 30000);
	}
	
	public void spegniRobot() {
		robot.cambiaStato();
		main.getRobotView().setStato();
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
			if(stanza.getNome().equals(nome)) {
				//System.out.print(stanza.getLavastoviglie().getId());
				return stanza;
			}
				
		}
		return null;
	}
	
	public List<Stanza> getStanze() {
		return this.stanze;
	}
	
	public List<Stanza> getStanzeClone() {
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
	
	public MainJFrame getMain() {
		return main;
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

			if(!stanza.getLampade().isEmpty()) {
				
				allOggettiStanza.addAll(stanza.getLampade());
				//System.out.println(allOggettiStanza.size());
			}
			if(stanza.getLavatrice() != null) {
				allOggettiStanza.add(stanza.getLavatrice());
				//System.out.println(allOggettiStanza.size());
			}
			if(stanza.getLavastoviglie() != null) {
				allOggettiStanza.add(stanza.getLavastoviglie());
				//System.out.println(allOggettiStanza.size());
			}
			if(stanza.getSensoreTemperatura() != null) {
				//System.out.println("dhkjasjdhsadh");
				allOggettiStanza.add(stanza.getSensoreTemperatura());
				//System.out.println(allOggettiStanza.size()+"ciao");
			}
			if(!stanza.getFinestre().isEmpty()) {
				allOggettiStanza.addAll(stanza.getFinestre());
				//System.out.println(allOggettiStanza.size());
			}
			
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
	
	public Stanza getStanzaElemento(Object e) {
		if(e instanceof SensoreTemperatura) {
			return ((SensoreTemperatura) e).getStanza();
		}
		else if(e instanceof Lavatrice) {
			for(int i = 0; i < getStanze().size(); i++) {
				if(getStanze().get(i).getLavatrice().getId() == ((Lavatrice) e).getId())
					return getStanze().get(i);
			}
			return null;
		}
		else if(e instanceof Lavastoviglie) {
			for(int i = 0; i < getStanze().size(); i++) {
				if(getStanze().get(i).getLavastoviglie().getId() == ((Lavastoviglie) e).getId())
					return getStanze().get(i);
			}
			return null;
		}
		else {
			((RobotPulizia) e).getBase();
		}
		return null;
	}
	
	
	public void creazioneCasa() {
		Stanza cameraLetto = new Stanza("Camera da Letto");
		Stanza cucina = new Stanza("Cucina");
		Stanza bagno = new Stanza("Bagno");
		Stanza sala = new Stanza("Sala");
		addStanza(cucina);
		addStanza(cameraLetto);
		addStanza(bagno);
		addStanza(sala);
		
		//CUCINA-----------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraCucina = new SensoreFinestra();
		Finestra finestraCucina = new Finestra(new Tapparella(1), sensoreFinestraCucina);
		Lampada lampadaCucina = new Lampada(1);
		Lavastoviglie lavastoviglieCucina = new Lavastoviglie(1);
		SensoreGas sensoreGasCucina =new SensoreGas();
		
		cucina.addLampada(lampadaCucina);
		cucina.addFinestra(finestraCucina);
		cucina.addLavastoviglie(lavastoviglieCucina);
		cucina.addSensoreTemperatura();
		cucina.addSensoreGas(sensoreGasCucina);
		cucina.startTimerEventi();
		//System.out.println(cucina.getSensoreTemperatura());
		
		
		//CAMERA DA LETTO-----------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraCameraMatrimoniale = new SensoreFinestra();
		Lampada lampadaCameraMatrimoniale = new Lampada(2);
		Finestra finestraCameraMatrimoniale = new Finestra(new Tapparella(2), sensoreFinestraCameraMatrimoniale);
		
		cameraLetto.addLampada(lampadaCameraMatrimoniale);
		cameraLetto.addFinestra(finestraCameraMatrimoniale);
		cameraLetto.addSensoreTemperatura();
		cameraLetto.startTimerEventi();
		
		
		//BAGNO-------------------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraBagno = new SensoreFinestra();
		Finestra finestraBagno = new Finestra(new Tapparella(3), sensoreFinestraBagno);
		Lampada lampadaBagno = new Lampada(3);
		Lavatrice lavatriceBagno = new Lavatrice(1);
		
		bagno.addLampada(lampadaBagno);
		bagno.addFinestra(finestraBagno);
		bagno.addLavatrice(lavatriceBagno);
		bagno.addSensoreTemperatura();
		bagno.startTimerEventi();
		
		//SALA-------------------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraSala = new SensoreFinestra();
		Finestra finestraSala = new Finestra(new Tapparella(4), sensoreFinestraSala);
		Lampada lampadaSala = new Lampada(4);
		
		sala.addLampada(lampadaSala);
		sala.addFinestra(finestraSala);
		sala.startTimerEventi();
		
	}
}