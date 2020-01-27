package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;



import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import application.controllers.ControllerCasa;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class HomepageView extends JPanel {
	private JLayeredPane panelPrincipale;
	private JLabel labelHomepage;
	private ControllerCasa casa;
	
	/**
	 * Create the panel.
	 */
	public HomepageView(JLayeredPane principale, ControllerCasa casa) {
		panelPrincipale = principale;
		this.casa = casa;
		
		inizializzazione();
	}
	
	public void inizializzazione() {
		labelHomepage = new JLabel("HOMEPAGE");
		labelHomepage.setHorizontalAlignment(SwingConstants.CENTER);
		labelHomepage.setFont(new Font("Arial", Font.PLAIN, 25));
		
		setLayoutHomepage();
		
		gestioneHomepage();
	}
	
	public void setLayoutHomepage() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelHomepage, GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelHomepage, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(397, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	public void gestioneHomepage() {
	}
}
