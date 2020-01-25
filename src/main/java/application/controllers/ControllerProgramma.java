package application.controllers;

import java.time.*;
import java.util.ArrayList;

import application.backend.dominio.*;
import application.backend.programmi.*;

public class ControllerProgramma {

	private ControllerCasa casa;
	private int counter;
	private ArrayList<Programma> programmi;

	public ControllerProgramma(int counter) {
		this.counter = counter;
		this.programmi = new ArrayList<>();
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
		for (Programma p : programmi) {
			if((p instanceof ProgrammaGiornaliero && ((ProgrammaGiornaliero) p).getInizio()== ora) ||
				p instanceof ProgrammaSettimanale && ((ProgrammaSettimanale) p).getInizio(giorno)== ora)
				cambiaStatoElemento(p.getElemento(),false);
		}
	}
	
	public void spegnimento(LocalTime ora, DayOfWeek giorno) {
		for (Programma p : programmi) {
			if(p instanceof ProgrammaSettimanale && ((ProgrammaSettimanale) p).getFine(giorno)== ora)
				cambiaStatoElemento(p.getElemento(),true);
			else if(p instanceof ProgrammaGiornaliero && ((ProgrammaGiornaliero) p).getFine()== ora) {
				cambiaStatoElemento(p.getElemento(),true);
				programmi.remove(p);
			}
		}
	}
	
	public void cambiaStatoElemento(Object e, boolean stato) {
			if(e instanceof RobotPulizia && ((RobotPulizia) e).isInFunzione()==stato)
				((RobotPulizia) e).cambiaStato();
			else {
				String stanza=casa.cercaElemento();
				if(e instanceof Lavatrice && ((Lavatrice) e).isInFunzione()== stato)
					casa.cambiaStatoLavatrice(stanza, ((Lavatrice) e).getId());
				if(e instanceof Lavastoviglie && ((Lavastoviglie) e).isInFunzione()== stato)
					casa.cambiaStatoLavastoviglie(stanza, ((Lavastoviglie) e).getId());
			}
	}
	
}
