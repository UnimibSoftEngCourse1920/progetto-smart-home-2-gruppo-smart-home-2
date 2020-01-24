package application.controllers;

import java.time.*;
import java.util.*;

public class Simulazione extends TimerTask{
	private LocalTime ora;
	private DayOfWeek giorno;
	private ControllerProgramma cp;

	public Simulazione(ControllerProgramma cp) {
		this.ora = LocalTime.of(0, 0);
		this.giorno = DayOfWeek.MONDAY;
		this.cp=cp;
	}
	
	@Override
	public void run() {
		if(this.ora.compareTo(LocalTime.of(23, 50)) == 0) {
			this.ora = LocalTime.of(0, 0);
			this.giorno=this.giorno.plus(1);
		}
		else
			this.ora.plusMinutes(10);
		cp.accensione(this.ora, this.giorno);
		cp.spegnimento(this.ora, this.giorno);
	}

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public DayOfWeek getGiorno() {
		return giorno;
	}

	public void setGiorno(DayOfWeek giorno) {
		this.giorno = giorno;
	}
	

}
