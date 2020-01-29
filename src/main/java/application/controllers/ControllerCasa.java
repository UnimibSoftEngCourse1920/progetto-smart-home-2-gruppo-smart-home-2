package application.controllers;

import application.backend.dominio.*;
import application.backend.sensori.*;

import application.frontend.*;
import application.frontend.support.Alert;

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
		this.allarme.aggiungiCasa(this);
		
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
			if(this.allarme.isAttivo()) {
				this.allarme.spegni();
				for (Stanza s:this.getStanze())
					s.stopTimerEventi();
			}
			else {
				this.allarme.accendi();
				for (Stanza s:this.getStanze())
					s.startTimerEventi();
			}
		}
	}
	
	public void cambiaStatoSensoreTemperatura(Stanza s) {
		if(s != null) {
			if(s.getSensoreTemperatura().getStato() == "Spento")
				s.accendiTermostato();
			else
				s.spegniTermostato();
		}
	}
	
	public void cambiaStatoLavatrice(Stanza s) {
		if(s!= null) {
			if(s.getLavatrice() != null)
				s.getLavatrice().cambiaStato();
		}
	}
	
	public void cambiaStatoLavastoviglie(Stanza s) {
		if(s!= null) {
			if(s.getLavastoviglie() != null)
				s.getLavastoviglie().cambiaStato();
		}
	}
	
	public void cambiaStatoLampada(int id, Stanza s) {
		if(s != null) {
			if(s.getLampada(id) != null)
				s.getLampada(id).cambiaStato();
		}
	}
	
	public void cambiaStatoFinestra(int id, Stanza s) {
		if(s != null) {
			if(s.getFinestra(id) != null && !s.getSensoreGas().getFuga())
				s.getFinestra(id).cambiaStato();
			if(s.getSensoreGas().getFuga())
				System.out.println("c'� una fuga, cazzo fai?");
		}
	}
	
	public void cambiaStatoTapparella(int id, Stanza s) {		
		if(s != null) {
			if(s.getFinestra(id).getTapparella() != null && !s.getSensoreGas().getFuga())
				s.getFinestra(id).getTapparella().cambiaStato();
			if(s.getSensoreGas().getFuga())
				System.out.println("c'� una fuga, cazzo fai?");
		}
	}
	
	public boolean lampadaIsAccesa(Lampada l) {
		return l.isAccesa();
	}
	
	public boolean finestraIsAperta(Finestra f) {
		return f.isAperta();
	}
	
	public boolean tapparellaIsAperta(Tapparella t) {
		return t.isAperta();
	}
	
	public boolean lavatriceIsInFunzione(Lavatrice l) {
		return l.isInFunzione();
	}
	
	public boolean lavastoviglieIsInFunzione(Lavastoviglie l) {
		return l.isInFunzione();
	}
	
	public String getStatoSensoreTemperatura(SensoreTemperatura s) {
		return s.getStato();
	}
	
	public int getIdLampada(Lampada l) {
		return l.getId();
	}
	
	public int getIdFinestra(Finestra f) {
		return f.getId();
	}
	
	public int getIdTapparella(Tapparella t) {
		return t.getId();
	}
	
	public double getTemperaturaCorrenteSensoreTemperatura(SensoreTemperatura s) {
		return s.getTemperaturaCorrente();
	}
	
	public double getTemperaturaDesiderataSensoreTemperatura(SensoreTemperatura s) {
		return s.getTemperaturaDesiderata();
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
	
	public String getNomeStanza(Stanza s) {
		return s.getNome();
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
		SensoreFinestra sensoreFinestraCucina = new SensoreFinestra(allarme);
		Finestra finestraCucina = new Finestra(new Tapparella(1), sensoreFinestraCucina);
		Lampada lampadaCucina = new Lampada(1);
		Lavastoviglie lavastoviglieCucina = new Lavastoviglie(1);
		SensoreGas sensoreGasCucina =new SensoreGas(allarme);
		
		cucina.addLampada(lampadaCucina);
		cucina.addFinestra(finestraCucina);
		cucina.addLavastoviglie(lavastoviglieCucina);
		cucina.addSensoreTemperatura();
		cucina.addSensoreGas(sensoreGasCucina);
		//System.out.println(cucina.getSensoreTemperatura());
		
		
		//CAMERA DA LETTO-----------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraCameraMatrimoniale = new SensoreFinestra(allarme);
		Lampada lampadaCameraMatrimoniale = new Lampada(2);
		Finestra finestraCameraMatrimoniale = new Finestra(new Tapparella(2), sensoreFinestraCameraMatrimoniale);
		
		cameraLetto.addLampada(lampadaCameraMatrimoniale);
		cameraLetto.addFinestra(finestraCameraMatrimoniale);
		cameraLetto.addSensoreTemperatura();
		
		
		//BAGNO-------------------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraBagno = new SensoreFinestra(allarme);
		Finestra finestraBagno = new Finestra(new Tapparella(3), sensoreFinestraBagno);
		Lampada lampadaBagno = new Lampada(3);
		Lavatrice lavatriceBagno = new Lavatrice(1);
		
		bagno.addLampada(lampadaBagno);
		bagno.addFinestra(finestraBagno);
		bagno.addLavatrice(lavatriceBagno);
		bagno.addSensoreTemperatura();
		
		//SALA-------------------------------------------------------------------------------------
		SensoreFinestra sensoreFinestraSala = new SensoreFinestra(allarme);
		Finestra finestraSala = new Finestra(new Tapparella(4), sensoreFinestraSala);
		Lampada lampadaSala = new Lampada(4);
		
		sala.addLampada(lampadaSala);
		sala.addFinestra(finestraSala);
		
	}
}