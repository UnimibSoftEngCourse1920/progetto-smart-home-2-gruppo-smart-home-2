package application.frontend.views;


import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import application.backend.dominio.ElementoProgrammabile;
import application.backend.dominio.Stanza;
import application.controllers.ControllerCasa;
import application.controllers.Simulazione;
import application.frontend.support.Alert;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import java.awt.Color;
import javax.swing.JButton;


public class AggiungiProgrammaView extends JPanel {
	private Simulazione s;
	private JLayeredPane panelPrincipale;
	private JPanel panelSelezioneStanzaElementoTipo;
	private JPanel panelSelezioneGiorni;
	private JPanel panelSelezioneSettimanale;
	private JPanel panelSelezioneGiornaliero;
	private JLabel labelProgramma;
	private ControllerCasa controllerCasa;
	
	//PanelSelezioneStanzaElementoTipo--------------------
	private JLabel labelSelezioneStanza;
	private JComboBox comboBoxSelezioneStanza;
	private JLabel labelSelezioneElemento;
	private JComboBox comboBoxSelezioneElemento;
	private JLabel labelSelezioneTipo;
	private JComboBox comboBoxSelezioneTipo;
	
	//PanelSelezioneSettimanale------------------------
	private JCheckBox checkBoxLunedi;
	private JCheckBox checkBoxMartedi;
	private JCheckBox checkBoxMercoledi;
	private JCheckBox checkBoxGiovedi;
	private JCheckBox checkBoxVenerdi;
	private JCheckBox checkBoxSabato;
	private JCheckBox checkBoxDomenica;
	private JLabel labelInizioLunedi;
	private JLabel labelInizioMartedi;
	private JLabel labelInizioMercoledi;
	private JLabel labelInizioGiovedi;
	private JLabel labelInizioVenerdi;
	private JLabel labelInizioSabato;
	private JLabel labelInizioDomenica;
	private JSpinner spinnerLunedi;
	private JSpinner spinnerMartedi;
	private JSpinner spinnerMercoledi;
	private JSpinner spinnerGiovedi;
	private JSpinner spinnerVenerdi;
	private JSpinner spinnerSabato;
	private JSpinner spinnerDomenica;
	private JButton bottoneAggiungiSettimanale;
	
	
	
	//PanelSelezioneGiornaliero
	private JLabel labelInizioGiornaliero;
	private JSpinner spinnerGiornaliero;
	private JButton bottoneAggiungiGiornaliero;
	
	public AggiungiProgrammaView(JLayeredPane principale, Simulazione s) {
		this.panelPrincipale = principale;
		this.controllerCasa = new ControllerCasa(panelPrincipale); 
		this.s = s;
		
		inizializzazione();
	}
	
