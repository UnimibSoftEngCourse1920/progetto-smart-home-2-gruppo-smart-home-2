package application.backend.programmi;

import java.util.ArrayList;
import application.backend.*;

public abstract class Programma {

	private int id;
	private ArrayList<Object> elementi = new ArrayList<>();
	
	public Programma(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void aggiungiElemento(Object e) {
		if(e!=null)
			elementi.add(e);
	}

	public ArrayList<Object> getElementi() {
		return elementi;
	}
	
}