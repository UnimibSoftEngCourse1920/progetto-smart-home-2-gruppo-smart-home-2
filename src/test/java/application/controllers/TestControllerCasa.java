package application.controllers;

import static org.junit.Assert.*;
import org.junit.Test;
import application.backend.dominio.*;


public class TestControllerCasa {

	@Test
	public void testAddStanza() {
		ControllerCasa c= new ControllerCasa(null);
		Stanza s=new Stanza("prova");
		c.addStanza(s);
		assertEquals(s.getNome(),c.getStanza("prova").getNome());
	}

	@Test
	public void testAddRobot() {
		ControllerCasa c= new ControllerCasa(null);
		Stanza s=new Stanza("prova");
		c.addStanza(s);
		c.addRobot(s);
		assertTrue(c.getRobot()!=null);
	}
}