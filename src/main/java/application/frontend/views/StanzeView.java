package application.frontend.views;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import application.backend.dominio.Stanza;
import application.controllers.*;
import javafx.scene.control.ComboBox;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class StanzeView extends JPanel {
	private ControllerCasa controllerCasa;
	private JLayeredPane panelPrincipale;
	private JPanel panelTabellaStanze;
	private JPanel panelSelezioneStanze;
	private JLabel labelStanze;
	private JComboBox comboBoxSelezioneStanze;
	private JLabel labelSelezioneStanze;
	private JTable tabellaStanze;
	
	public StanzeView(JLayeredPane principale) {
		panelPrincipale = principale;
		controllerCasa = new ControllerCasa(panelPrincipale);
		
		inizializzazione();
	}
	
	public void inizializzazione() {
		labelStanze = new JLabel("STANZE");
		labelStanze.setHorizontalAlignment(SwingConstants.CENTER);
		labelStanze.setFont(new Font("Arial", Font.PLAIN, 25));
		panelTabellaStanze = new JPanel();
		panelSelezioneStanze = new JPanel();
		labelSelezioneStanze = new JLabel("Seleziona la stanza:");
		comboBoxSelezioneStanze = new JComboBox();
		tabellaStanze = new JTable();
		tabellaStanze.setVisible(false);
		
		comboBoxStanze();
		
		setLayoutStanze();
		
		gestioneStanze();
	}
	
	public void setLayoutStanze() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelStanze, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
				.addComponent(panelSelezioneStanze, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
				.addComponent(panelTabellaStanze, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelStanze, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSelezioneStanze, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabellaStanze, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		GroupLayout gl_panelTabellaStanze = new GroupLayout(panelTabellaStanze);
		gl_panelTabellaStanze.setHorizontalGroup(
			gl_panelTabellaStanze.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTabellaStanze.createSequentialGroup()
					.addGap(44)
					.addComponent(tabellaStanze, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
					.addGap(47))
		);
		gl_panelTabellaStanze.setVerticalGroup(
			gl_panelTabellaStanze.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTabellaStanze.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabellaStanze, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTabellaStanze.setLayout(gl_panelTabellaStanze);
		
		GroupLayout gl_panelSelezioneStanze = new GroupLayout(panelSelezioneStanze);
		gl_panelSelezioneStanze.setHorizontalGroup(
			gl_panelSelezioneStanze.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneStanze.createSequentialGroup()
					.addGap(322)
					.addComponent(labelSelezioneStanze, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSelezioneStanze, 0, 185, Short.MAX_VALUE)
					.addGap(248))
		);
		gl_panelSelezioneStanze.setVerticalGroup(
			gl_panelSelezioneStanze.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelSelezioneStanze.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSelezioneStanze.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panelSelezioneStanze.createSequentialGroup()
							.addGap(4)
							.addComponent(comboBoxSelezioneStanze))
						.addComponent(labelSelezioneStanze, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		panelSelezioneStanze.setLayout(gl_panelSelezioneStanze);
	}
	
	public void gestioneStanze() {
		comboBoxSelezioneStanze.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	String nomeStanza;
            	if (comboBoxSelezioneStanze.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanze.getSelectedItem().toString();
                    Stanza stanza = controllerCasa.getStanza(nomeStanza);
                   
                    if(stanza != null)
                    	viewTabellaStanze(stanza);
                    else {
                    	Error error = new Error(panelPrincipale);
                    	
                    	panelPrincipale.removeAll();
        				panelPrincipale.add(error);
        				panelPrincipale.repaint();
        				panelPrincipale.revalidate();
                    }
            	}
            }
        });
	}
	
	public void viewTabellaStanze(Stanza stanza) {
		//System.out.print(stanza.getNome());
		String[] colonne = {"ID", "Elemento", "Azioni"};
		tabellaStanze.setVisible(true);
	}
	
	public void comboBoxStanze() {
		String[] stanze = controllerCasa.getNomiStanze();
		
		for(String nome : stanze) {
			if(nome != null) 
				comboBoxSelezioneStanze.addItem(nome);
		}
	}
}
