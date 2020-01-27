package application.backend.programmi;

import java.time.LocalTime;

public class ProgrammaGiornaliero extends Programma {
	private LocalTime inizio;
	private LocalTime fine;
	private double valoreDiSetting;
	
	public ProgrammaGiornaliero(int id, LocalTime inizio, LocalTime fine, double valoreDiSetting, Object e) {
		super(id);
		this.inizio = inizio;
		this.fine = fine;
		this.valoreDiSetting = valoreDiSetting;
		super.aggiungiElemento(e);
	}
	
	public ProgrammaGiornaliero(int id, LocalTime inizio, LocalTime fine, double valoreDiSetting) {
		super(id);
		this.inizio = inizio;
		this.fine = fine;
		this.valoreDiSetting = valoreDiSetting;
	}

	public LocalTime getInizio() {
		return inizio;
	}

	public LocalTime getFine() {
		return fine;
	}

	public double getValoreDiSetting() {
		return valoreDiSetting;
	}
	
	public void setElemento(Object e) {
		super.aggiungiElemento(e);
	}

}
