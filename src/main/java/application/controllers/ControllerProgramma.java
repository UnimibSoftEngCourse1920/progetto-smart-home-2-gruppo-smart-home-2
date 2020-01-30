package application.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import application.backend.dominio.*;
import application.backend.programmi.*;
import application.backend.sensori.SensoreTemperatura;
import application.frontend.support.Alert;
import application.frontend.views.ProgrammaSettimanaleView;

public class ControllerProgramma {

	private ControllerCasa casa;
	private int counter;
	private ArrayList<Programma> programmi;
	private static final String SENSORETEMPERATURA = "SensoreTemperatura";
	private static final String LAVASTOVIGLIE = "Lavastoviglie";

	public ControllerProgramma(int counter) {
		this.counter = counter;
		this.programmi = new ArrayList<>();
	}
	
	public ControllerProgramma() {
		this(1);
	}
	
	public void creaProgrammaGiornaliero(LocalTime inizio, LocalTime fine, double valoreDiSetting, Object e) {
		this.programmi.add(new ProgrammaGiornaliero(counter,inizio,fine,valoreDiSetting,e));
		this.counter++;
	}
	
	public int creaProgrammaSettimanale() {
		this.programmi.add(new ProgrammaSettimanale(counter));
		this.counter++;
		return (this.counter-1);
	}
	
	public void eliminaProgramma(int id) {
		for(int i = 0; i < programmi.size(); i++) {
			if(programmi.get(i).getId() == id)
				programmi.remove(i);
		}
		
	}
	
