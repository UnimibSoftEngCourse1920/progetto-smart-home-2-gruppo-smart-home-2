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
	
	//PalelSelezioneGiornaliero
	private JLabel labelInizioGiornaliero;
	private JSpinner spinnerGiornaliero;
	private SpinnerDateModel spinnerModelGiornaliero;
	
	
	
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
		
		//panelSelezioneGiornaliero------------------------
		panelSelezioneGiornaliero = new JPanel();
		panelSelezioneGiornaliero.setVisible(false);
		labelInizioGiornaliero = new JLabel("Ora inizio:");
		spinnerModelGiornaliero = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		spinnerGiornaliero = new JSpinner(spinnerModelGiornaliero);
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerGiornaliero, "HH:mm");
		spinnerGiornaliero.setEditor(dateEditor);
		
		
		
		
		
		comboBoxStanze();
		comboBoxTipo();
		
		setLayoutProgramma();
		
		gestioneProgramma();
	}
	
	public void setLayoutProgramma() {
		GroupLayout gl_panelSelezioneGiorni = new GroupLayout(panelSelezioneGiorni);
		gl_panelSelezioneGiorni.setHorizontalGroup(
			gl_panelSelezioneGiorni.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSelezioneSettimanale, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
				.addComponent(panelSelezioneGiornaliero, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
		);
		gl_panelSelezioneGiorni.setVerticalGroup(
			gl_panelSelezioneGiorni.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneGiorni.createSequentialGroup()
					.addComponent(panelSelezioneGiornaliero, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSelezioneSettimanale, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
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
					.addGap(748))
		);
		gl_panelSelezioneSettimanale.setVerticalGroup(
			gl_panelSelezioneSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneSettimanale.createSequentialGroup()
					.addGap(18)
					.addComponent(checkBoxLunedi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(checkBoxMartedi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(checkBoxMercoledi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(checkBoxGiovedi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(checkBoxVenerdi, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(checkBoxSabato, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(checkBoxDomenica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(17))
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
					.addComponent(spinnerGiornaliero, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
					.addGap(481))
		);
		gl_panelSelezioneGiornaliero.setVerticalGroup(
			gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelezioneGiornaliero.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panelSelezioneGiornaliero.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelInizioGiornaliero)
						.addComponent(spinnerGiornaliero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
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
}
