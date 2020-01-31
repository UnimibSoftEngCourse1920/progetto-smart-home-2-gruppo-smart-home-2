package application.frontend.views;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import application.controllers.ControllerCasa;
import application.frontend.support.Alert;

import java.awt.Color;
import java.awt.Font;

public class RobotPuliziaView extends JPanel {
	private transient ControllerCasa casa;
	private JLabel titolo;
	private JButton cambiaStato;
	private JLabel posizioneRobot;
	private JButton aggiornaPosizioneRobot;
	
	public RobotPuliziaView(ControllerCasa casa) {
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
		gestioneRobot();
		gestionePosizione();
	}

	public void setStato() {
		if(this.casa.getRobot() != null) {
			if(this.casa.getRobot().isInFunzione()) {
				cambiaStato.setText("Acceso");
				cambiaStato.setBackground(Color.GREEN);
			}
			else {
				cambiaStato.setText("Spento");
				cambiaStato.setBackground(Color.RED);
			}
		}
		else
			(new Alert()).errore("La casa non ha un robot della pulizia", "Attenzione");
	}

	public void gestioneRobot() {
		cambiaStato.addActionListener(e -> {
				if(casa.getRobot() != null) {
					if(casa.getRobot().isInFunzione())
						casa.spegniRobot();
					else
						casa.accendiRobot();
					setStato();
				}	
				else
					(new Alert()).errore("La casa non ha un robot della pulizia", "Attenzione");
		});
	}
	
	private void gestionePosizione() {
		aggiornaPosizioneRobot.addActionListener(e ->{
				if(casa.getRobot() != null) {
					posizioneRobot.setText("Il robot ora si trova in "+casa.getRobot().getPosizione().getNome());
				}	
				else
					(new Alert()).errore("La casa non ha un robot della pulizia", "Attenzione");
		});
	}
	
	public void setLayoutRobot() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(cambiaStato, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(aggiornaPosizioneRobot, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(posizioneRobot, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(titolo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(titolo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(cambiaStato, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(aggiornaPosizioneRobot, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(posizioneRobot, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(344, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
