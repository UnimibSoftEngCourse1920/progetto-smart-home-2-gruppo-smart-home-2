package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import application.backend.dominio.Lavatrice;
import application.backend.dominio.Stanza;
import application.controllers.ControllerCasa;
import application.controllers.ControllerProgramma;
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


public class AggiuntaProgrammaView extends JPanel {
	private Simulazione s;
	private JLayeredPane panelPrincipale;
	private JPanel panelSelezioneStanzaElementoTipo;
	private JPanel panelSelezioneGiorni;
	private JPanel panelSelezioneSettimanale;
	private JPanel panelSelezioneGiornaliero;
	private JLabel labelProgramma;
	private ControllerCasa controllerCasa;
	private ControllerProgramma controllerProgramma;
	
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
	
	
	
	
	//PanelSelezioneGiornaliero-----------------------
	private JLabel labelInizioGiornaliero;
	private JSpinner spinnerGiornaliero;
	private JButton bottoneAggiungiGiornaliero;
	private JLabel labelTempGiornaliero;
	private JTextField textTempGiornaliero;
	
	public AggiuntaProgrammaView(JLayeredPane principale, ControllerCasa casa, ControllerProgramma controllerProgramma) {
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
		
		checkBoxSettimanale[0] = new JCheckBox("Lunedi");
		checkBoxSettimanale[1] = new JCheckBox("Martedi");
		checkBoxSettimanale[2] = new JCheckBox("Mercoledi");
		checkBoxSettimanale[3] = new JCheckBox("Giovedi");
		checkBoxSettimanale[4] = new JCheckBox("Venerdi");
		checkBoxSettimanale[5] = new JCheckBox("Sabato");
		checkBoxSettimanale[6] = new JCheckBox("Domenica");
		
		for(int i = 0; i < 7; i++) {
			labelInizioSettimanale[i] = new JLabel("Ora inizio:");
			spinnerSettimanale[i] = new JSpinner(spinnerModel);
			JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerSettimanale[i], "HH:mm");
			spinnerSettimanale[i].setEditor(dateEditor);
			labelTempSettimanale[i] = new JLabel("Valore Temperatura:");
			textTempSettimanale[i] = new JTextField();
			textTempSettimanale[i].setColumns(10);
		}
		
		
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
						.addComponent(checkBoxSettimanale[0], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[1], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[2], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[3], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[4], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[5], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(checkBoxSettimanale[6], GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
					.addGap(90)
					.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(labelInizioSettimanale[2], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerSettimanale[2], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(labelTempSettimanale[2], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textTempSettimanale[2], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(bottoneAggiungiSettimanale, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
							.addGap(122))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[0], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(spinnerSettimanale[0], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[0], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[0], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[1], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[1], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[1], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[1], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[3], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[3], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[3], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[3], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[4], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[4], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[4], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[4], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[5], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[5], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[5], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[5], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
									.addComponent(labelInizioSettimanale[6], GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerSettimanale[6], GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTempSettimanale[6], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTempSettimanale[6], GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(242, Short.MAX_VALUE))))
		);
		gl_panelSelezioneSettimanale.setVerticalGroup(
			gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addGap(3)
							.addComponent(labelInizioSettimanale[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(80)
							.addComponent(bottoneAggiungiSettimanale)
							.addGap(166))
						.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
							.addComponent(spinnerSettimanale[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(labelTempSettimanale[0])
							.addComponent(textTempSettimanale[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
							.addComponent(checkBoxSettimanale[0], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[1], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioSettimanale[1])
								.addComponent(spinnerSettimanale[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[1])
								.addComponent(textTempSettimanale[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[2], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioSettimanale[2])
								.addComponent(spinnerSettimanale[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[2])
								.addComponent(textTempSettimanale[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[3], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(labelInizioSettimanale[3])
								.addComponent(spinnerSettimanale[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[3])
								.addComponent(textTempSettimanale[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[4], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(spinnerSettimanale[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioSettimanale[4])
								.addComponent(labelTempSettimanale[4])
								.addComponent(textTempSettimanale[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[5], GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(spinnerSettimanale[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioSettimanale[5])
								.addComponent(labelTempSettimanale[5])
								.addComponent(textTempSettimanale[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelSelezioneSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spinnerSettimanale[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInizioSettimanale[6])
								.addComponent(labelTempSettimanale[6])
								.addComponent(textTempSettimanale[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelSelezioneGiornaliero.createSequentialGroup()
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
		gl_panelSelezioneGiornaliero.setVerticalGroup(
			gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelSelezioneGiornaliero.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelInizioGiornaliero)
						.addComponent(spinnerGiornaliero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bottoneAggiungiGiornaliero)
						.addComponent(labelTempGiornaliero)
						.addComponent(textTempGiornaliero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
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
		if(controllerCasa.getRobot() != null && (controllerCasa.getRobot()).getBase().getNome().equals(nomeStanza))
			elementi.add(controllerCasa.getRobot());
		if(controllerCasa.getStanza(nomeStanza).getLavastoviglie() != null)
			elementi.add(controllerCasa.getStanza(nomeStanza).getLavastoviglie());
		if(controllerCasa.getStanza(nomeStanza).getLavatrice() != null)
			elementi.add(controllerCasa.getStanza(nomeStanza).getLavatrice());
		
		//System.out.println(elementi.size());
		
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
		//System.out.println("ciao");
		comboBoxSelezioneTipo.setEnabled(true);
		comboBoxSelezioneTipo.addItem("Giornaliero");
		comboBoxSelezioneTipo.addItem("Settimanale");
		
	}
	
	public void gestioneSelezioneSettimanale() {
		for(int i = 0; i < 7; i++) {
			if(!checkBoxSettimanale[i].isSelected()) {
				spinnerSettimanale[i].setEnabled(false);
			}
		}	
		
		//CHECKBOX LUNEDI----------------------------------------------------
		checkBoxSettimanale[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSettimanale[0].isSelected()) {
					spinnerSettimanale[0].setEnabled(true);
				}
				else
					spinnerSettimanale[0].setEnabled(false);
			}
		});
		//CHECKBOX MARTEDI----------------------------------------------------
		checkBoxSettimanale[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSettimanale[1].isSelected()) {
					spinnerSettimanale[1].setEnabled(true);
				}
				else
					spinnerSettimanale[1].setEnabled(false);
			}
		});
		//CHECKBOX MERCOLEDI----------------------------------------------------
		checkBoxSettimanale[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSettimanale[2].isSelected()) {
					spinnerSettimanale[2].setEnabled(true);
				}
				else
					spinnerSettimanale[2].setEnabled(false);
			}
		});
		//CHECKBOX GIOVEDI----------------------------------------------------
		checkBoxSettimanale[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSettimanale[3].isSelected()) {
					spinnerSettimanale[3].setEnabled(true);
				}
				else
					spinnerSettimanale[3].setEnabled(false);
			}
		});
		//CHECKBOX VENERDI----------------------------------------------------
		checkBoxSettimanale[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSettimanale[4].isSelected()) {
					spinnerSettimanale[4].setEnabled(true);
				}
				else
					spinnerSettimanale[4].setEnabled(false);
			}
		});
		//CHECKBOX SABATO----------------------------------------------------
		checkBoxSettimanale[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSettimanale[5].isSelected()) {
					spinnerSettimanale[5].setEnabled(true);
				}
				else
					spinnerSettimanale[5].setEnabled(false);
			}
		});
		//CHECKBOX DOMENICA----------------------------------------------------
		checkBoxSettimanale[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBoxSettimanale[6].isSelected()) {
					spinnerSettimanale[6].setEnabled(true);
				}
				else
					spinnerSettimanale[6].setEnabled(false);
			}
		});
	}
	
	public void gestioneAggiungiGiornaliero() {
		bottoneAggiungiGiornaliero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errore = false;
				String nomeStanza = null;
				String nomeClasseElemento = null;
				
				if(comboBoxSelezioneStanza.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanza.getSelectedItem().toString();
                    
                    if(comboBoxSelezioneElemento.getSelectedItem() != null) {
    					nomeClasseElemento = comboBoxSelezioneElemento.getSelectedItem().toString();
    					//System.out.print(nomeClasseElemento);
    					double tempDefault = 0;
    					int ore = 0;
    					int minuti = 0;
    					
    					SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        				String value = (formato.format(spinnerGiornaliero.getValue())).toString();
        				Date data = null;
        				try {
        					data = formato.parse(value);
        					ore = data.getHours();
        					minuti = data.getMinutes();
        					
        					int oreCorrente = controllerCasa.getMain().getSimulazione().getOre();
        					int minutiCorrenti = controllerCasa.getMain().getSimulazione().getMinuti();
        					
        					if(ore >= oreCorrente) {
        						if((ore == oreCorrente && minuti > minutiCorrenti) || ore > oreCorrente) {
	        						if(nomeClasseElemento.equals("SensoreTemperatura")) {
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
        							(new Alert()).errore("L'orario deve essere maggiore di quello dell'orologio", "Attenzione");
            						errore = true;
        						}
        					}
        					else {
        						(new Alert()).errore("L'orario deve essere maggiore di quello dell'orologio", "Attenzione");
        						errore = true;
        					}
        					
        					
        					
        					//controllerProgramma.nuovoProgrammaGiornaliero(nomeStanza, nomeClasseElemento, ore, minuti);
        					
        				} catch (ParseException e1) {
        					// TODO Auto-generated catch block
        					(new Alert()).errore("Errore nella scelta del tempo di inizio", "Attenzione");
        					errore = true;
        				}
        				
        				if(!errore) {
        					controllerProgramma.nuovoProgrammaGiornaliero(nomeStanza, nomeClasseElemento, ore, minuti, tempDefault);
            				(new Alert()).info("Il ProgrammaGiornaliero è stato aggiunto", "Operazione effettuata con successo");
            				caricaProgrammiView();
            				//controllerCasa.getMain().viewHomepage();
        				}
        				
        				
                    }
    				else
    					(new Alert()).errore("Devi selezionare un oggetto", "Attenzione");
				}
				else
					(new Alert()).errore("Devi selezionare una stanza", "Attenzione");
				
				
			}
		});
	}
	
	public void gestioneAggiungiSettimanale() {
		bottoneAggiungiSettimanale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errore = false;
				String nomeStanza = null;
				String nomeClasseElemento = null;
				String value;
				double tempDefault = 0;
				int ore = 0;
				int minuti = 0;
				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				Date data = null;
				
				if(comboBoxSelezioneStanza.getSelectedItem() != null) {
                    nomeStanza = comboBoxSelezioneStanza.getSelectedItem().toString();
                    
                    if(comboBoxSelezioneElemento.getSelectedItem() != null) {
    					nomeClasseElemento = comboBoxSelezioneElemento.getSelectedItem().toString();
    					
    					for(int i = 0; i < 7; i++) {
    						if(checkBoxSettimanale[i].isSelected()) {
    							
    						}
    					}
                    /*
	                    //LUNEDI
	                    if(checkBoxLunedi.isSelected()) {
	                    	String valueLunedi = (formato.format(spinnerLunedi.getValue())).toString();
	                    	try {
								data = formato.parse(valueLunedi);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					ore = data.getHours();
        					minuti = data.getMinutes();
	                    }
	                    //MARTEDI
	                    if(checkBoxMartedi.isSelected()) {
	                    	String valueMartedi = (formato.format(spinnerMartedi.getValue())).toString();
	                    	try {
								data = formato.parse(valueMartedi);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					ore = data.getHours();
        					minuti = data.getMinutes();
	                    }
	                    //MERCOLEDI
	                    if(checkBoxMercoledi.isSelected()) {
	                    	String valueMercoledi = (formato.format(spinnerMercoledi.getValue())).toString();
	                    	try {
								data = formato.parse(valueMercoledi);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					ore = data.getHours();
        					minuti = data.getMinutes();
	                    }
	                    //GIOVEDI
	                    if(checkBoxGiovedi.isSelected()) {
	                    	String valueGiovedi = (formato.format(spinnerGiovedi.getValue())).toString();
	                    	try {
								data = formato.parse(valueGiovedi);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					ore = data.getHours();
        					minuti = data.getMinutes();
	                    }
	                    //VENERDI
	                    if(checkBoxVenerdi.isSelected()) {
	                    	String valueVenerdi = (formato.format(spinnerVenerdi.getValue())).toString();
	                    	try {
								data = formato.parse(valueVenerdi);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					ore = data.getHours();
        					minuti = data.getMinutes();
	                    }
	                    //SABATO
	                    if(checkBoxSabato.isSelected()) {
	                    	String valueSabato = (formato.format(spinnerSabato.getValue())).toString();
	                    	try {
								data = formato.parse(valueSabato);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					ore = data.getHours();
        					minuti = data.getMinutes();
	                    }
	                    //DOMENICA
	                    if(checkBoxDomenica.isSelected()) {
	                    	String valueDomenica = (formato.format(spinnerDomenica.getValue())).toString();
	                    	try {
								data = formato.parse(valueDomenica);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					ore = data.getHours();
        					minuti = data.getMinutes();
	                    }*/
                    
	                    /*
	                    if(nomeClasseElemento.equals("SensoreTemperatura")) {
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
    					}*/
    					
    					
        				
        					
        					
        					
        					
        					//controllerProgramma.nuovoProgrammaGiornaliero(nomeStanza, nomeClasseElemento, ore, minuti);
        				
        				
        				if(!errore) {
        					//controllerProgramma.nuovoProgrammaGiornaliero(nomeStanza, nomeClasseElemento, ore, minuti, tempDefault);
            				//(new Alert()).info("Il ProgrammaGiornaliero è stato aggiunto", "Operazione effettuata con successo");
            				//caricaProgrammiView();
            				//controllerCasa.getMain().viewHomepage();
        				}
        				
        				
                    }
    				else
    					(new Alert()).errore("Devi selezionare un oggetto", "Attenzione");
				}
				else
					(new Alert()).errore("Devi selezionare una stanza", "Attenzione");
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
