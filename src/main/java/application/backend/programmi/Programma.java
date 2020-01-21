package application.backend.programmi;

import java.util.ArrayList;
import application.backend.*;

public abstract class Programma {

	private int id;
	private ArrayList<ElementoProgrammabile> elementi = new ArrayList<ElementoProgrammabile>();
	
	public Programma(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void aggiungiElemento(ElementoProgrammabile e) {
		if(e!=null)
			elementi.add(e);
	}

	/*public ArrayList<ElementoProgrammabile> getElementi() {
		return elementi;
	}*/
	
}