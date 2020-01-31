package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import application.backend.dominio.Stanza;
import application.backend.programmi.ProgrammaSettimanale;
import application.backend.sensori.SensoreTemperatura;
import application.controllers.ControllerCasa;
import application.controllers.ControllerProgramma;
import application.frontend.support.Alert;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ProgrammaSettimanaleView extends JPanel {
	private JLayeredPane panelPrincipale;
	private transient ControllerProgramma controllerProgramma;
	private transient ProgrammaSettimanale programmaSettimanale;
	private transient ControllerCasa controllerCasa;
	
	private JPanel panelModificaSettimanale;
	private JLabel labelModificaSettimanale;
	
	private JCheckBox[] checkBoxSettimanale;
	private JLabel[] labelInizioSettimanale;
	private JSpinner[] spinnerSettimanale;
	private JButton bottoneModificaSettimanale;
	private JLabel[] labelTempSettimanale;
	private JTextField[] textTempSettimanale;
	private transient Object elementoProgramma;
	
	public ProgrammaSettimanaleView(JLayeredPane principale, ControllerProgramma programma, ProgrammaSettimanale pSettimanale) {
		this.panelPrincipale = principale;
		this.controllerProgramma = programma;
		this.programmaSettimanale = pSettimanale;
		
		
		inizializzazione();
		
	}
	
	public void inizializzazione() {
		labelModificaSettimanale = new JLabel("MODIFICA PROGRAMMA SETTIMANALE");
		labelModificaSettimanale.setHorizontalAlignment(SwingConstants.CENTER);
		labelModificaSettimanale.setFont(new Font("Arial", Font.PLAIN, 25));
		panelModificaSettimanale = new JPanel();
		
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
		
		checkBoxSettimanale = new JCheckBox[7];
		labelInizioSettimanale = new JLabel[7];
		spinnerSettimanale = new JSpinner[7];
		labelTempSettimanale = new JLabel[7];
		textTempSettimanale = new JTextField[7];

		SpinnerDateModel[] modelSpinnerSettimanale;
		modelSpinnerSettimanale = new SpinnerDateModel[7];
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
		
		bottoneModificaSettimanale = new JButton("Modifica");
		
		elementoProgramma = controllerProgramma.getElementoProgramma(programmaSettimanale);
		
		controllerCasa = controllerProgramma.getCasa();
		
		setLayoutModificaSettimanale();
		
		gestioneSettimanale();
		
		visualizzaValoriSettimanale();
		
		gestioneAggiornamento();
	}
	
	public void setLayoutModificaSettimanale() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelModificaSettimanale, GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
				.addComponent(panelModificaSettimanale, GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelModificaSettimanale, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelModificaSettimanale, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		GroupLayout glpanelModificaSettimanale = new GroupLayout(panelModificaSettimanale);
		glpanelModificaSettimanale.setHorizontalGroup(
				glpanelModificaSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelModificaSettimanale.createSequentialGroup()
					.addGap(73)
					.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBoxSettimanale[0], GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxSettimanale[1], GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxSettimanale[2], GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxSettimanale[3], GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxSettimanale[4], GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxSettimanale[5], GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxSettimanale[6], GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.LEADING)
						.addComponent(labelInizioSettimanale[0], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addComponent(labelInizioSettimanale[1], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addComponent(labelInizioSettimanale[2], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addComponent(labelInizioSettimanale[3], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addComponent(labelInizioSettimanale[4], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addComponent(labelInizioSettimanale[5], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addComponent(labelInizioSettimanale[6], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
					.addGap(35)
					.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.LEADING)
						.addComponent(spinnerSettimanale[0] , GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerSettimanale[1] , GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerSettimanale[2] , GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerSettimanale[3] , GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerSettimanale[4] , GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerSettimanale[5] , GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinnerSettimanale[6] , GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addComponent(labelTempSettimanale[6], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textTempSettimanale[6], GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addComponent(labelTempSettimanale[5], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textTempSettimanale[5], GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addComponent(labelTempSettimanale[4], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textTempSettimanale[4], GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addComponent(labelTempSettimanale[3], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textTempSettimanale[3], GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addComponent(labelTempSettimanale[2], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textTempSettimanale[2], GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addComponent(labelTempSettimanale[1], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textTempSettimanale[1], GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addComponent(labelTempSettimanale[0], GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textTempSettimanale[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(76)
					.addComponent(bottoneModificaSettimanale)
					.addGap(96))
		);
		glpanelModificaSettimanale.setVerticalGroup(
				glpanelModificaSettimanale.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelModificaSettimanale.createSequentialGroup()
					.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.LEADING)
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addGap(53)
							.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[0])
								.addComponent(labelInizioSettimanale[0])
								.addComponent(spinnerSettimanale[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[0])
								.addComponent(textTempSettimanale[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[1])
								.addComponent(labelInizioSettimanale[1])
								.addComponent(spinnerSettimanale[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[1])
								.addComponent(textTempSettimanale[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[2])
								.addComponent(labelInizioSettimanale[2])
								.addComponent(spinnerSettimanale[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[2])
								.addComponent(textTempSettimanale[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[3])
								.addComponent(labelInizioSettimanale[3])
								.addComponent(spinnerSettimanale[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[3])
								.addComponent(textTempSettimanale[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[4])
								.addComponent(labelInizioSettimanale[4])
								.addComponent(spinnerSettimanale[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[4])
								.addComponent(textTempSettimanale[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[5])
								.addComponent(labelInizioSettimanale[5])
								.addComponent(spinnerSettimanale[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[5])
								.addComponent(textTempSettimanale[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(glpanelModificaSettimanale.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxSettimanale[6])
								.addComponent(labelInizioSettimanale[6])
								.addComponent(spinnerSettimanale[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTempSettimanale[6])
								.addComponent(textTempSettimanale[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(glpanelModificaSettimanale.createSequentialGroup()
							.addGap(160)
							.addComponent(bottoneModificaSettimanale)))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		panelModificaSettimanale.setLayout(glpanelModificaSettimanale);
	}
	
	public void gestioneSettimanale() {
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
					if(elementoProgramma instanceof SensoreTemperatura) {
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
					if(elementoProgramma instanceof SensoreTemperatura) {
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
					if(elementoProgramma instanceof SensoreTemperatura) {
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
					if(elementoProgramma instanceof SensoreTemperatura) {
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
					if(elementoProgramma instanceof SensoreTemperatura) {
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
					if(elementoProgramma instanceof SensoreTemperatura) {
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
					if(elementoProgramma instanceof SensoreTemperatura) {
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
	
	public void visualizzaValoriSettimanale() {
		controllerProgramma.getProgrammiGiornalieri(programmaSettimanale, this);
	}
	
	public void gestioneAggiornamento() {
		bottoneModificaSettimanale.addActionListener((e) -> {
				boolean errore = false;
				String valueInizio = "";
				double tempDefault = 0;
				int ore = 0;
				int minuti = 0;
				int i = 0;
				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				Date data = null;
				int oreCorrente = controllerCasa.getMain().getSimulazione().getOre();
				int minutiCorrenti = controllerCasa.getMain().getSimulazione().getMinuti();
				Object elemento = controllerProgramma.getElementoProgramma(programmaSettimanale);
				Stanza s = controllerCasa.getStanzaElemento(elemento);
				String classeElemento = elemento.getClass().getSimpleName();
				
				if(s != null) {
	    					
					for(i = 0; i < 7; i++) {
						if(checkBoxSettimanale[i].isSelected()) {
							valueInizio = (formato.format(spinnerSettimanale[i].getValue()));
							
							try {
								data = formato.parse(valueInizio);
								ore = data.getHours();
	        					minuti = data.getMinutes();
	        					
	        					if(ore >= oreCorrente) {
	        						if((ore == oreCorrente && minuti > minutiCorrenti) || ore > oreCorrente) {
		        						if(classeElemento.equals("SensoreTemperatura")) {
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
	        							(new Alert()).errore("L'orario deve essere maggiore di quello dell'orologio. Errore riga: "+ (i+1), "Attenzione");
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
	        					controllerProgramma.aggiornaProgrammaSettimanale(controllerProgramma.getIdProgramma(programmaSettimanale), (i+1), s.getNome(), classeElemento, ore, minuti, tempDefault);
	        					(new Alert()).info("Il ProgrammaSettimanale è stato modificato con successo", "Operazione effettuata con successo");
	            				caricaProgrammiView();
	        				}
						}
					}
				}
				
		});
	}
	
	public JSpinner[] getSpinnerSettimanale() {
		return spinnerSettimanale;
	}
	
	public JTextField[] getTextSettimanale() {
		return textTempSettimanale;
	}
	
	public void modificaSpinner(int i, Date inizio) {
		spinnerSettimanale[i].setValue(inizio);
	}
	
	public void modificaText(int i, String value) {
		textTempSettimanale[i].setText(value);
	}
	
	public void caricaProgrammiView() {
		panelPrincipale.removeAll();
		panelPrincipale.add(controllerCasa.getMain().getPanelProgrammi());
		panelPrincipale.repaint();
		panelPrincipale.revalidate();
		
		controllerCasa.getMain().getPanelProgrammi().viewTabellaProgrammiGiornalieri();
		controllerCasa.getMain().getPanelProgrammi().viewTabellaProgrammiSettimanali();
	}
}
