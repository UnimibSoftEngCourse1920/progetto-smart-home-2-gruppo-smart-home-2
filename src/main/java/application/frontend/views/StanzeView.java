package application.frontend.views;


import javax.swing.JLayeredPane;
import javax.swing.JPanel;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableModel;


import application.backend.dominio.*;
import application.controllers.*;
import application.frontend.support.Alert;
import application.frontend.support.ColoreCellaTabella;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class StanzeView extends JPanel {
	private ControllerCasa controllerCasa;
	private JLayeredPane panelPrincipale;
	private JPanel panelTabellaStanze;
	private JPanel panelSelezioneStanze;
	private JLabel labelStanze;
	private JComboBox comboBoxSelezioneStanze;
	private JLabel labelSelezioneStanze;
	private JTable tabellaStanze;
	private JScrollPane scrollPaneTabellaStanze;
	private DefaultTableModel modelTabellaStanze;
	private Stanza stanzaSelezionata;
	private Object[] rowData;
	
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
		
		
		//TABELLA-----------------------------------------------------------------------------------------
		tabellaStanze = new JTable();
		tabellaStanze.setFont(new Font("Arial", Font.PLAIN, 11));
		
		tabellaStanze.setEnabled(false);
		
		tabellaStanze.setRowHeight(40);
		
		scrollPaneTabellaStanze = new JScrollPane(tabellaStanze);
		scrollPaneTabellaStanze.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		String[] colonne = {"Elemento", "ID", "Cambia Stato"};
		modelTabellaStanze = (DefaultTableModel) tabellaStanze.getModel();
		modelTabellaStanze.setColumnIdentifiers(colonne);
		
		
		
		
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
		
		GroupLayout glpanelTabellaStanze = new GroupLayout(panelTabellaStanze);
		glpanelTabellaStanze.setHorizontalGroup(
				glpanelTabellaStanze.createParallelGroup(Alignment.TRAILING)
				.addGroup(glpanelTabellaStanze.createSequentialGroup()
					.addGap(67)
					.addComponent(scrollPaneTabellaStanze, GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
					.addGap(41))
		);
		glpanelTabellaStanze.setVerticalGroup(
				glpanelTabellaStanze.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelTabellaStanze.createSequentialGroup()
					.addGap(34)
					.addComponent(scrollPaneTabellaStanze, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
					.addGap(69))
		);
		panelTabellaStanze.setLayout(glpanelTabellaStanze);
		
		GroupLayout glpanelSelezioneStanze = new GroupLayout(panelSelezioneStanze);
		glpanelSelezioneStanze.setHorizontalGroup(
				glpanelSelezioneStanze.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelSelezioneStanze.createSequentialGroup()
					.addGap(322)
					.addComponent(labelSelezioneStanze, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSelezioneStanze, 0, 185, Short.MAX_VALUE)
					.addGap(248))
		);
		glpanelSelezioneStanze.setVerticalGroup(
				glpanelSelezioneStanze.createParallelGroup(Alignment.TRAILING)
				.addGroup(glpanelSelezioneStanze.createSequentialGroup()
					.addContainerGap()
					.addGroup(glpanelSelezioneStanze.createParallelGroup(Alignment.BASELINE)
						.addGroup(glpanelSelezioneStanze.createSequentialGroup()
							.addGap(4)
							.addComponent(comboBoxSelezioneStanze))
						.addComponent(labelSelezioneStanze, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		panelSelezioneStanze.setLayout(glpanelSelezioneStanze);
	}
	
	public void gestioneStanze() {
		comboBoxSelezioneStanze.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	rimuoviRigheTabellaStanze();
            	String nomeStanza;
            	if (comboBoxSelezioneStanze.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanze.getSelectedItem().toString();
                    Stanza stanza = controllerCasa.getStanza(nomeStanza);
                   
                    if(stanza != null) {
                    	stanzaSelezionata = stanza;
        				viewTabellaStanze(stanza);
                    }
                    	
                    else {
                    	(new Alert()).errore("Stanza non trovata", "Errore");
                    	
                    }
            	}
            }
        });
		tabellaStanze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			        Point point = e.getPoint();
			        int column = tabellaStanze.columnAtPoint(point);
			        int row = tabellaStanze.rowAtPoint(point);
			         
			        //(new Alert()).errore("ciao", "Column header #" + column + " is clicked, riga "+ row);
			         
			        if(column == 2) {
			        	String tipoOggetto = (String) tabellaStanze.getModel().getValueAt(row, 0);
			        	int id = (int) tabellaStanze.getModel().getValueAt(row, 1);
						
						if(tipoOggetto.equals("Lampada")) {
							Lampada l = stanzaSelezionata.getLampada(id);
							l.cambiaStato();
						}
						else if(tipoOggetto.equals("Finestra")) {
							Finestra f = stanzaSelezionata.getFinestra(id);
							f.cambiaStato();
						}
						else if(tipoOggetto.equals("Tapparella")) {
							Tapparella t = stanzaSelezionata.getFinestra(id).getTapparella();
							t.cambiaStato();
						}
						rimuoviRigheTabellaStanze();
						viewTabellaStanze(stanzaSelezionata);
			        }
			       
					
			   
			}
		});
	}
	
	public void viewTabellaStanze(Stanza stanza) {
		tabellaStanze.setVisible(true);
		List<Object> listaOggettiStanza = controllerCasa.getAllOggettiStanza(stanza);
		
		int numeroOggettiStanza = listaOggettiStanza.size();
		
		Object oggettoStanza;
		
		if(numeroOggettiStanza != 0) {
			rowData = new Object[3];
			
			for(int i = 0; i < numeroOggettiStanza; i++) {
				oggettoStanza = listaOggettiStanza.get(i);
				if(oggettoStanza != null) {
					viewRigaTabellaStanze(oggettoStanza);
					if(oggettoStanza instanceof Finestra) {
						Finestra f = (Finestra) oggettoStanza;
						i++;
						viewRigaTabellaStanze(f.getTapparella());
					}
					
				}
				
			}
		}
		else {
			(new Alert()).info("La stanza non contiene oggetti", "Information");
		}
	}
	
	public void viewRigaTabellaStanze(Object oggettoStanza) {
		boolean stato = false;
		ColoreCellaTabella renderer = new ColoreCellaTabella();
		tabellaStanze.setDefaultRenderer(Object.class, renderer);
		rowData[0] = oggettoStanza.getClass().getSimpleName();
		
		if(oggettoStanza instanceof Lampada) {
			Lampada l = (Lampada) oggettoStanza;
			rowData[1] = l.getId();
			stato= l.isAccesa();
			
			if(stato)
				rowData[2] = "Accesa";
			else
				rowData[2] = "Spenta";
			
		}
		else if(oggettoStanza instanceof Finestra) {
			Finestra f = (Finestra) oggettoStanza;
			rowData[1] = f.getId();
			stato = f.isAperta();
			
			if(stato)
				rowData[2] = "Aperta";
			else
				rowData[2] = "Chiusa";
			
			
			
		}
		
		else if(oggettoStanza instanceof Tapparella) {
			Tapparella t = (Tapparella) oggettoStanza;
			rowData[1] = t.getId();
			stato = t.isAperta();
			if(stato)
				rowData[2] = "Aperta";
			else
				rowData[2] = "Chiusa";
			
		}
		modelTabellaStanze.addRow(rowData);
	}
	
	public void comboBoxStanze() {
		String[] stanze = controllerCasa.getNomiStanze();
		for(String nome : stanze) {
			if(nome != null)  {
				comboBoxSelezioneStanze.addItem(nome);
			}
		}
	}
	
	public void rimuoviRigheTabellaStanze() {
		int numeroRighe = modelTabellaStanze.getRowCount();
		for (int i = numeroRighe - 1; i >= 0; i--) {
		    modelTabellaStanze.removeRow(i);
		}
	}
}
