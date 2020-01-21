package application.backend.programmi;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;

public class ProgrammaSettimanale extends Programma {

	private HashMap<DayOfWeek,ProgrammaGiornaliero> settimana = new HashMap<DayOfWeek,ProgrammaGiornaliero>();
	
	public ProgrammaSettimanale(int id) {
		super(id);
	}
	
	public void aggiungiGiorno(DayOfWeek giorno, LocalTime inizio, LocalTime fine, double valoreDiSetting) {
		settimana.put(giorno, new ProgrammaGiornaliero(super.getId(),inizio,fine,valoreDiSetting));
	}
	
	public LocalTime getInizio(DayOfWeek giorno) {
		return settimana.get(giorno).getInizio();
	}

	public LocalTime getFine(DayOfWeek giorno) {
		return settimana.get(giorno).getFine();
	}

	public double getValoreDiSetting(DayOfWeek giorno) {
		return settimana.get(giorno).getValoreDiSetting();
	}
	
}
