package application.backend.sensori;

public class Allarme {
	private static Allarme istance = null;
	private boolean isAttivo;
	
	public static Allarme getInstance() {
		if(istance == null) 
			istance = new Allarme();
		return istance;
	}
	
	//metodo che gestisce il cambiamento si stato del sensore
	public void notifica() {
	}
	
	public boolean isAttivo() {
		return this.isAttivo;
	}
	
	public void cambiaStato() {
		if(isAttivo())
			this.isAttivo = false;
		else this.isAttivo = true;
	}
}
