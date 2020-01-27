package application.frontend.views;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import application.controllers.ControllerCasa;
import java.awt.Font;

public class RobotPuliziaView extends JPanel {

	private JLayeredPane panelPrincipale;
	private ControllerCasa casa;
	private JLabel titolo;
	private JButton cambiaStato;
	private JLabel posizioneRobot;
	private JButton aggiornaPosizioneRobot;
	
	public RobotPuliziaView(JLayeredPane principale, ControllerCasa casa) {
		this.panelPrincipale = principale;
		this.casa = casa;
		inizializzazione();
	}
	
	public void inizializzazione() {
		titolo = new JLabel("ROBOT PULIZIA");
		cambiaStato = new JButton("");
		aggiornaPosizioneRobot  = new JButton("Aggiorna Posizione Robot");
		posizioneRobot = new JLabel("");
		
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setFont(new Font("Arial", Font.PLAIN, 25));
		
		setLayoutRobot();
		setStato();
	}
	
	private void setStato() {
		if(this.casa.getRobot().isInFunzione())
			cambiaStato.setText("Spegni Robot");
		else
			cambiaStato.setText("Accendi Robot");
	}

	public void setLayoutRobot() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(titolo, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(cambiaStato, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(aggiornaPosizioneRobot, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(posizioneRobot, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addGap(121))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(titolo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cambiaStato, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(aggiornaPosizioneRobot, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(posizioneRobot, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(374, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
