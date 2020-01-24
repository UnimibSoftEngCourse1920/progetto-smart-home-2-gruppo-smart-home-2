package application.frontend.views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Color;

public class ProgrammiView extends JPanel {
	public JButton btnNewButton;
	/**
	 * Create the panel.
	 */
	public ProgrammiView() {
		
		 btnNewButton = new JButton("ciao");
		add(btnNewButton);

	}
	public JButton getB() {
		return btnNewButton;
	}
}
