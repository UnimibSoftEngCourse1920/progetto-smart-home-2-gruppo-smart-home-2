package application.frontend.support;


import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Alert extends JPanel {
	
	public void errore(String infoMessaggio, String titolo){
        JOptionPane.showMessageDialog(null, infoMessaggio, titolo, JOptionPane.ERROR_MESSAGE);
    }
	
	public void info(String infoMessaggio, String titolo){
        JOptionPane.showMessageDialog(null, infoMessaggio, titolo, JOptionPane.INFORMATION_MESSAGE);
    }
}
