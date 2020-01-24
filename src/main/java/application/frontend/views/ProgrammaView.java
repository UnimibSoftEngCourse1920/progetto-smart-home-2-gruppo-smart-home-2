package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProgrammaView extends JPanel {
	JLayeredPane prin;
	/**
	 * Create the panel.
	 */
	public ProgrammaView(JLayeredPane p) {
		StanzeView panelStanze = new StanzeView();
		JButton button = new JButton("Stanza");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prin.removeAll();
				prin.add(panelStanze);
				prin.repaint();
				prin.revalidate();
			}
		});
		
		JLabel lblProgramma = new JLabel("PROGRAMMA");
		lblProgramma.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramma.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblProgramma);
		add(button);
		prin = p;
	}

}
