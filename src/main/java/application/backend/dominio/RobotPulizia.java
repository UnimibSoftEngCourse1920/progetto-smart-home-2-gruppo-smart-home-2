
package application.backend.dominio;

public class RobotPulizia {

	private boolean isInFunzione;
	private Stanza base;
	private Stanza posizione;
	
	public RobotPulizia(Stanza base) {
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
	
	public boolean isInFunzione() {
		return isInFunzione;
	}
	
	public void cambiaStato() {
		if(isInFunzione()) {
			this.isInFunzione = false;
			this.posizione = this.base;
		}
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
