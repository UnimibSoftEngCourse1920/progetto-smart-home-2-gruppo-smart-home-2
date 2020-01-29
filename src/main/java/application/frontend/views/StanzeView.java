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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import application.backend.dominio.*;
import application.backend.sensori.SensoreTemperatura;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class StanzeView extends JPanel {
	private ControllerCasa controllerCasa;
	private JLayeredPane panelPrincipale;
	private JPanel panelTabellaStanze;
	private JPanel panelSelezioneStanza;
	private JLabel labelStanze;
	private JComboBox comboBoxSelezioneStanza;
	private JLabel labelSelezioneStanza;
	private JTable tabellaStanze;
	private JScrollPane scrollPaneTabellaStanze;
	private DefaultTableModel modelTabellaStanze;
	private Stanza stanzaSelezionata;
	private Object[] rowData;
	
	private JTextField textValore;
	private JButton btnInvia;
	private JLabel labelInserimento;
	
	public StanzeView(JLayeredPane principale, ControllerCasa casa) {
		panelPrincipale = principale;
		controllerCasa = casa;
		
		inizializzazione();
	}
	
	public void inizializzazione() {
		labelStanze = new JLabel("STANZE");
		labelStanze.setHorizontalAlignment(SwingConstants.CENTER);
		labelStanze.setFont(new Font("Arial", Font.PLAIN, 25));
		panelTabellaStanze = new JPanel();
		panelSelezioneStanza = new JPanel();
		labelSelezioneStanza = new JLabel("Seleziona la stanza:");
		comboBoxSelezioneStanza = new JComboBox();
		btnInvia = new JButton("Invia");
		btnInvia.setVisible(false);
		labelInserimento = new JLabel("Inserisci qui la temperatura da impostare:");
		labelInserimento.setVisible(false);
		textValore = new JTextField();
		textValore.setVisible(false);
		
		//TABELLA-----------------------------------------------------------------------------------------
		tabellaStanze = new JTable();
		tabellaStanze.setFont(new Font("Arial", Font.PLAIN, 11));
		tabellaStanze.setEnabled(false);
		tabellaStanze.setRowHeight(40);
		
		scrollPaneTabellaStanze = new JScrollPane(tabellaStanze);
		scrollPaneTabellaStanze.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		String[] colonne = {"Elemento", "ID", "Temperatura Attuale", "Temperatura Desiderata", "Cambia Stato"};
		modelTabellaStanze = (DefaultTableModel) tabellaStanze.getModel();
		modelTabellaStanze.setColumnIdentifiers(colonne);
		
		comboBoxStanze();
		setLayoutStanze();
		
		gestioneStanze();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabellaStanze.setDefaultRenderer(Object.class, centerRenderer);
	}
	
	public void setLayoutStanze() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelStanze, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
				.addComponent(panelSelezioneStanza, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
				.addComponent(panelTabellaStanze, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelStanze, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSelezioneStanza, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabellaStanze, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		labelInserimento.setHorizontalAlignment(SwingConstants.CENTER);
		labelInserimento.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		textValore.setColumns(10);
		
		GroupLayout glpanelTabellaStanze = new GroupLayout(panelTabellaStanze);
		glpanelTabellaStanze.setHorizontalGroup(
			glpanelTabellaStanze.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, glpanelTabellaStanze.createSequentialGroup()
					.addGap(67)
					.addComponent(scrollPaneTabellaStanze, GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
					.addGap(41))
				.addGroup(Alignment.LEADING, glpanelTabellaStanze.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelInserimento, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textValore, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnInvia, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(396, Short.MAX_VALUE))
		);
		glpanelTabellaStanze.setVerticalGroup(
			glpanelTabellaStanze.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelTabellaStanze.createSequentialGroup()
					.addGap(34)
					.addComponent(scrollPaneTabellaStanze, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addGap(31)
					.addGroup(glpanelTabellaStanze.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelInserimento, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(textValore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInvia, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(43))
		);
		panelTabellaStanze.setLayout(glpanelTabellaStanze);
		
		GroupLayout glpanelSelezioneStanze = new GroupLayout(panelSelezioneStanza);
		glpanelSelezioneStanze.setHorizontalGroup(
				glpanelSelezioneStanze.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelSelezioneStanze.createSequentialGroup()
					.addGap(322)
					.addComponent(labelSelezioneStanza, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSelezioneStanza, 0, 185, Short.MAX_VALUE)
					.addGap(248))
		);
		glpanelSelezioneStanze.setVerticalGroup(
				glpanelSelezioneStanze.createParallelGroup(Alignment.TRAILING)
				.addGroup(glpanelSelezioneStanze.createSequentialGroup()
					.addContainerGap()
					.addGroup(glpanelSelezioneStanze.createParallelGroup(Alignment.BASELINE)
						.addGroup(glpanelSelezioneStanze.createSequentialGroup()
							.addGap(4)
							.addComponent(comboBoxSelezioneStanza))
						.addComponent(labelSelezioneStanza, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		panelSelezioneStanza.setLayout(glpanelSelezioneStanze);
	}
	
	public void gestioneStanze() {
		comboBoxSelezioneStanza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	rimuoviRigheTabellaStanze();
            	String nomeStanza;
            	if (comboBoxSelezioneStanza.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanza.getSelectedItem().toString();
                    Stanza stanza = controllerCasa.getStanza(nomeStanza);
                   
                    if(stanza != null) {
                    	stanzaSelezionata = stanza;
        				viewTabellaStanze(stanza);
        				if(stanza.getSensoreTemperatura() != null) {
        				}
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
			         
			        //GESTIONE CAMBIA STATO----------------------------------------------------------------
			        if(column == 4) {
			        	String tipoOggetto = (String) tabellaStanze.getModel().getValueAt(row, 0);
			        	int id = 0;
			        	if(tabellaStanze.getModel().getValueAt(row, 1) != "")
			        		id = (int) tabellaStanze.getModel().getValueAt(row, 1);
						
						if(tipoOggetto.equals("Lampada")) {
							controllerCasa.cambiaStatoLampada(id, stanzaSelezionata);
						}
						else if(tipoOggetto.equals("Finestra")) {
							controllerCasa.cambiaStatoFinestra(id, stanzaSelezionata);
						}
						else if(tipoOggetto.equals("Tapparella")) {
							controllerCasa.cambiaStatoTapparella(id, stanzaSelezionata);;
						}
						else if(tipoOggetto.equals("SensoreTemperatura")) {
							controllerCasa.cambiaStatoSensoreTemperatura(stanzaSelezionata);
							SensoreTemperatura s = stanzaSelezionata.getSensoreTemperatura();
							if(s.getStato() == "Spento") {
								stanzaSelezionata.accendiTermostato();
								btnInvia.setVisible(true);
	        					labelInserimento.setVisible(true);
	        					textValore.setVisible(true);
							}
							else {
								stanzaSelezionata.spegniTermostato();
								btnInvia.setVisible(false);
	        					labelInserimento.setVisible(false);
	        					textValore.setVisible(false);
							}
						}
						else if(tipoOggetto.equals("Lavatrice")) {
							controllerCasa.cambiaStatoLavatrice(stanzaSelezionata);
						}
						else if(tipoOggetto.equals("Lavastoviglie")) {
							controllerCasa.cambiaStatoLavastoviglie(stanzaSelezionata);
						}
						
						rimuoviRigheTabellaStanze();
						viewTabellaStanze(stanzaSelezionata);
			        }
			       
			}
		});
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						double temperaturaInserita = Double.parseDouble(textValore.getText());
						controllerCasa.cambiaTempDesiderata(getStanzaSelezionata().getNome(), temperaturaInserita);
						rimuoviRigheTabellaStanze();
						getStanzaSelezionata().getSensoreTemperatura().run();
						viewTabellaStanze(getStanzaSelezionata());
					}
					catch(Exception ex) {
						(new Alert()).errore("Il valore è vuoto oppure non contiene un numero", "Errore");
					}
			}
		});
	}
	
	public void viewTabellaStanze(Stanza stanza) {
		tabellaStanze.setVisible(true);
		
		List<Object> listaOggettiStanza = controllerCasa.getAllOggettiStanza(stanza);
		
		int numeroOggettiStanza = listaOggettiStanza.size();
		//System.out.println(numeroOggettiStanza);
		
		Object oggettoStanza;
		
		if(numeroOggettiStanza != 0) {
			rowData = new Object[5];
			
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
		String statoSensoreTemperatura = "";
		ColoreCellaTabella renderer = new ColoreCellaTabella();
		tabellaStanze.setDefaultRenderer(Object.class, renderer);
		rowData[0] = oggettoStanza.getClass().getSimpleName();
		
		if(oggettoStanza instanceof Lampada) {
			stato = controllerCasa.lampadaIsAccesa((Lampada) oggettoStanza);
			rowData[1] = controllerCasa.getIdLampada((Lampada) oggettoStanza);
			
			if(stato)
				rowData[4] = "Accesa";
			else
				rowData[4] = "Spenta";
			
			rowData[2] = "";
			rowData[3] = "";
		}
		else if(oggettoStanza instanceof Finestra) {
			stato = controllerCasa.finestraIsAperta((Finestra) oggettoStanza);
			rowData[1] = controllerCasa.getIdFinestra((Finestra) oggettoStanza);
			
			if(stato)
				rowData[4] = "Aperta";
			else
				rowData[4] = "Chiusa";
			
			rowData[2] = "";
			rowData[3] = "";
		}
		else if(oggettoStanza instanceof Tapparella) {
			stato = controllerCasa.tapparellaIsAperta((Tapparella) oggettoStanza);
			rowData[1] = controllerCasa.getIdTapparella((Tapparella) oggettoStanza);
			
			if(stato)
				rowData[4] = "Aperta";
			else
				rowData[4] = "Chiusa";
			
			rowData[2] = "";
			rowData[3] = "";
			
		}
		else if(oggettoStanza instanceof SensoreTemperatura) {
			//System.out.println("ciao");
			rowData[1] = "";
			NumberFormat nf = new DecimalFormat("0.00");
			statoSensoreTemperatura = controllerCasa.getStatoSensoreTemperatura((SensoreTemperatura) oggettoStanza);
			//statoSensoreTemperatura = s.getStato();
			rowData[2] = nf.format(controllerCasa.getTemperaturaCorrenteSensoreTemperatura((SensoreTemperatura) oggettoStanza));
			rowData[3] = nf.format(controllerCasa.getTemperaturaDesiderataSensoreTemperatura((SensoreTemperatura) oggettoStanza));
			rowData[4] = statoSensoreTemperatura;
		}
		else if(oggettoStanza instanceof Lavatrice) {
			stato = controllerCasa.lavatriceIsInFunzione((Lavatrice) oggettoStanza);
			rowData[1] = "";
			
			if(stato)
				rowData[4] = "In Funzione";
			else
				rowData[4] = "Spenta";
			
			rowData[2] = "";
			rowData[3] = "";
		}
		else if(oggettoStanza instanceof Lavastoviglie) {
			stato = controllerCasa.lavastoviglieIsInFunzione((Lavastoviglie) oggettoStanza);
			rowData[1] = "";
			
			if(stato)
				rowData[4] = "In Funzione";
			else
				rowData[4] = "Spenta";
			
			rowData[2] = "";
			rowData[3] = "";
		}
		
		modelTabellaStanze.addRow(rowData);
	}
	
	public void comboBoxStanze() {
		String[] stanze = controllerCasa.getNomiStanze();
		for(String nome : stanze) {
			if(nome != null)  {
				comboBoxSelezioneStanza.addItem(nome);
			}
		}
	}
	
	public void rimuoviRigheTabellaStanze() {
		int numeroRighe = modelTabellaStanze.getRowCount();
		for (int i = numeroRighe - 1; i >= 0; i--) {
		    modelTabellaStanze.removeRow(i);
		}
	}
	
	public Stanza getStanzaSelezionata() {
		return this.stanzaSelezionata;
	}
}