	public void inizializzazione() {
		labelProgramma = new JLabel("AGGIUNGI PROGRAMMA");
		labelProgramma.setHorizontalAlignment(SwingConstants.CENTER);
		labelProgramma.setFont(new Font("Arial", Font.PLAIN, 25));
		
		//panelSelezioneStanzaElementoTipo----------------------------
		panelSelezioneStanzaElementoTipo = new JPanel();
		labelSelezioneStanza = new JLabel("Seleziona la stanza:");
		comboBoxSelezioneStanza = new JComboBox();
		labelSelezioneElemento = new JLabel("Seleziona elemento:");
		comboBoxSelezioneElemento = new JComboBox();
		comboBoxSelezioneElemento.setEnabled(false);
		labelSelezioneTipo = new JLabel("Tipo:");
		comboBoxSelezioneTipo = new JComboBox();
		
		
		panelSelezioneGiorni = new JPanel();
		
		
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
		SpinnerDateModel spinnerModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		
		//panelSelezioneSettimanale-----------------------
		panelSelezioneSettimanale = new JPanel();
		panelSelezioneSettimanale.setVisible(false);
		checkBoxLunedi = new JCheckBox("Lunedi");
		checkBoxMartedi = new JCheckBox("Martedi");
		checkBoxMercoledi = new JCheckBox("Mercoledi");
		checkBoxGiovedi = new JCheckBox("Giovedi");
		checkBoxVenerdi = new JCheckBox("Venerdi");
		checkBoxSabato = new JCheckBox("Sabato");
		checkBoxDomenica = new JCheckBox("Domenica");
		labelInizioLunedi = new JLabel("Ora inizio:");
		labelInizioMartedi = new JLabel("Ora inizio:");
		labelInizioMercoledi = new JLabel("Ora inizio:");
		labelInizioGiovedi = new JLabel("Ora inizio:");
		labelInizioVenerdi = new JLabel("Ora inizio:");
		labelInizioSabato = new JLabel("Ora inizio:");
		labelInizioDomenica = new JLabel("Ora inizio:");
		spinnerLunedi = new JSpinner(spinnerModel);
		spinnerMartedi = new JSpinner(spinnerModel);
		spinnerMercoledi = new JSpinner(spinnerModel);
		spinnerGiovedi = new JSpinner(spinnerModel);
		spinnerVenerdi = new JSpinner(spinnerModel);
		spinnerSabato = new JSpinner(spinnerModel);
		spinnerDomenica = new JSpinner(spinnerModel);
		JSpinner.DateEditor dateEditorLunedi = new JSpinner.DateEditor(spinnerLunedi, "HH:mm");
		spinnerLunedi.setEditor(dateEditorLunedi);
		JSpinner.DateEditor dateEditorMartedi = new JSpinner.DateEditor(spinnerMartedi, "HH:mm");
		spinnerMartedi.setEditor(dateEditorMartedi);
		JSpinner.DateEditor dateEditorMercoledi = new JSpinner.DateEditor(spinnerMercoledi, "HH:mm");
		spinnerMercoledi.setEditor(dateEditorMercoledi);
		JSpinner.DateEditor dateEditorGiovedi = new JSpinner.DateEditor(spinnerGiovedi, "HH:mm");
		spinnerGiovedi.setEditor(dateEditorGiovedi);
		JSpinner.DateEditor dateEditorVenerdi = new JSpinner.DateEditor(spinnerVenerdi, "HH:mm");
		spinnerVenerdi.setEditor(dateEditorVenerdi);
		JSpinner.DateEditor dateEditorSabato = new JSpinner.DateEditor(spinnerSabato, "HH:mm");
		spinnerSabato.setEditor(dateEditorSabato);
		JSpinner.DateEditor dateEditorDomenica = new JSpinner.DateEditor(spinnerDomenica, "HH:mm");
		spinnerDomenica.setEditor(dateEditorDomenica);
		bottoneAggiungiSettimanale = new JButton("Aggiungi");
		
		
		//panelSelezioneGiornaliero------------------------
		panelSelezioneGiornaliero = new JPanel();
		panelSelezioneGiornaliero.setVisible(false);
		labelInizioGiornaliero = new JLabel("Ora inizio:");
		bottoneAggiungiGiornaliero = new JButton("Aggiungi");
		
		spinnerGiornaliero = new JSpinner(spinnerModel);
		JSpinner.DateEditor dateEditorGiornaliero = new JSpinner.DateEditor(spinnerGiornaliero, "HH:mm");
		spinnerGiornaliero.setEditor(dateEditorGiornaliero);
		
		
		
		
		
		comboBoxStanze();
		comboBoxTipo();
		
		setLayoutProgramma();
		
		gestioneProgramma();
		
		gestioneSelezioneSettimanale();
		
		gestioneAggiungiGiornaliero();
	}
	
