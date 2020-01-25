package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class HomepageView extends JPanel {
	private JLayeredPane panelPrincipale;
	private JButton bottoneStanze;
	private JButton bottoneProgrammi;
	private JButton bottoneAllarme;
	private JLabel labelHomepage;
	
	/**
	 * Create the panel.
	 */
	public HomepageView(JLayeredPane principale) {
		panelPrincipale = principale;
		
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
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(labelHomepage, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(582, Short.MAX_VALUE))
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
