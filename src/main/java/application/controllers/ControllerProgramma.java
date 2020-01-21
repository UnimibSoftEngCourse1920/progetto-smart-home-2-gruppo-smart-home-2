package application.controllers;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import application.backend.programmi.*;
import application.backend.*;

public class ControllerProgramma {

	private int counter;
	private ArrayList<Programma> programmi;

	public ControllerProgramma(int counter) {
		this.counter = counter;
		this.programmi = new ArrayList<Programma>();
	}
	
	public ControllerProgramma() {
		this(0);
	}
	
	public void creaProgrammaGiornaliero(ElementoProgrammabile e, LocalTime inizio, LocalTime fine, double valoreDiSetting) {
		this.programmi.add(new ProgrammaGiornaliero(counter,inizio,fine,valoreDiSetting));
		this.counter++;
	}
	
	public void creaProgrammaSettimanale() {
		this.programmi.add(new ProgrammaSettimanale(counter));
		this.counter++;
	}
	
	public void aggiungiElemento(int id,ElementoProgrammabile e) {
		for (Programma p : programmi) {
			if (p.getId() == id) {
				p.aggiungiElemento(e);
			}
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
		
	}
	
	public void spegnimento(LocalTime ora, DayOfWeek giorno) {
		
	}
	
	
}
