package application.backend;

public class Tapparella {
	private int id;
	private boolean isAperta;
	
	public Tapparella(int id) {
		this.id = id;
		this.isAperta = false;
	}
	
	public boolean isAperta() {
		return this.isAperta;
	}
	
	public void cambiaStato() {
		if(isAperta())
			this.isAperta = false;
		else this.isAperta = true;
	}
	
	public int getId() {
		return this.id;
	}
}
