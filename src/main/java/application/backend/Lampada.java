package application.backend;

public class Lampada {
	private int id;
	private boolean isAccesa;
	
	public Lampada(int id) {
		this.id = id;
		this.isAccesa = false;
	}
	
	public boolean isAccesa() {
		return this.isAccesa;
	}
	
	public void cambiaStato() {
		if(isAccesa())
			this.isAccesa = false;
		else this.isAccesa = true;
	}
	
	public int getId() {
		return this.id;
	}
}
