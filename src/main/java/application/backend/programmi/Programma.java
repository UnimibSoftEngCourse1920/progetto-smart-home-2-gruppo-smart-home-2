package application.backend.programmi;

import application.backend.dominio.*;

public abstract class Programma {

	private int id;
	private Object elemento = new Object();
	
	public Programma(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void aggiungiElemento(Object e) {
		if(e!=null)
			elemento = e;
	}

	public Object getElemento() {
		return elemento;
	}
	
}