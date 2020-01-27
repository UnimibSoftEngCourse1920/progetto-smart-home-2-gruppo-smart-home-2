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
	private FileReader jsonProgrammi;

	public ControllerProgramma(int counter) {
		this.counter = counter;
		this.programmi = new ArrayList<>();
		
		try {
			this.jsonProgrammi =  new FileReader(getClass().getClassLoader().getResource("programmi.json").getFile());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ControllerProgramma() {
		this(0);
	}
	
	public void creaProgrammaGiornaliero(LocalTime inizio, LocalTime fine, double valoreDiSetting) {
		this.programmi.add(new ProgrammaGiornaliero(counter,inizio,fine,valoreDiSetting));
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
	
	public void aggiungiGiornoASettimana(int id, DayOfWeek giorno, LocalTime inizio, LocalTime fine, double valoreDiSetting) {
		ProgrammaSettimanale ps;
		for (Programma p : programmi) {
			if (p.getId() == id && p instanceof ProgrammaSettimanale) {
				ps = (ProgrammaSettimanale)p;
				ps.aggiungiGiorno(giorno, inizio, fine, valoreDiSetting);
			}
		}
	}
	
	public void accensione(LocalTime ora, DayOfWeek giorno) {
		LocalTime inizio;
		for (Programma p : programmi) {
			if(p instanceof ProgrammaGiornaliero) {
				inizio = ((ProgrammaGiornaliero) p).getInizio();
				if(inizio.compareTo(ora)==0 || inizio.compareTo(ora)== -1) {
					if(p.getElemento() instanceof SensoreTemperatura)
						cambiaTemperatura(((ProgrammaGiornaliero) p).getValoreDiSetting(), (SensoreTemperatura) p.getElemento());
					else
						cambiaStatoElemento(p.getElemento(),false);
					}
				}
			if (p instanceof ProgrammaSettimanale){
				inizio= ((ProgrammaSettimanale) p).getInizio(giorno);
				if(inizio.compareTo(ora)==0 || inizio.compareTo(ora)== -1) {
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
				if(fine != null && (fine.compareTo(ora)==0 || fine.compareTo(ora)== -1)) {
					if(p.getElemento() instanceof SensoreTemperatura)
						cambiaTemperatura(SensoreTemperatura.TEMPERATURADEFAULT, (SensoreTemperatura) p.getElemento());
					else
						cambiaStatoElemento(p.getElemento(),true);
				}
			}
			else if(p instanceof ProgrammaGiornaliero) {
				fine = ((ProgrammaGiornaliero) p).getFine();
				if(fine != null && (fine.compareTo(ora)==0 || fine.compareTo(ora)== -1)) {
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
			else {if(e instanceof RobotPulizia && ((RobotPulizia) e).isInFunzione()==stato && stato == true)
				casa.spegniRobot();
				else {
					if(e instanceof ElementoProgrammabile && ((ElementoProgrammabile) e).isInFunzione()== stato)
						((ElementoProgrammabile) e).cambiaStato();
				}
			}
	}
	
	public ArrayList<Programma> getAllProgrammi() {
		return this.programmi;
	}
	
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
	}
}
