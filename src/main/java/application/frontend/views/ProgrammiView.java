package application.frontend.views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingConstants;

import application.backend.dominio.Stanza;
import application.backend.programmi.Programma;
import application.backend.programmi.ProgrammaGiornaliero;
import application.backend.programmi.ProgrammaSettimanale;
import application.controllers.ControllerProgramma;
import application.frontend.support.Alert;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ScrollPaneConstants;

public class ProgrammiView extends JPanel {
	private transient ControllerProgramma controllerProgramma;
	private ProgrammaSettimanaleView panelModificaSettimanale;
	private AggiungiProgrammaView panelAggiuntaProgramma;
	private JLayeredPane panelPrincipale;
	
	private JLabel labelProgrammi;
	private JLabel labelAggiungiProgramma;
	private JButton bottoneAggiungiProgramma;
	private JPanel panelTabellaProgrammi;
	
	private JPanel panelAggiungiProgramma;
	
	
	//TABELLA PROGRAMMI GIORNALIERI-------------------------------
	private JPanel panelTabellaProgrammiGiornalieri;
	private JLabel labelProgrammiGiornalieri;
	private JScrollPane scrollPaneTabellaProgrammiGiornalieri;
	private JTable tabellaProgrammiGiornalieri;
	private DefaultTableModel modelTabellaProgrammiGiornalieri;
	
	
	//TABELLA PROGRAMMI SETTIMANALI-------------------------------
	private JPanel panelTabellaProgrammiSettimanali;
	private JLabel labelProgrammiSettimanali;
	private JScrollPane scrollPaneTabellaProgrammiSettimanali;
	private JTable tabellaProgrammiSettimanali;
	private DefaultTableModel modelTabellaProgrammiSettimanali;
	
	
	
	
	public ProgrammiView(JLayeredPane panelPrincipale, ControllerProgramma controllerProgramma) {
		this.panelPrincipale = panelPrincipale;
		this.controllerProgramma = controllerProgramma;
		
		inizializzazione();
	}
	
	public void inizializzazione() {
		labelAggiungiProgramma = new JLabel("Nuovo programma:");
		labelProgrammi = new JLabel("PROGRAMMI");
		labelProgrammi.setHorizontalAlignment(SwingConstants.CENTER);
		labelProgrammi.setFont(new Font("Arial", Font.PLAIN, 25));
		labelProgrammiGiornalieri = new JLabel("Tabella Programmi Giornalieri");
		labelProgrammiGiornalieri.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelProgrammiGiornalieri.setHorizontalAlignment(SwingConstants.CENTER);
		labelProgrammiSettimanali = new JLabel("Tabella Programmi Settimanali");
		labelProgrammiSettimanali.setHorizontalAlignment(SwingConstants.CENTER);
		labelProgrammiSettimanali.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bottoneAggiungiProgramma = new JButton("Aggiungi");
		
		panelAggiuntaProgramma = new AggiungiProgrammaView(panelPrincipale, this.controllerProgramma.getCasa(), this.controllerProgramma);
		panelAggiungiProgramma = new JPanel();
		panelTabellaProgrammi = new JPanel();
		panelTabellaProgrammiGiornalieri = new JPanel();
		
		
		//PROGRAMMI SETTIMANALI------------------------------------------
		tabellaProgrammiSettimanali = new JTable();
		tabellaProgrammiSettimanali.setFont(new Font("Arial", Font.PLAIN, 11));
		tabellaProgrammiSettimanali.setEnabled(false);
		tabellaProgrammiSettimanali.setRowHeight(40);
		panelTabellaProgrammiSettimanali = new JPanel();
		scrollPaneTabellaProgrammiSettimanali = new JScrollPane(tabellaProgrammiSettimanali);
		scrollPaneTabellaProgrammiSettimanali.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		modelTabellaProgrammiSettimanali = (DefaultTableModel) tabellaProgrammiSettimanali.getModel();
		String[] colonneSettimanali = { "Tipo", "ID", "Elemento", "Stanza", "Visualizza", "Elimina"};
		modelTabellaProgrammiSettimanali.setColumnIdentifiers(colonneSettimanali);
		((DefaultTableCellRenderer)tabellaProgrammiSettimanali.getDefaultRenderer(Object.class)).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		
		//PROGRAMMI GIORNALIERI-------------------------------------------
		tabellaProgrammiGiornalieri = new JTable();
		tabellaProgrammiGiornalieri.setFont(new Font("Arial", Font.PLAIN, 11));
		tabellaProgrammiGiornalieri.setEnabled(false);
		tabellaProgrammiGiornalieri.setRowHeight(40);
		scrollPaneTabellaProgrammiGiornalieri = new JScrollPane(tabellaProgrammiGiornalieri);
		scrollPaneTabellaProgrammiGiornalieri.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		modelTabellaProgrammiGiornalieri = (DefaultTableModel) tabellaProgrammiGiornalieri.getModel();
		String[] colonneGiornalieri = { "Tipo", "Stanza", "ID", "Elemento", "Inizio", "Fine", "Valore Settato", "Elimina"};
		modelTabellaProgrammiGiornalieri.setColumnIdentifiers(colonneGiornalieri);
		((DefaultTableCellRenderer)tabellaProgrammiGiornalieri.getDefaultRenderer(Object.class)).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		
		viewTabellaProgrammiGiornalieri();
		viewTabellaProgrammiSettimanali();
		
		setLayoutProgrammi();
		
		gestioneProgrammi();
	}
	