	public void aggiungiElemento(int id, Object e) {
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
				if(inizio.compareTo(ora)==0) {
					if(p.getElemento() instanceof SensoreTemperatura && !((SensoreTemperatura)p.getElemento()).getStato().equals("Spento")) {
						cambiaTemperatura(((ProgrammaGiornaliero) p).getValoreDiSetting(), (SensoreTemperatura) p.getElemento());
					}
					else {
						cambiaStatoElemento(p.getElemento(),false);
						
					}
				}
			}
			if (p instanceof ProgrammaSettimanale && ((ProgrammaSettimanale) p).getInizio(giorno) != null){
				inizio= ((ProgrammaSettimanale) p).getInizio(giorno);
				if(inizio.compareTo(ora)==0) {
					if(p.getElemento() instanceof SensoreTemperatura && !((SensoreTemperatura)p.getElemento()).getStato().equals("Spento"))
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
		Programma p = null;
		for(int i = 0; i < programmi.size() && programmi.get(i) != null; i++) {
			p = programmi.get(i);

			if(p instanceof ProgrammaSettimanale) {
				fine = ((ProgrammaSettimanale) p).getFine(giorno);
				if(fine != null && (fine.compareTo(ora)==0)) {
					if(p.getElemento() instanceof SensoreTemperatura)
						cambiaTemperatura(SensoreTemperatura.TEMPERATURADEFAULT, (SensoreTemperatura) p.getElemento());
					else
						cambiaStatoElemento(p.getElemento(),true);
				}
			}
			else if(p instanceof ProgrammaGiornaliero) {
				fine = ((ProgrammaGiornaliero) p).getFine();
				if(fine != null && (fine.compareTo(ora)==0)) {
					if(p.getElemento() instanceof SensoreTemperatura) {
						cambiaTemperatura(SensoreTemperatura.TEMPERATURADEFAULT, (SensoreTemperatura) p.getElemento());
					}
					else
						cambiaStatoElemento(p.getElemento(),true);
					eliminaProgramma(p.getId());
					getCasa().getMain().getPanelProgrammi().viewTabellaProgrammiGiornalieri();
				}
			}
		}
		
	}
	
	public void cambiaStatoElemento(Object e, boolean stato) {
		if(e instanceof RobotPulizia && ((RobotPulizia) e).isInFunzione()==stato && !stato)
		{
			casa.accendiRobot();
			getCasa().getMain().getRobotView().setStato();
		}
			
		if(e instanceof RobotPulizia && ((RobotPulizia) e).isInFunzione()==stato && stato) {
			casa.spegniRobot();
			getCasa().getMain().getRobotView().setStato();
		}
		if(e instanceof Lavatrice && ((Lavatrice) e).isInFunzione()== stato) {
			((Lavatrice) e).cambiaStato();
		}
		if(e instanceof Lavastoviglie && ((Lavastoviglie) e).isInFunzione()== stato) {
			((Lavastoviglie) e).cambiaStato();
		}
	}
	
	public List<Programma> getAllProgrammi() {
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
	
	public Object getElementoProgramma(Programma p) {
		return p.getElemento();
	}
	
	public int getIdProgramma(Programma p) {
		return p.getId();
	}
	
	public LocalTime getInizioProgrammaGiornaliero(ProgrammaGiornaliero p) {
		return p.getInizio();
	}
	
	public LocalTime getFineProgrammaGiornaliero(ProgrammaGiornaliero p) {
		return p.getFine();
	}
	
	public double getSettingProgrammaGiornaliero(ProgrammaGiornaliero p) {
		return p.getValoreDiSetting();
	}
	
	public void nuovoProgrammaGiornaliero(String nomeStanza, String nomeClasseElemento, int ore, int minuti, double tempDefault) {
		LocalTime inizio = LocalTime.of(ore, minuti);
		LocalTime fine = LocalTime.of((ore+2), minuti);
		
		
		Stanza stanza = casa.getStanza(nomeStanza);
		
		if(nomeClasseElemento.equals(SENSORETEMPERATURA)) {
			SensoreTemperatura s = stanza.getSensoreTemperatura();
			creaProgrammaGiornaliero(inizio, fine, tempDefault, s);
		}
		else if(nomeClasseElemento.equals("RobotPulizia")) {
			RobotPulizia r = casa.getRobot();
			creaProgrammaGiornaliero(inizio, fine, 0, r);
		} 
		else if(nomeClasseElemento.equals(LAVASTOVIGLIE)) {
			Lavastoviglie l = stanza.getLavastoviglie();
			creaProgrammaGiornaliero(inizio, fine, 0, l);
		}
		else {
			Lavatrice l = stanza.getLavatrice();
			creaProgrammaGiornaliero(inizio, fine, 0, l);
		}
	}
	
	public void eliminaProgrammaGiornaliero(Programma p) {
		eliminaProgramma(p.getId());
	}
	
	public void creazioneProgrammi() {
		nuovoProgrammaGiornaliero("Cucina", LAVASTOVIGLIE, 1, 45, 0);
		nuovoProgrammaGiornaliero("Cucina", SENSORETEMPERATURA, 2, 10, 10);
	}
	
	public void aggiornaProgrammaSettimanale(int idSettimanale, int giornoSettimana, String nomeStanza, String nomeClasseElemento, int ore, int minuti, double tempDefault) {
		LocalTime inizio = LocalTime.of(ore, minuti);
		LocalTime fine = LocalTime.of((ore+2), minuti);
		
		
		Stanza stanza = casa.getStanza(nomeStanza);
		
		
		if(nomeClasseElemento.equals(SENSORETEMPERATURA)) {
			SensoreTemperatura s = stanza.getSensoreTemperatura();
			aggiungiElemento(idSettimanale, s);
			aggiungiGiornoASettimana(idSettimanale, DayOfWeek.of(giornoSettimana%8), inizio, fine, tempDefault, s);
		}
		else if(nomeClasseElemento.equals("RobotPulizia")) {
			RobotPulizia r = casa.getRobot();
			aggiungiElemento(idSettimanale, r);
			aggiungiGiornoASettimana(idSettimanale, DayOfWeek.of(giornoSettimana%8), inizio, fine, 0, r);
		} 
		else if(nomeClasseElemento.equals(LAVASTOVIGLIE)) {
			Lavastoviglie l = stanza.getLavastoviglie();
			aggiungiElemento(idSettimanale, l);
			aggiungiGiornoASettimana(idSettimanale, DayOfWeek.of(giornoSettimana%8), inizio, fine, 0, l);
		}
		else {
			Lavatrice l = stanza.getLavatrice();
			aggiungiElemento(idSettimanale, l);
			aggiungiGiornoASettimana(idSettimanale, DayOfWeek.of(giornoSettimana%8), inizio, fine, 0, l);
		}
	}
	
	public void getProgrammiGiornalieri(ProgrammaSettimanale p, ProgrammaSettimanaleView view) {
		EnumMap<DayOfWeek, ProgrammaGiornaliero> settimana = (EnumMap<DayOfWeek, ProgrammaGiornaliero>)p.getSettimana();
		int i = 0;
		Date data = null;
		
		for (Entry<DayOfWeek, ProgrammaGiornaliero> entry : settimana.entrySet()) {
			DayOfWeek giorno = entry.getKey();
			SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
			i = (giorno.getValue()-1)%7;
			
			try {
				data = formato.parse(p.getInizio(giorno).toString());
			} catch (ParseException e) {
				(new Alert()).errore("Errore nel formato data", "Errore");
			}
			
			view.modificaSpinner(i, data);
			
			if(p.getElemento() instanceof SensoreTemperatura)
				view.modificaText(i, String.valueOf(p.getValoreDiSetting(giorno)));
		}
	}
}