	public void setLayoutProgramma() {
		GroupLayout gl_panelSelezioneGiorni = new GroupLayout(panelSelezioneGiorni);
		gl_panelSelezioneGiorni.setHorizontalGroup(
			gl_panelSelezioneGiorni.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSelezioneGiornaliero, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
				.addComponent(panelSelezioneSettimanale, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
		);
		gl_panelSelezioneGiorni.setVerticalGroup(
			gl_panelSelezioneGiorni.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneGiorni.createSequentialGroup()
					.addComponent(panelSelezioneGiornaliero, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSelezioneSettimanale, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
		);
		
		GroupLayout gl_panelSelezioneSettimanale = new GroupLayout(panelSelezioneSettimanale);
		gl_panelSelezioneSettimanale.setHorizontalGroup(
			gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBoxLunedi, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(checkBoxMartedi, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(checkBoxMercoledi, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(checkBoxGiovedi, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(checkBoxVenerdi, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(checkBoxSabato, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(checkBoxDomenica, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
					.addGap(90)
					.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioLunedi, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(spinnerLunedi, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioMartedi, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerMartedi, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioMercoledi, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerMercoledi, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(174)
							.addComponent(bottoneAggiungiSettimanale, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioGiovedi, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerGiovedi, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioVenerdi, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerVenerdi, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioSabato, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerSabato, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioDomenica, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerDomenica, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
					.addGap(232))
		);
		gl_panelSelezioneSettimanale.setVerticalGroup(
			gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addGap(3)
							.addComponent(labelInizioLunedi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(269))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(spinnerLunedi)
							.addGap(77)
							.addComponent(bottoneAggiungiSettimanale)
							.addGap(166))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(checkBoxLunedi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxMartedi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioMartedi)
								.addComponent(spinnerMartedi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxMercoledi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioMercoledi)
								.addComponent(spinnerMercoledi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxGiovedi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioGiovedi)
								.addComponent(spinnerGiovedi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxVenerdi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(spinnerVenerdi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioVenerdi))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSabato, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(spinnerSabato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioSabato))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxDomenica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spinnerDomenica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioDomenica))
							.addGap(17))))
		);
		panelSelezioneSettimanale.setLayout(gl_panelSelezioneSettimanale);
		panelSelezioneGiorni.setLayout(gl_panelSelezioneGiorni);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelProgramma, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
				.addComponent(panelSelezioneGiorni, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
				.addComponent(panelSelezioneStanzaElementoTipo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelProgramma, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelSelezioneStanzaElementoTipo, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSelezioneGiorni, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		GroupLayout gl_panelStanzaElementoTipo = new GroupLayout(panelSelezioneStanzaElementoTipo);
		gl_panelStanzaElementoTipo.setHorizontalGroup(
				gl_panelStanzaElementoTipo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelStanzaElementoTipo.createSequentialGroup()
					.addGap(20)
					.addComponent(labelSelezioneStanza, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSelezioneStanza, 0, 151, Short.MAX_VALUE)
					.addGap(29)
					.addComponent(labelSelezioneElemento, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSelezioneElemento, 0, 154, Short.MAX_VALUE)
					.addGap(55)
					.addComponent(labelSelezioneTipo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSelezioneTipo, 0, 169, Short.MAX_VALUE)
					.addGap(43))
		);
		gl_panelStanzaElementoTipo.setVerticalGroup(
				gl_panelStanzaElementoTipo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelStanzaElementoTipo.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panelStanzaElementoTipo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelSelezioneStanza, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxSelezioneStanza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSelezioneElemento, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxSelezioneElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSelezioneTipo)
						.addComponent(comboBoxSelezioneTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelSelezioneStanzaElementoTipo.setLayout(gl_panelStanzaElementoTipo);

		GroupLayout gl_panelSelezioneGiornaliero = new GroupLayout(panelSelezioneGiornaliero);
		gl_panelSelezioneGiornaliero.setHorizontalGroup(
			gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneGiornaliero.createSequentialGroup()
					.addGap(236)
					.addComponent(labelInizioGiornaliero, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerGiornaliero, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addGap(88)
					.addComponent(bottoneAggiungiGiornaliero)
					.addGap(304))
		);
		gl_panelSelezioneGiornaliero.setVerticalGroup(
			gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneGiornaliero.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelInizioGiornaliero)
						.addComponent(spinnerGiornaliero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bottoneAggiungiGiornaliero))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panelSelezioneGiornaliero.setLayout(gl_panelSelezioneGiornaliero);
	}
	
	public void gestioneProgramma() {
		comboBoxSelezioneStanza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelezioneGiornaliero.setVisible(false);
            	panelSelezioneSettimanale.setVisible(false);
				comboBoxSelezioneElemento.removeAllItems();
				String nomeStanza;
            	if (comboBoxSelezioneStanza.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanza.getSelectedItem().toString();
                    Stanza stanza = controllerCasa.getStanza(nomeStanza);
                    
                    if(stanza != null)
                    	comboBoxElementi(nomeStanza);
            	}
			}
		});
		comboBoxSelezioneTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxSelezioneElemento.isEnabled()) {
					if (comboBoxSelezioneTipo.getSelectedItem() != null) {
			            String tipo = comboBoxSelezioneTipo.getSelectedItem().toString();
			            if(tipo.equals("Giornaliero")) {
			            	panelSelezioneGiornaliero.setVisible(true);
			            	panelSelezioneSettimanale.setVisible(false);
			            }
			            else {
			            	panelSelezioneSettimanale.setVisible(true);
			            	panelSelezioneGiornaliero.setVisible(false);
			            }
			        }
				}
				else
					(new Alert()).errore("Devi selezionare un oggetto", "Attenzione");
			}
		});
		comboBoxSelezioneElemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	panelSelezioneSettimanale.setVisible(false);
            	panelSelezioneGiornaliero.setVisible(false); 
        	}
		});
	}
	
	public void comboBoxStanze() {
		String[] stanze = controllerCasa.getNomiStanze();
		for(String nome : stanze) {
			if(nome != null)  {
				comboBoxSelezioneStanza.addItem(nome);
			}
		}
	}
	
	public void comboBoxElementi(String nomeStanza) {
		String stringa = "";
		ArrayList<Object> elementi = new ArrayList<>();
		if(controllerCasa.getStanza(nomeStanza).getSensoreTemperatura() != null)
			elementi.add(controllerCasa.getStanza(nomeStanza).getSensoreTemperatura());
		if(controllerCasa.getRobot() != null)
			elementi.add(controllerCasa.getRobot());
		if(controllerCasa.getStanza(nomeStanza).getElementi() != null)
			elementi.addAll(controllerCasa.getStanza(nomeStanza).getElementi());
		
		//System.out.println(elementi.size());
		
		if(elementi.isEmpty()) {
			(new Alert()).info("La stanza non contiene oggetti programmabili", "Information");
			comboBoxSelezioneElemento.setEnabled(false);
		}
		else {
			comboBoxSelezioneElemento.setEnabled(true);
			for(Object oggetto : elementi) {
				if(oggetto != null)  {
					if(oggetto instanceof ElementoProgrammabile)
						stringa = ((ElementoProgrammabile) oggetto).getTipo();
					comboBoxSelezioneElemento.addItem(oggetto.getClass().getSimpleName() + " " + stringa);
				}
			}
		}
	}
	
	public void comboBoxTipo() {
		//System.out.println("ciao");
		comboBoxSelezioneTipo.setEnabled(true);
		comboBoxSelezioneTipo.addItem("Giornaliero");
		comboBoxSelezioneTipo.addItem("Settimanale");
		
	}
	
	public void gestioneSelezioneSettimanale() {
		if(!checkBoxLunedi.isSelected()) {
			spinnerLunedi.setEnabled(false);
		}
		if(!checkBoxMartedi.isSelected()) {
			spinnerMartedi.setEnabled(false);
		}
		if(!checkBoxMercoledi.isSelected()) {
			spinnerMercoledi.setEnabled(false);
		}
		if(!checkBoxGiovedi.isSelected()) {
			spinnerGiovedi.setEnabled(false);
		}
		if(!checkBoxVenerdi.isSelected()) {
			spinnerVenerdi.setEnabled(false);
		}
		if(!checkBoxSabato.isSelected()) {
			spinnerSabato.setEnabled(false);
		}
		if(!checkBoxDomenica.isSelected()) {
			spinnerDomenica.setEnabled(false);
		}
		
		checkBoxLunedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxLunedi.isSelected()) {
					spinnerLunedi.setEnabled(true);
				}
				else
					spinnerLunedi.setEnabled(false);
			}
		});
		checkBoxMartedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxMartedi.isSelected()) {
					spinnerMartedi.setEnabled(true);
				}
				else
					spinnerMartedi.setEnabled(false);
			}
		});
		checkBoxMercoledi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxMercoledi.isSelected()) {
					spinnerMercoledi.setEnabled(true);
				}
				else
					spinnerMercoledi.setEnabled(false);
			}
		});
		checkBoxGiovedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxGiovedi.isSelected()) {
					spinnerGiovedi.setEnabled(true);
				}
				else
					spinnerGiovedi.setEnabled(false);
			}
		});
		checkBoxVenerdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxVenerdi.isSelected()) {
					spinnerVenerdi.setEnabled(true);
				}
				else
					spinnerVenerdi.setEnabled(false);
			}
		});
		checkBoxSabato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSabato.isSelected()) {
					spinnerSabato.setEnabled(true);
				}
				else
					spinnerSabato.setEnabled(false);
			}
		});
		checkBoxDomenica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxDomenica.isSelected()) {
					spinnerDomenica.setEnabled(true);
				}
				else
					spinnerDomenica.setEnabled(false);
			}
		});
		
	}
	
	public void gestioneAggiungiGiornaliero() {
		bottoneAggiungiGiornaliero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeStanza = null;
				String nomeClasseElemento = null;
				
				if(comboBoxSelezioneStanza.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanza.getSelectedItem().toString();
				}
				else
					(new Alert()).errore("Devi selezionare una stanza", "Attenzione");
				
				if(comboBoxSelezioneElemento.getSelectedItem() != null) {
					nomeClasseElemento = comboBoxSelezioneElemento.getSelectedItem().toString();
				}
				else
					(new Alert()).errore("Devi selezionare un oggetto", "Attenzione");
				
				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				String value = (formato.format(spinnerGiornaliero.getValue())).toString();
				Date data = null;
				try {
					data = formato.parse(value);
					int ore = data.getHours();
					int minuti = data.getMinutes();
					
					
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					(new Alert()).errore("Errore nella scelta del tempo di inizio", "Attenzione");
				}
			}
		});
	}
}