	public void setLayoutProgrammi() {		
		GroupLayout glpanel = new GroupLayout(panelAggiungiProgramma);
		glpanel.setHorizontalGroup(
			glpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanel.createSequentialGroup()
					.addGap(372)
					.addComponent(labelAggiungiProgramma, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneAggiungiProgramma)
					.addContainerGap(308, Short.MAX_VALUE))
		);
		glpanel.setVerticalGroup(
			glpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glpanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(glpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(bottoneAggiungiProgramma, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelAggiungiProgramma, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		panelAggiungiProgramma.setLayout(glpanel);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelAggiungiProgramma, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(labelProgrammi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelAggiungiProgramma, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
		);
		
		
		GroupLayout glpanel2 = new GroupLayout(panelTabellaProgrammiSettimanali);
		glpanel2.setHorizontalGroup(
				glpanel2.createParallelGroup(Alignment.LEADING)
				.addComponent(labelProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addGroup(glpanel2.createSequentialGroup()
					.addGap(45)
					.addComponent(scrollPaneTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
					.addGap(56))
		);
		glpanel2.setVerticalGroup(
				glpanel2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, glpanel2.createSequentialGroup()
					.addComponent(labelProgrammiSettimanali, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(scrollPaneTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTabellaProgrammiSettimanali.setLayout(glpanel2);
		
		
		GroupLayout glpanelTabellaProgrammi = new GroupLayout(panelTabellaProgrammi);
		glpanelTabellaProgrammi.setHorizontalGroup(
				glpanelTabellaProgrammi.createParallelGroup(Alignment.LEADING)
				.addComponent(panelTabellaProgrammiGiornalieri, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
		);
		glpanelTabellaProgrammi.setVerticalGroup(
				glpanelTabellaProgrammi.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelTabellaProgrammi.createSequentialGroup()
					.addComponent(panelTabellaProgrammiGiornalieri, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		GroupLayout glpanelGiornalieri = new GroupLayout(panelTabellaProgrammiGiornalieri);
		glpanelGiornalieri.setHorizontalGroup(
			glpanelGiornalieri.createParallelGroup(Alignment.LEADING)
				.addComponent(labelProgrammiGiornalieri, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, glpanelGiornalieri.createSequentialGroup()
					.addGap(44)
					.addComponent(scrollPaneTabellaProgrammiGiornalieri, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
					.addGap(55))
		);
		glpanelGiornalieri.setVerticalGroup(
			glpanelGiornalieri.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelGiornalieri.createSequentialGroup()
					.addComponent(labelProgrammiGiornalieri, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPaneTabellaProgrammiGiornalieri, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTabellaProgrammiGiornalieri.setLayout(glpanelGiornalieri);
		panelTabellaProgrammi.setLayout(glpanelTabellaProgrammi);
		setLayout(groupLayout);
	}
	
	public void gestioneProgrammi() {
		bottoneAggiungiProgramma.addActionListener((e) -> {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelAggiuntaProgramma);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
		});
		tabellaProgrammiGiornalieri.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				int column = tabellaProgrammiGiornalieri.columnAtPoint(point);
				int row = tabellaProgrammiGiornalieri.rowAtPoint(point);
				int idProgramma = (int) tabellaProgrammiGiornalieri.getModel().getValueAt(row, 1);
				String tipoProgramma = (String) tabellaProgrammiGiornalieri.getModel().getValueAt(row, 0);
				Programma p = controllerProgramma.getProgramma(idProgramma);
				
				//GESTIONE CANCELLAZIONE
				if(column == 7) {
					if(p instanceof ProgrammaGiornaliero) {
						if(tipoProgramma.equals(p.getClass().getSimpleName())) {
							controllerProgramma.eliminaProgrammaGiornaliero(p);
							(new Alert()).info("Il ProgrammaGiornaliero è stato eliminato", "Operazione effettuata con successo");
							rimuoviRigheTabellaProgrammiGiornalieri();
							viewTabellaProgrammiGiornalieri();						
						}	
					}	
					else
						(new Alert()).errore("Errore", "Errore");
				}
			}
		});
		tabellaProgrammiSettimanali.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				int column = tabellaProgrammiSettimanali.columnAtPoint(point);
				int row = tabellaProgrammiSettimanali.rowAtPoint(point);
				int idProgramma = (int) tabellaProgrammiSettimanali.getModel().getValueAt(row, 1);
				String tipoProgramma = (String) tabellaProgrammiSettimanali.getModel().getValueAt(row, 0);
				Programma p = controllerProgramma.getProgramma(idProgramma);
				
				//GESTIONE CANCELLAZIONE
				if(column == 5) {
					if(p instanceof ProgrammaSettimanale) {
						if(tipoProgramma.equals(p.getClass().getSimpleName())) {
							controllerProgramma.eliminaProgrammaGiornaliero(p);
							(new Alert()).info("Il ProgrammaSettimanale è stato eliminato", "Operazione effettuata con successo");
							rimuoviRigheTabellaProgrammiSettimanali();
							viewTabellaProgrammiSettimanali();						
						}	
					}	
					else
						(new Alert()).errore("Errore", "Errore");
				}
				
				//GESTIONE MODIFICA
				if(column == 4) {
					if(p instanceof ProgrammaSettimanale) {
						if(tipoProgramma.equals(p.getClass().getSimpleName())) {
							panelModificaSettimanale = new ProgrammaSettimanaleView(panelPrincipale, controllerProgramma, (ProgrammaSettimanale)p);
							panelPrincipale.removeAll();
							panelPrincipale.add(panelModificaSettimanale);
							panelPrincipale.repaint();
							panelPrincipale.revalidate();	
						}	
					}	
					else
						(new Alert()).errore("Errore", "Errore");
				}
			}
		});
	}
	
	public void viewTabellaProgrammiGiornalieri() {
		rimuoviRigheTabellaProgrammiGiornalieri();
		tabellaProgrammiGiornalieri.setVisible(true);
		List<Programma> listaProgrammi = controllerProgramma.getAllProgrammi();
		Object[] rowDataGiornalieri;
		int numeroProgrammi = listaProgrammi.size();
		
		if(numeroProgrammi != 0) {
			rowDataGiornalieri = new Object[8];
			
			for(Programma p : listaProgrammi) {
				if(p instanceof ProgrammaGiornaliero) {
					Object e = controllerProgramma.getElementoProgramma(p);
					rowDataGiornalieri[0] = p.getClass().getSimpleName();
					rowDataGiornalieri[1] = controllerProgramma.getIdProgramma(p);
					rowDataGiornalieri[3] = e.getClass().getSimpleName();
					
					Stanza stanza = controllerProgramma.getCasa().getStanzaElemento(e);
					
					if(stanza != null) {
						rowDataGiornalieri[2] = controllerProgramma.getCasa().getNomeStanza(stanza);
					}
					
					rowDataGiornalieri[4] = controllerProgramma.getInizioProgrammaGiornaliero((ProgrammaGiornaliero)p);
					rowDataGiornalieri[5] = controllerProgramma.getFineProgrammaGiornaliero((ProgrammaGiornaliero)p);
					
					if(controllerProgramma.getSettingProgrammaGiornaliero((ProgrammaGiornaliero)p) == 0.0)
						rowDataGiornalieri[6] = "";
					else
						rowDataGiornalieri[6] = controllerProgramma.getSettingProgrammaGiornaliero((ProgrammaGiornaliero)p);
					
					rowDataGiornalieri[7] = "Elimina";
					
					modelTabellaProgrammiGiornalieri.addRow(rowDataGiornalieri);
				}
			}
			
		}
	}
	
	public void viewTabellaProgrammiSettimanali() {
		rimuoviRigheTabellaProgrammiSettimanali();
		tabellaProgrammiSettimanali.setVisible(true);
		List<Programma> listaProgrammi = controllerProgramma.getAllProgrammi();
		Object[] rowDataSettimanali;
		
		int numeroProgrammi = listaProgrammi.size();
		
		if(numeroProgrammi != 0) {
			rowDataSettimanali = new Object[6];
			
			
			for(Programma p : listaProgrammi) {
				if(p instanceof ProgrammaSettimanale) {
					
					rowDataSettimanali[0] = p.getClass().getSimpleName();
					rowDataSettimanali[1] = controllerProgramma.getIdProgramma(p);
					rowDataSettimanali[2] = controllerProgramma.getElementoProgramma(p).getClass().getSimpleName();
					rowDataSettimanali[3] = controllerProgramma.getCasa().getStanzaElemento(controllerProgramma.getElementoProgramma(p)).getNome();
					rowDataSettimanali[4] = "Visualizza";
					rowDataSettimanali[5] = "Elimina";
					
					modelTabellaProgrammiSettimanali.addRow(rowDataSettimanali);
				}
			}
			
		}
	}
	
	public void rimuoviRigheTabellaProgrammiGiornalieri() {
		int numeroRighe = tabellaProgrammiGiornalieri.getRowCount();
		for (int i = numeroRighe - 1; i >= 0; i--) {
		    modelTabellaProgrammiGiornalieri.removeRow(i);
		}
	}
	
	public void rimuoviRigheTabellaProgrammiSettimanali() {
		int numeroRighe = tabellaProgrammiSettimanali.getRowCount();
		for (int i = numeroRighe - 1; i >= 0; i--) {
			modelTabellaProgrammiSettimanali.removeRow(i);
		}
	}
	
	public ProgrammaSettimanaleView getPanelSettimanale() {
		return panelModificaSettimanale;
	}
}
