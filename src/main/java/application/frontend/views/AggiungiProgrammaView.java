package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import application.backend.dominio.Stanza;
import application.controllers.ControllerCasa;
import application.controllers.ControllerProgramma;
import application.frontend.support.Alert;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerDateModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;


public class AggiungiProgrammaView extends JPanel {
	private JLayeredPane panelPrincipale;
	private JPanel panelSelezioneStanzaElementoTipo;
	private JPanel panelSelezioneGiorni;
	private JPanel panelSelezioneSettimanale;
	private JPanel panelSelezioneGiornaliero;
	private JLabel labelProgramma;
	private transient ControllerCasa controllerCasa;
	private transient ControllerProgramma controllerProgramma;
	
	//PanelSelezioneStanzaElementoTipo--------------------
	private JLabel labelSelezioneStanza;
	private JComboBox comboBoxSelezioneStanza;
	private JLabel labelSelezioneElemento;
	private JComboBox comboBoxSelezioneElemento;
	private JLabel labelSelezioneTipo;
	private JComboBox comboBoxSelezioneTipo;
	
	//PanelSelezioneSettimanale------------------------
	private JCheckBox[] checkBoxSettimanale;
	private JLabel[] labelInizioSettimanale;
	private JSpinner[] spinnerSettimanale;
	private JButton bottoneAggiungiSettimanale;
	private JLabel[] labelTempSettimanale;
	private JTextField[] textTempSettimanale;
	private String oggettoSelezionato;

	//PanelSelezioneGiornaliero-----------------------
	private JLabel labelInizioGiornaliero;
	private JSpinner spinnerGiornaliero;
	private JButton bottoneAggiungiGiornaliero;
	private JLabel labelTempGiornaliero;
	private JTextField textTempGiornaliero;
	
	public AggiungiProgrammaView(JLayeredPane principale, ControllerCasa casa, ControllerProgramma controllerProgramma) {
		this.panelPrincipale = principale;
		this.controllerCasa = casa;
		this.controllerProgramma = controllerProgramma;
		
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
		checkBoxSettimanale = new JCheckBox[7];
		labelInizioSettimanale = new JLabel[7];
		spinnerSettimanale = new JSpinner[7];
		labelTempSettimanale = new JLabel[7];
		textTempSettimanale = new JTextField[7];
		SpinnerDateModel[] modelSpinnerSettimanale = new SpinnerDateModel[7];
		checkBoxSettimanale[0] = new JCheckBox("Lunedi");
		checkBoxSettimanale[1] = new JCheckBox("Martedi");
		checkBoxSettimanale[2] = new JCheckBox("Mercoledi");
		checkBoxSettimanale[3] = new JCheckBox("Giovedi");
		checkBoxSettimanale[4] = new JCheckBox("Venerdi");
		checkBoxSettimanale[5] = new JCheckBox("Sabato");
		checkBoxSettimanale[6] = new JCheckBox("Domenica");
		
		
		JSpinner.DateEditor dateEditorSettimanale;
		for(int i = 0; i < 7; i++) {
			labelInizioSettimanale[i] = new JLabel("Ora inizio:");
			labelTempSettimanale[i] = new JLabel("Valore Temperatura:");
			textTempSettimanale[i] = new JTextField();
			textTempSettimanale[i].setColumns(10);
			//gestione 7 jspinner
			modelSpinnerSettimanale[i] = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
			spinnerSettimanale[i] = new JSpinner(modelSpinnerSettimanale[i]);
			dateEditorSettimanale = new JSpinner.DateEditor(spinnerSettimanale[i], "HH:mm");
			spinnerSettimanale[i].setEditor(dateEditorSettimanale);
		}
		
		oggettoSelezionato = "";
		
		
		bottoneAggiungiSettimanale = new JButton("Aggiungi");
		
		//panelSelezioneGiornaliero------------------------
		panelSelezioneGiornaliero = new JPanel();
		panelSelezioneGiornaliero.setVisible(false);
		labelInizioGiornaliero = new JLabel("Ora inizio:");
		bottoneAggiungiGiornaliero = new JButton("Aggiungi");
		textTempGiornaliero = new JTextField();
		textTempGiornaliero.setColumns(10);
		labelTempGiornaliero = new JLabel("Valore Temperatura:");
		spinnerGiornaliero = new JSpinner(spinnerModel);
		JSpinner.DateEditor dateEditorGiornaliero = new JSpinner.DateEditor(spinnerGiornaliero, "HH:mm");
		spinnerGiornaliero.setEditor(dateEditorGiornaliero);
		
		comboBoxStanze();
		comboBoxTipo();
		
		setLayoutProgramma();
		
		gestioneProgramma();
		
		gestioneSelezioneSettimanale();
		
		gestioneAggiungiGiornaliero();
		
		gestioneAggiungiSettimanale();
	}
	
