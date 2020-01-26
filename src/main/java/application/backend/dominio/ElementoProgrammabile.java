package application.backend.dominio;

public class ElementoProgrammabile {

	private int id;
	private boolean isInFunzione;
	private String tipo;
	
	public ElementoProgrammabile(int id, String tipo) {
		this.id = id;
		this.isInFunzione = false;
		this.tipo = tipo;
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

	public String getTipo() {
		return tipo;
	}
	
}
