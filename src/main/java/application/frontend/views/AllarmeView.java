package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import application.backend.sensori.Allarme;
import application.controllers.ControllerCasa;
import application.frontend.support.Alert;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllarmeView extends JPanel {
	private transient ControllerCasa casa;
	
	private JLabel labelAllarme;
	private JLabel labelSituazioneAttuale;
	private JLabel labelEffrazione;
	private JLabel labelGas;
	private JLabel labelMovimenti;
	private JButton bottoneAccendiSpegni;
	private JButton bottoneTerminaEmergenza;

	public AllarmeView(ControllerCasa casa) {
		this.casa = casa;
		
		inizializzazione();
	}

	public void inizializzazione() {
		Border b = BorderFactory.createLineBorder(Color.BLACK);
		labelAllarme = new JLabel("ALLARME");
		labelAllarme.setHorizontalAlignment(SwingConstants.CENTER);
		labelAllarme.setFont(new Font("Arial", Font.PLAIN, 25));
		labelSituazioneAttuale = new JLabel("Situazione attuale in casa:");
		labelSituazioneAttuale.setHorizontalAlignment(SwingConstants.CENTER);
		labelSituazioneAttuale.setFont(new Font("Arial", Font.PLAIN, 20));
		
		labelEffrazione = new JLabel("Effrazione");
		labelEffrazione.setHorizontalAlignment(SwingConstants.CENTER);
		labelEffrazione.setBorder(b);
		labelGas = new JLabel("Fuga di Gas");
		labelGas.setHorizontalAlignment(SwingConstants.CENTER);
		labelGas.setBorder(b);
		labelMovimenti = new JLabel("Movimenti");
		labelMovimenti.setHorizontalAlignment(SwingConstants.CENTER);
		labelMovimenti.setBorder(b);
		
		bottoneAccendiSpegni = new JButton("");
		bottoneTerminaEmergenza = new JButton("Termina Emergenza");
		
		setLayoutAllarme();
		
		setBottoneAccendiSpegni();
		
		setLabelEffrazione();
		setLabelGas();
		setLabelMovimenti();
		
		gestioneAllarme();
		terminaEmergenza();
	}
	
	public void setLayoutAllarme() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelAllarme, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(360)
					.addComponent(bottoneAccendiSpegni, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(349))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(102)
					.addComponent(labelEffrazione, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(163)
					.addComponent(labelGas, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(146)
					.addComponent(labelMovimenti, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(108))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(labelSituazioneAttuale, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(362)
					.addComponent(bottoneTerminaEmergenza, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(347))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelAllarme, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addComponent(bottoneAccendiSpegni, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(labelSituazioneAttuale, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(labelEffrazione, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelGas, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelMovimenti, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addGap(72)
					.addComponent(bottoneTerminaEmergenza, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		setLayout(groupLayout);
	}
	
	public void setBottoneAccendiSpegni() {
		if(casa.getAllarme() != null) {
			if(casa.getAllarme().isAttivo()) {
				bottoneAccendiSpegni.setText("Spegni");
				bottoneAccendiSpegni.setBackground(Color.GREEN);
			}
			else {
				bottoneAccendiSpegni.setText("Accendi");
				bottoneAccendiSpegni.setBackground(Color.RED);
			}
		}
		else {
			(new Alert()).errore("La casa non è dotata di allarme", "Errore");
			this.setEnabled(false);
		}
	}
	
	public void gestioneAllarme() {
		bottoneAccendiSpegni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Allarme a = casa.getAllarme();
				if(a != null) {
					if(a.isEmergenza() && a.isAttivo())
						(new Alert()).errore("Non puoi spegnere l'allarme durante un'emergenza", "Errore");
					else {
						casa.cambiaStatoAllarme();
						setBottoneAccendiSpegni();
					}
				}
			}
		});
	}
	
	public void terminaEmergenza() {
		bottoneTerminaEmergenza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Allarme a = casa.getAllarme();
				if(a.isAttivo() && a.isEmergenza()) {
					a.terminaEmergenza();
				}
				else
					(new Alert()).errore("Non puoi terminare l'emergenza se non c'è emergenza o se l'allarme è spento", "Errore");
			}
		});
	}
	
	public void setLabelEffrazione() {
		if(casa.getAllarme().isEmergenza()) {
			(new Alert()).info("C'è un emergenza, apri la finestra dell'allarme per verificare di cosa si tratta", "Emergenza");
			labelEffrazione.setOpaque(true);
			labelEffrazione.setBackground(Color.RED);
		}
		else {
			labelEffrazione.setOpaque(true);
			labelEffrazione.setBackground(Color.GREEN);
		}
	}
	
	public void setLabelGas() {
		if(casa.getAllarme().isEmergenza()) {
			(new Alert()).info("C'è un emergenza, apri la finestra dell'allarme per verificare di cosa si tratta", "Emergenza");
			labelGas.setOpaque(true);
			labelGas.setBackground(Color.RED);
		}
		else {
			labelGas.setOpaque(true);
			labelGas.setBackground(Color.GREEN);
		}
	}
	
	public void setLabelMovimenti() {
		if(casa.getAllarme().isEmergenza()) {
			(new Alert()).info("C'è un emergenza, apri la finestra dell'allarme per verificare di cosa si tratta", "Emergenza");
			labelMovimenti.setOpaque(true);
			labelMovimenti.setBackground(Color.RED);
		}
		else {
			labelMovimenti.setOpaque(true);
			labelMovimenti.setBackground(Color.GREEN);
		}
	}
}
