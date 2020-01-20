package application.backend;

public class RobotPulizia extends ElementoProgrammabile {

	private int id;
	private boolean isInFunzione;
	/*private Stanza base;
	private Stanza posizione;
	
	public RobotPulizia(int id, boolean isInFunzione, Stanza base) {
		this.id = id;
		this.isInFunzione = isInFunzione;
		this.base = base;
		this.posizione = base;
	}
	
	public Stanza getBase() {
		return base;
	}
	
	public Stanza getPosizione() {
		return posizione;
	}
	
	*/
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
	
}
