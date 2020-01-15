package application.controllers;

import application.backend.*;

public class Controller {
	public void metodoController() {
		System.out.println("Controller");
		ClasseBackend b = new ClasseBackend();
		b.metodoBackend();;
	}
}
