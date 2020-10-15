package integrationTest.application.controllers;

import org.junit.Before;
import org.junit.Test;

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
		
	}
}
