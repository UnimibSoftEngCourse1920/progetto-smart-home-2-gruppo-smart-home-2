package application.backend.sensori;

public abstract class Sensore {
	private Allarme allarme;
	
	public Allarme getAllarme() {
		return this.allarme;
	}
}