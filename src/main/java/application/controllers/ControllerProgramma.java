package application.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import application.backend.dominio.*;
import application.backend.programmi.*;
import application.backend.sensori.SensoreTemperatura;

public class ControllerProgramma {

	private ControllerCasa casa;
	private int counter;
	private ArrayList<Programma> programmi;

	public ControllerProgramma(int counter) {
		this.counter = counter;
		this.programmi = new ArrayList<>();
		
		/*try {
			this.jsonProgrammi =  new FileReader(getClass().getClassLoader().getResource("programmi.json").getFile());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}*/
		
		creazioneProgrammi();
	}
	
	public ControllerProgramma() {
		this(0);
	}
	
	public void creaProgrammaGiornaliero(LocalTime inizio, LocalTime fine, double valoreDiSetting, Object e) {
		this.programmi.add(new ProgrammaGiornaliero(counter,inizio,fine,valoreDiSetting,e));
		this.counter++;
	}
	
	public void creaProgrammaSettimanale() {
		this.programmi.add(new ProgrammaSettimanale(counter));
		this.counter++;
	}
	
	public void eliminaProgramma(int id) {
		for (Programma p : programmi) {
			if (p.getId() == id) 
				programmi.remove(p);
		}
	}
	
	public void aggiungiElemento(int id,Object e) {
		for (Programma p : programmi) {
			if (p.getId() == id) 
				p.aggiungiElemento(e);
		}
	}
	
	public void aggiungiGiornoASettimana(int id, DayOfWeek giorno, LocalTime inizio, LocalTime fine, double valoreDiSetting, Object e) {
		ProgrammaSettimanale ps;
		for (Programma p : programmi) {
			if (p.getId() == id && p instanceof ProgrammaSettimanale) {
				ps = (ProgrammaSettimanale)p;
				ps.aggiungiGiorno(giorno, inizio, fine, valoreDiSetting, e);
			}
		}
	}
	
	public void accensione(LocalTime ora, DayOfWeek giorno) {
		LocalTime inizio;
		for (Programma p : programmi) {
			if(p instanceof ProgrammaGiornaliero) {
				inizio = ((ProgrammaGiornaliero) p).getInizio();
				if(inizio.compareTo(ora)==0 || inizio.compareTo(ora) <0) {
					if(p.getElemento() instanceof SensoreTemperatura)
						cambiaTemperatura(((ProgrammaGiornaliero) p).getValoreDiSetting(), (SensoreTemperatura) p.getElemento());
					else
						cambiaStatoElemento(p.getElemento(),false);
					}
				}
			if (p instanceof ProgrammaSettimanale){
				inizio= ((ProgrammaSettimanale) p).getInizio(giorno);
				if(inizio.compareTo(ora)==0 || inizio.compareTo(ora) <0) {
					if(p.getElemento() instanceof SensoreTemperatura)
						cambiaTemperatura(((ProgrammaSettimanale) p).getValoreDiSetting(giorno), (SensoreTemperatura) p.getElemento());
					else
						cambiaStatoElemento(p.getElemento(),false);
				}
			}
		}
	}
	
	private void cambiaTemperatura(double valore, SensoreTemperatura e) {
		e.setTemperaturaDesiderata(valore);
	}

	public void spegnimento(LocalTime ora, DayOfWeek giorno) {
		LocalTime fine;
		for (Programma p : programmi) {
			if(p instanceof ProgrammaSettimanale) {
				fine = ((ProgrammaSettimanale) p).getFine(giorno);
				if(fine != null && (fine.compareTo(ora)==0 || fine.compareTo(ora)<0)) {
					if(p.getElemento() instanceof SensoreTemperatura)
						cambiaTemperatura(SensoreTemperatura.TEMPERATURADEFAULT, (SensoreTemperatura) p.getElemento());
					else
						cambiaStatoElemento(p.getElemento(),true);
				}
			}
			else if(p instanceof ProgrammaGiornaliero) {
				fine = ((ProgrammaGiornaliero) p).getFine();
				if(fine != null && (fine.compareTo(ora)==0 || fine.compareTo(ora) <0)) {
					if(p.getElemento() instanceof SensoreTemperatura)
						cambiaTemperatura(SensoreTemperatura.TEMPERATURADEFAULT, (SensoreTemperatura) p.getElemento());
					else
						cambiaStatoElemento(p.getElemento(),true);
				}
			}
		}
	}
	
	public void cambiaStatoElemento(Object e, boolean stato) {
		if(e instanceof RobotPulizia && ((RobotPulizia) e).isInFunzione()==stato && stato == false)
			casa.accendiRobot();
		if(e instanceof RobotPulizia && ((RobotPulizia) e).isInFunzione()==stato && stato == true)
			casa.spegniRobot();
		if(e instanceof Lavatrice && ((Lavatrice) e).isInFunzione()== stato)
			((Lavatrice) e).cambiaStato();
		if(e instanceof Lavastoviglie && ((Lavastoviglie) e).isInFunzione()== stato)
			((Lavastoviglie) e).cambiaStato();
	}
	
	public ArrayList<Programma> getAllProgrammi() {
		return this.programmi;
	}
	
	public Programma getProgramma(int id) {
		for(Programma p : getAllProgrammi()) {
			if(p.getId() == id) 
				return p;
		}
		
		return null;
	}
	
	public ControllerCasa getCasa() {
		return this.casa;
	}
	
	public void setCasa(ControllerCasa casa) {
		this.casa = casa;
	}
	
	/*
	//@SuppressWarnings("uncheched")
	public void scriviAllProgrammiJson() {
		//JSONObject dettaglioProgramma = new JSONObject();
		
		JSONArray arrayProgrammi = new JSONArray();
		
		for(Programma p : getAllProgrammi()) { 
			JSONObject objectProgrammi = new JSONObject();
			JSONObject dettaglioProgramma = new JSONObject();
			if(p instanceof ProgrammaGiornaliero) {
				ProgrammaGiornaliero programmaGiornaliero = (ProgrammaGiornaliero) p;
				dettaglioProgramma.put("ID", programmaGiornaliero.getId());
				dettaglioProgramma.put("Inizio", programmaGiornaliero.getInizio());
				dettaglioProgramma.put("Fine", programmaGiornaliero.getFine());
				dettaglioProgramma.put("Valore Setting", programmaGiornaliero.getValoreDiSetting());
				objectProgrammi.put(programmaGiornaliero.getClass().getSimpleName(), dettaglioProgramma);
			}
			
			arrayProgrammi.add(objectProgrammi);
		}
	}*/
	
	public void nuovoProgrammaGiornaliero(String nomeStanza, String nomeClasseElemento, int ore, int minuti) {
		LocalTime inizio = LocalTime.parse(ore+": "+minuti);
		LocalTime fine = LocalTime.parse((ore+2)+": "+minuti);
		
		Stanza stanza = casa.getStanza(nomeStanza);
		
		if(nomeClasseElemento.equals("SensoreTemperatura")) {
			SensoreTemperatura s = stanza.getSensoreTemperatura();
		}
		else if(nomeClasseElemento.equals("Robot")) {
			RobotPulizia r = casa.getRobot();
		} 
		else if(nomeClasseElemento.equals("Lavastoviglie")) {
			//ElementoProgrammabile r = 
		}
		else {
			
		}
	}
	
	public void creazioneProgrammi() {
		//nuovoProgrammaGiornaliero("Cucina", nomeClasseElemento, ore, minuti);
	}
}
