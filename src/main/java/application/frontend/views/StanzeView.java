package application.frontend.views;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StanzeView extends JPanel {

	public JButton btnNewButton;
	/**
	 * Create the panel.
	 */
	public StanzeView() {
		
		 btnNewButton = new JButton("kKFDK");
		add(btnNewButton);

	}
	public JButton getB() {
		return btnNewButton;
	}
}
