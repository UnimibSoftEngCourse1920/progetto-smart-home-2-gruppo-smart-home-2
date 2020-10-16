package integrationTest.application.controllers;

import org.junit.Before;
import org.junit.Test;

import application.backend.dominio.Stanza;
import application.controllers.ControllerCasa;

import static org.junit.Assert.*;

import java.sql.*;

public class CasaIT {
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
		insertStanze();
		
		String select = "SELECT * FROM stanze";
		int count = 0;
        
        try {
        	Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            
            while(rs.next()) {
            	count++;
            }
            
            assertTrue(count == 3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void insertStanze() {
		ControllerCasa controller = new ControllerCasa(null);
		Stanza cucina = new Stanza("Cucina");
		Stanza sala = new Stanza("Sala");
		Stanza cameraDaLetto = new Stanza("Camera da Letto");
		
		controller.addStanza(cucina);
		controller.addStanza(sala);
		controller.addStanza(cameraDaLetto);
		
		for(int i = 0; i < 3; i++) {
			String insert = "INSERT INTO stanze(nome) VALUES(?)";
			
			try {
	        	PreparedStatement pstmt = conn.prepareStatement(insert);
	        	pstmt.setString(1, controller.getStanze().get(i).getNome());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}
	}
}
