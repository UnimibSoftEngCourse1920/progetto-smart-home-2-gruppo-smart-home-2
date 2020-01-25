package application.controllers;

import application.backend.dominio.*;
import application.backend.sensori.*;
import application.frontend.*;
import application.frontend.views.StanzeView;

import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ControllerCasa {
	private JLayeredPane panelPrincipale;
	private ArrayList<Stanza> stanze = new ArrayList<Stanza>(); 
	private RobotPulizia robot;
	private Allarme allarme;
	
	public ControllerCasa(JLayeredPane panelPrincipale) {
		this.panelPrincipale = panelPrincipale;
		creazioneCasa();
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
	
	public ArrayList<Stanza> getStanze() {
		//System.out.print(this.stanze.size());
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
	public ArrayList<Object> getAllOggettiStanza(Stanza stanza) {
		if(stanza != null) {
			ArrayList<Object> allOggettiStanza = new ArrayList<Object>();
			allOggettiStanza.addAll(stanza.getLampade());
			allOggettiStanza.addAll(stanza.getFinestre());
			allOggettiStanza.add(stanza.getLavastoviglie());
			allOggettiStanza.add(stanza.getLavatrice());
			return allOggettiStanza;
		}
		else return null;
	} 
	
	public String[] getNomiStanze() {
		String[] nomiStanze = new String[15];
		ArrayList<Stanza> stanze = getStanze();
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
	}
}