	public void setLayoutProgramma() {
		
		GroupLayout glpanelSelezioneGiorni = new GroupLayout(panelSelezioneGiorni);
		glpanelSelezioneGiorni.setHorizontalGroup(
				glpanelSelezioneGiorni.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSelezioneGiornaliero, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
				.addComponent(panelSelezioneSettimanale, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
		);
		glpanelSelezioneGiorni.setVerticalGroup(
				glpanelSelezioneGiorni.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelSelezioneGiorni.createSequentialGroup()
					.addComponent(panelSelezioneGiornaliero, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSelezioneSettimanale, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
		);
		
		GroupLayout glpanelSelezioneSettimanale = new GroupLayout(panelSelezioneSettimanale);
		glpanelSelezioneSettimanale.setHorizontalGroup(
				glpanelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
					.addGap(71)
					.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBoxSettimanale[0], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[1], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[2], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[3], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[4], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[5], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[6], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
					.addGap(90)
					.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioSettimanale[2], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerSettimanale[2], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(labelTempSettimanale[2], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textTempSettimanale[2], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(bottoneAggiungiSettimanale, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(122))
						.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
							.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
								.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[0], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(spinnerSettimanale[0], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[0], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[0], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[1], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[1], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[1], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[1], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[3], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[3], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[3], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[3], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[4], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[4], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[4], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[4], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[5], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[5], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[5], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[5], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[6], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[6], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[6], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[6], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(242, Short.MAX_VALUE))))
		);
		glpanelSelezioneSettimanale.setVerticalGroup(
				glpanelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
					.addGap(18)
					.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
							.addGap(3)
							.addComponent(labelInizioSettimanale[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(80)
							.addComponent(bottoneAggiungiSettimanale)
							.addGap(180))
						.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
							.addComponent(spinnerSettimanale[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(labelTempSettimanale[0])
							.addComponent(textTempSettimanale[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(glpanelSelezioneSettimanale.createSequentialGroup()
							.addComponent(checkBoxSettimanale[0], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[1], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioSettimanale[1])
								.addComponent(spinnerSettimanale[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[1])
								.addComponent(textTempSettimanale[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[2], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioSettimanale[2])
								.addComponent(spinnerSettimanale[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[2])
								.addComponent(textTempSettimanale[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[3], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioSettimanale[3])
								.addComponent(spinnerSettimanale[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[3])
								.addComponent(textTempSettimanale[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[4], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(spinnerSettimanale[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioSettimanale[4])
								.addComponent(labelTempSettimanale[4])
								.addComponent(textTempSettimanale[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[5], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(spinnerSettimanale[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioSettimanale[5])
								.addComponent(labelTempSettimanale[5])
								.addComponent(textTempSettimanale[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spinnerSettimanale[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioSettimanale[6])
								.addComponent(labelTempSettimanale[6])
								.addComponent(textTempSettimanale[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(17))))
		);
		panelSelezioneSettimanale.setLayout(glpanelSelezioneSettimanale);
		panelSelezioneGiorni.setLayout(glpanelSelezioneGiorni);
		
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

		GroupLayout glpanelStanzaElementoTipo = new GroupLayout(panelSelezioneStanzaElementoTipo);
		glpanelStanzaElementoTipo.setHorizontalGroup(
				glpanelStanzaElementoTipo.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelStanzaElementoTipo.createSequentialGroup()
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
		glpanelStanzaElementoTipo.setVerticalGroup(
				glpanelStanzaElementoTipo.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelStanzaElementoTipo.createSequentialGroup()
					.addGap(13)
					.addGroup(glpanelStanzaElementoTipo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelSelezioneStanza, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxSelezioneStanza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSelezioneElemento, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxSelezioneElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSelezioneTipo)
						.addComponent(comboBoxSelezioneTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelSelezioneStanzaElementoTipo.setLayout(glpanelStanzaElementoTipo);
		
		

		GroupLayout glpanelSelezioneGiornaliero = new GroupLayout(panelSelezioneGiornaliero);
		glpanelSelezioneGiornaliero.setHorizontalGroup(
				glpanelSelezioneGiornaliero.createParallelGroup(Alignment.TRAILING)
				.addGroup(glpanelSelezioneGiornaliero.createSequentialGroup()
					.addGap(151)
					.addComponent(labelInizioGiornaliero, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerGiornaliero, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(labelTempGiornaliero, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textTempGiornaliero, 111, 111, 111)
					.addGap(75)
					.addComponent(bottoneAggiungiGiornaliero)
					.addGap(146))
		);
		glpanelSelezioneGiornaliero.setVerticalGroup(
				glpanelSelezioneGiornaliero.createParallelGroup(Alignment.TRAILING)
				.addGroup(glpanelSelezioneGiornaliero.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(glpanelSelezioneGiornaliero.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelInizioGiornaliero)
						.addComponent(spinnerGiornaliero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bottoneAggiungiGiornaliero)
						.addComponent(labelTempGiornaliero)
						.addComponent(textTempGiornaliero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelSelezioneGiornaliero.setLayout(glpanelSelezioneGiornaliero);
	}
	
	public void gestioneProgramma() {
		comboBoxSelezioneStanza.addActionListener((e) -> {
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
		});
		comboBoxSelezioneTipo.addActionListener((e) -> {
				if(comboBoxSelezioneElemento.isEnabled()) {
					String elemento = comboBoxSelezioneElemento.getSelectedItem().toString();
					if (comboBoxSelezioneTipo.getSelectedItem() != null) {
			            String tipo = comboBoxSelezioneTipo.getSelectedItem().toString();
			            if(tipo.equals("Giornaliero")) {
			            	viewPanelGiornaliero(elemento);
			            	
			            }
			            else {
			            	panelSelezioneSettimanale.setVisible(true);
			            	panelSelezioneGiornaliero.setVisible(false);
			            }
			        }
				}
				else
					(new Alert()).errore("Devi selezionare un oggetto", "Attenzione");
		});
		comboBoxSelezioneElemento.addActionListener((e) -> {
				oggettoSelezionato = comboBoxSelezioneElemento.getSelectedItem().toString();
            	panelSelezioneSettimanale.setVisible(false);
            	panelSelezioneGiornaliero.setVisible(false); 
            	
            	comboBoxTipo();
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
		ArrayList<Object> elementi = new ArrayList<>();
		if(controllerCasa.getStanza(nomeStanza).getSensoreTemperatura() != null)
			elementi.add(controllerCasa.getStanza(nomeStanza).getSensoreTemperatura());
		if(controllerCasa.getRobot() != null && (controllerCasa.getRobot()).getBase().getNome().equals(nomeStanza))
			elementi.add(controllerCasa.getRobot());
		if(controllerCasa.getStanza(nomeStanza).getLavastoviglie() != null)
			elementi.add(controllerCasa.getStanza(nomeStanza).getLavastoviglie());
		if(controllerCasa.getStanza(nomeStanza).getLavatrice() != null)
			elementi.add(controllerCasa.getStanza(nomeStanza).getLavatrice());
		
		if(elementi.isEmpty()) {
			(new Alert()).info("La stanza non contiene oggetti programmabili", "Information");
			comboBoxSelezioneElemento.setEnabled(false);
		}
		else {
			comboBoxSelezioneElemento.setEnabled(true);
			for(Object oggetto : elementi) {
				if(oggetto != null)  {
					comboBoxSelezioneElemento.addItem(oggetto.getClass().getSimpleName());
				}
			}
		}
	}
	
	public void comboBoxTipo() {
		comboBoxSelezioneTipo.removeAllItems();
		
		comboBoxSelezioneTipo.setEnabled(true);
		if(oggettoSelezionato.equals("")) {
			comboBoxSelezioneTipo.addItem("Giornaliero");
			comboBoxSelezioneTipo.addItem("Settimanale");
		}
		else if(oggettoSelezionato.equals("SensoreTemperatura") || oggettoSelezionato.equals("RobotPulizia")) {
			comboBoxSelezioneTipo.addItem("Settimanale");
		}
		else {
			comboBoxSelezioneTipo.addItem("Giornaliero");
		}
		
	}
	
	public void gestioneSelezioneSettimanale() {
		for(int i = 0; i < 7; i++) {
			if(!checkBoxSettimanale[i].isSelected()) {
				spinnerSettimanale[i].setEnabled(false);
				labelTempSettimanale[i].setVisible(false);
				textTempSettimanale[i].setVisible(false);
			}
		}	
		
		//CHECKBOX LUNEDI----------------------------------------------------
		checkBoxSettimanale[0].addActionListener((e) -> {
				if(checkBoxSettimanale[0].isSelected()) {
					if(oggettoSelezionato.equals("SensoreTemperatura")) {
						labelTempSettimanale[0].setVisible(true);
						textTempSettimanale[0].setVisible(true);
					}
					spinnerSettimanale[0].setEnabled(true);
				}
				else {
					spinnerSettimanale[0].setEnabled(false);
					labelTempSettimanale[0].setVisible(false);
					textTempSettimanale[0].setVisible(false);
				}
		});
		//CHECKBOX MARTEDI----------------------------------------------------
		checkBoxSettimanale[1].addActionListener((e) -> {
				if(checkBoxSettimanale[1].isSelected()) {
					if(oggettoSelezionato.equals("SensoreTemperatura")) {
						labelTempSettimanale[1].setVisible(true);
						textTempSettimanale[1].setVisible(true);
					}
					spinnerSettimanale[1].setEnabled(true);
				}
				else {
					spinnerSettimanale[1].setEnabled(false);
					labelTempSettimanale[1].setVisible(false);
					textTempSettimanale[1].setVisible(false);
				}
		});
		//CHECKBOX MERCOLEDI----------------------------------------------------
		checkBoxSettimanale[2].addActionListener((e) -> {
				if(checkBoxSettimanale[2].isSelected()) {
					if(oggettoSelezionato.equals("SensoreTemperatura")) {
						labelTempSettimanale[2].setVisible(true);
						textTempSettimanale[2].setVisible(true);
					}
					spinnerSettimanale[2].setEnabled(true);
				}
				else {
					spinnerSettimanale[2].setEnabled(false);
					labelTempSettimanale[2].setVisible(false);
					textTempSettimanale[2].setVisible(false);
				}
		});
		//CHECKBOX GIOVEDI----------------------------------------------------
		checkBoxSettimanale[3].addActionListener((e) -> {
				if(checkBoxSettimanale[3].isSelected()) {
					if(oggettoSelezionato.equals("SensoreTemperatura")) {
						labelTempSettimanale[3].setVisible(true);
						textTempSettimanale[3].setVisible(true);
					}
					spinnerSettimanale[3].setEnabled(true);
				}
				else {
					spinnerSettimanale[3].setEnabled(false);
					labelTempSettimanale[3].setVisible(false);
					textTempSettimanale[3].setVisible(false);
				}
		});
		//CHECKBOX VENERDI----------------------------------------------------
		checkBoxSettimanale[4].addActionListener((e) -> {
				if(checkBoxSettimanale[4].isSelected()) {
					if(oggettoSelezionato.equals("SensoreTemperatura")) {
						labelTempSettimanale[4].setVisible(true);
						textTempSettimanale[4].setVisible(true);
					}
					spinnerSettimanale[4].setEnabled(true);
				}
				else {
					spinnerSettimanale[4].setEnabled(false);
					labelTempSettimanale[4].setVisible(false);
					textTempSettimanale[4].setVisible(false);
				}
		});
		//CHECKBOX SABATO----------------------------------------------------
		checkBoxSettimanale[5].addActionListener((e) -> {
				if(checkBoxSettimanale[5].isSelected()) {
					if(oggettoSelezionato.equals("SensoreTemperatura")) {
						labelTempSettimanale[5].setVisible(true);
						textTempSettimanale[5].setVisible(true);
					}
					spinnerSettimanale[5].setEnabled(true);
				}
				else {
					spinnerSettimanale[5].setEnabled(false);
					labelTempSettimanale[5].setVisible(false);
					textTempSettimanale[5].setVisible(false);
				}
		});
		//CHECKBOX DOMENICA----------------------------------------------------
		checkBoxSettimanale[6].addActionListener((e) -> {
				if(checkBoxSettimanale[6].isSelected()) {
					if(oggettoSelezionato.equals("SensoreTemperatura")) {
						labelTempSettimanale[6].setVisible(true);
						textTempSettimanale[6].setVisible(true);
					}
					spinnerSettimanale[6].setEnabled(true);
				}
				else {
					spinnerSettimanale[6].setEnabled(false);
					labelTempSettimanale[6].setVisible(false);
					textTempSettimanale[6].setVisible(false);
				}
		});
	}
	
	public void gestioneAggiungiGiornaliero() {
		bottoneAggiungiGiornaliero.addActionListener((e) ->{
				boolean errore = false;
				String nomeStanza = null;
				
				if(comboBoxSelezioneStanza.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanza.getSelectedItem().toString();
                    
                    if(comboBoxSelezioneElemento.getSelectedItem() != null) {
    					double tempDefault = 0;
    					int ore = 0;
    					int minuti = 0;
    					
    					SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        				String value = (formato.format(spinnerGiornaliero.getValue()));
        				Date data = null;
        				
        				
        				try {
        					data = formato.parse(value);
        					ore = data.getHours();
        					minuti = data.getMinutes();
        					
        					int oreCorrente = controllerCasa.getMain().getSimulazione().getOre();
        					int minutiCorrenti = controllerCasa.getMain().getSimulazione().getMinuti();
        					
        					if(ore >= oreCorrente) {
        						if((ore == oreCorrente && minuti > minutiCorrenti) || ore > oreCorrente) {
        							if(ore < 22) {
		        						if(oggettoSelezionato.equals("SensoreTemperatura")) {
		            						try {
		            							tempDefault = Double.parseDouble(textTempGiornaliero.getText());
		            							
		            							if(tempDefault <= 8 || tempDefault >= 28) {
		            								(new Alert()).errore("Il valore deve essere maggiore di 8 e minore di 28", "Attenzione");
		            								errore = true;
		            							}
		            							
		            							if(tempDefault == 0.0)
		            								tempDefault = 17;
		            						}catch (NumberFormatException error) {
		            							(new Alert()).errore("Il valore deve essere un numero", "Attenzione");
		            							errore = true;
		            						}
		            					}
        							}
        							else {
            							(new Alert()).errore("Il programma deve iniziare prima delle 22", "Attenzione");
                						errore = true;
            						}
        						}
        						else {
        							(new Alert()).errore("L'orario deve essere maggiore di quello dell'orologio", "Attenzione");
            						errore = true;
        						}
        					}
        					else {
        						(new Alert()).errore("L'orario deve essere maggiore di quello dell'orologio", "Attenzione");
        						errore = true;
        					}
        					
        				} catch (ParseException e1) {
        					(new Alert()).errore("Errore nella scelta del tempo di inizio", "Attenzione");
        					errore = true;
        				}
        				
        				if(!errore) {
        					controllerProgramma.nuovoProgrammaGiornaliero(nomeStanza, oggettoSelezionato, ore, minuti, tempDefault);
            				(new Alert()).info("Il ProgrammaGiornaliero è stato aggiunto", "Operazione effettuata con successo");
            				caricaProgrammiView();
        				}
                    }
    				else
    					(new Alert()).errore("Devi selezionare un oggetto", "Attenzione");
				}
				else
					(new Alert()).errore("Devi selezionare una stanza", "Attenzione");
		});
	}
	
	public void gestioneAggiungiSettimanale() {
		bottoneAggiungiSettimanale.addActionListener((e) -> {
				boolean errore = false;
				String nomeStanza = null;
				String nomeClasseElemento = null;
				String valueInizio = "";
				double tempDefault = 0;
				int ore = 0;
				int minuti = 0;
				int i = 0;
				boolean creatoSettimanale = false;
				int idSettimanale = 0;
				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				Date data = null;
				
				
				if(comboBoxSelezioneStanza.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanza.getSelectedItem().toString();
                    
                    if(comboBoxSelezioneElemento.getSelectedItem() != null) {
    					nomeClasseElemento = comboBoxSelezioneElemento.getSelectedItem().toString();
    					
    					for(i = 0; i < 7; i++) {
    						if(checkBoxSettimanale[i].isSelected()) {
    							valueInizio = (formato.format(spinnerSettimanale[i].getValue()));
    							
    							try {
    								data = formato.parse(valueInizio);
    								ore = data.getHours();
    	        					minuti = data.getMinutes();
        							if(ore < 22) {
		        						if(oggettoSelezionato.equals("SensoreTemperatura")) {
		            						try {
		            							tempDefault = Double.parseDouble(textTempSettimanale[i].getText());
		            							
		            							if(tempDefault <= 8 || tempDefault >= 28) {
		            								(new Alert()).errore("Il valore deve essere maggiore di 8 e minore di 28. Errore riga: "+(i+1), "Attenzione");
		            								errore = true;
		            							}
		            							
		            							if(tempDefault == 0.0)
		            								tempDefault = 17;
		            						}catch (NumberFormatException error) {
		            							(new Alert()).errore("Il valore deve essere un numero. Errore riga: "+(i+1), "Attenzione");
		            							errore = true;
		            						}
		            					}
        							}
        							else {
            							(new Alert()).errore("Il programma deve iniziare prima delle 22. Errore riga: "+ (i+1), "Attenzione");
                						errore = true;
            						}
        						}
    	        			 catch (ParseException e1) {
    	        					(new Alert()).errore("Errore nella scelta del tempo di inizio", "Attenzione");
    	        					errore = true;
    	        				}
    							if(!errore) {
    	        					if(!creatoSettimanale) {
    	        						creatoSettimanale = true;
    	        						idSettimanale = controllerProgramma.creaProgrammaSettimanale();
    	        					}
    	        					controllerProgramma.aggiornaProgrammaSettimanale(idSettimanale, (i+1), nomeStanza, nomeClasseElemento, ore, minuti, tempDefault);
    	        				}
    						}
    					}
        						
        				
                    }
    				else
    					(new Alert()).errore("Devi selezionare un oggetto", "Attenzione");
				}
				else
					(new Alert()).errore("Devi selezionare una stanza", "Attenzione");
				
				if(!errore) {
				(new Alert()).info("Il ProgrammaSettimanale è stato aggiunto", "Operazione effettuata con successo");
				caricaProgrammiView();
				}
				
		});
	}
	
	public void viewPanelGiornaliero(String elemento) {
		panelSelezioneGiornaliero.setVisible(true);
    	panelSelezioneSettimanale.setVisible(false);
    	
    	if(elemento.equals("SensoreTemperatura")) {
    		labelTempGiornaliero.setVisible(true);
    		textTempGiornaliero.setVisible(true);
    	}
    	else {
    		labelTempGiornaliero.setVisible(false);
    		textTempGiornaliero.setVisible(false);
    	}
	}
	
	public void caricaProgrammiView() {
		panelSelezioneSettimanale.setVisible(false);
		panelSelezioneGiornaliero.setVisible(false);
		panelPrincipale.removeAll();
		panelPrincipale.add(controllerCasa.getMain().getPanelProgrammi());
		panelPrincipale.repaint();
		panelPrincipale.revalidate();
		
		controllerCasa.getMain().getPanelProgrammi().viewTabellaProgrammiGiornalieri();
		controllerCasa.getMain().getPanelProgrammi().viewTabellaProgrammiSettimanali();
	}
}
