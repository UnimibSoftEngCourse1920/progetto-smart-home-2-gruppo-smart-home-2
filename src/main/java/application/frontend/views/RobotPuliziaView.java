package application.frontend.views;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RobotPuliziaView extends JPanel {

	private JLayeredPane panelPrincipale;
	private JLabel titolo;
	private JButton cambiaStato;
	
	public RobotPuliziaView(JLayeredPane principale) {
		this.panelPrincipale = principale;
		cambiaStato = new JButton("Programmi");
		inizializzazione();
	}
	
	public void inizializzazione() {
		titolo = new JLabel("SMART HOME");
		
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setFont(new Font("Arial", Font.PLAIN, 25));
		
		setLayoutRobot();
	}
	
	public void setLayoutRobot() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(titolo, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(cambiaStato, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(505, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(titolo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cambiaStato, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(374, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
