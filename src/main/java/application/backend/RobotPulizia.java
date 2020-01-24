
package application.backend;

public class RobotPulizia {

	private int id;
	private boolean isInFunzione;
	private Stanza base;
	private Stanza posizione;
	
	public RobotPulizia(int id, Stanza base) {
		this.id = id;
		this.isInFunzione = false;
		this.base = base;
		this.posizione = base;
	}
	
	public Stanza getBase() {
		return base;
	}
	
	public Stanza getPosizione() {
		return posizione;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isInFunzione() {
		return isInFunzione;
	}
	
	public void cambiaStato() {
		if(isInFunzione())
			this.isInFunzione = false;
		else 
			this.isInFunzione = true;
	}
	
	public void muovi(Stanza s) {
		this.posizione = s;
	}
	
	public void fine() {
		this.posizione = base;
	}
}
