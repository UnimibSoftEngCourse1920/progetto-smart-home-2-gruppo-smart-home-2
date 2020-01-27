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
import javax.swing.JEditorPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AllarmeView extends JPanel {
	private JLayeredPane panelPrincipale;
	private ControllerCasa casa;
	
	private JLabel labelAllarme;
	private JLabel labelSituazioneAttuale;
	private JLabel labelEffrazione;
	private JLabel labelGas;
	private JLabel labelMovimenti;
	private JButton bottoneAccendiSpegni;

	public AllarmeView(JLayeredPane principale, ControllerCasa casa) {
		this.panelPrincipale = principale;
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
		
		setLayoutAllarme();
		
		setBottoneAccendiSpegni();
		
		setLabelEffrazione();
		setLabelGas();
		setLabelMovimenti();
		
		gestioneAllarme();
	}
	
	public void setLayoutAllarme() {
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelAllarme, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(360)
					.addComponent(bottoneAccendiSpegni, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(349))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(102)
					.addComponent(labelEffrazione, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addGap(163)
					.addComponent(labelGas, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addGap(146)
					.addComponent(labelMovimenti, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addGap(108))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(labelSituazioneAttuale, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
					.addContainerGap())
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
					.addGap(87))
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
				bottoneAccendiSpegni.setBackground(null);
				//bottoneAccendiSpegni.setBackground(Color.RED);
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
					if(a.isAttivo()) {
						a.spegni();
					}
					else {
						a.accendi();
					}
					setBottoneAccendiSpegni();
				}
			}
		});
	}
	
	public void setLabelEffrazione() {
		labelEffrazione.setOpaque(true);
		labelEffrazione.setBackground(Color.GREEN);
		
		//pezzo codice che imposta rosso se c'è effrazione
	}
	
	public void setLabelGas() {
		labelGas.setOpaque(true);
		labelGas.setBackground(Color.GREEN);
		
		//pezzo codice che imposta rosso se c'è fuga di gas
	}
	
	public void setLabelMovimenti() {
		labelMovimenti.setOpaque(true);
		labelMovimenti.setBackground(Color.GREEN);
		
		//pezzo codice che imposta rosso se c'è movimento
	}
}
