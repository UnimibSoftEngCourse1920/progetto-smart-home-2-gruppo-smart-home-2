package application.backend;

public class Lavatrice {

	private int id;
	private boolean isInFunzione;
	
	public Lavatrice(int id) {
		this.id = id;
		this.isInFunzione = false;
	}
	
	public boolean isInFunzione() {
		return this.isInFunzione;
	}
	
	public void cambiaStato() {
		if(isInFunzione())
			this.isInFunzione = false;
		else 
			this.isInFunzione = true;
	}
	
	public int getId() {
		return this.id;
	}
}
