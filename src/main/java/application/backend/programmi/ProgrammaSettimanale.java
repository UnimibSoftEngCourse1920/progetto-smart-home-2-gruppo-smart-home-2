package application.backend.programmi;

import java.time.*;
import java.util.EnumMap;

public class ProgrammaSettimanale extends Programma {

	private EnumMap<DayOfWeek,ProgrammaGiornaliero> settimana = new EnumMap<>(DayOfWeek.class);
	
	public ProgrammaSettimanale(int id) {
		super(id);
	}
	
	public void aggiungiGiorno(DayOfWeek giorno, LocalTime inizio, LocalTime fine, double valoreDiSetting, Object e) {
		settimana.put(giorno, new ProgrammaGiornaliero(super.getId(),inizio,fine,valoreDiSetting,e));
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
