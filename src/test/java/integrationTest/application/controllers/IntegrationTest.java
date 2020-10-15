package integrationTest.application.controllers;

import org.junit.Before;
import org.junit.Test;

import application.backend.dominio.Stanza;
import application.controllers.ControllerCasa;

import static org.junit.Assert.*;

import java.sql.*;

public class IntegrationTest {
	Connection conn = null;
	
	@Before
	public void connectionDB() {
		conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:smarthome.db");
			assertTrue(true);
		}		
		catch(Exception e) {
			assertFalse(false);
		}
	}
	
	@Test
	public void checkStanze() {
		ControllerCasa controller = new ControllerCasa(null);
		Stanza cucina = new Stanza("Cucina");
		Stanza sala = new Stanza("Sala");
		Stanza cameraDaLetto = new Stanza("Camera da Letto");
		
		controller.addStanza(cucina);
		controller.addStanza(sala);
		controller.addStanza(cameraDaLetto);
	}
}
