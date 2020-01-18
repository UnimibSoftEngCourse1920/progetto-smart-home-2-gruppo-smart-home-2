package application.backend;

public class Finestra {
	private int id;
	private boolean isAperta;
	private Tapparella tapparella;
	
	public Finestra(Tapparella tapparella) {
		this.id = tapparella.getId();
		this.isAperta = false;
		this.tapparella = tapparella;
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
	
	public Tapparella getTapparella() {
		return this.tapparella;
	}
}
