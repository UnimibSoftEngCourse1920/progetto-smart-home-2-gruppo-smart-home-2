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
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.SwingConstants;

import application.backend.dominio.Finestra;
import application.backend.dominio.Lampada;
import application.backend.dominio.Lavastoviglie;
import application.backend.dominio.Lavatrice;
import application.backend.dominio.Stanza;
import application.backend.dominio.Tapparella;
import application.backend.programmi.Programma;
import application.backend.programmi.ProgrammaGiornaliero;
import application.backend.programmi.ProgrammaSettimanale;
import application.backend.sensori.SensoreTemperatura;
import application.controllers.ControllerProgramma;
import application.controllers.Simulazione;
import application.frontend.support.Alert;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import java.awt.Color;

public class ProgrammiView extends JPanel {
	private Simulazione s;
	private ControllerProgramma controllerProgramma;
	private ProgrammaSettimanaleView panelModificaSettimanale;
	private AggiuntaProgrammaView panelAggiungiProgramma;
	private JLayeredPane panelPrincipale;
	
	private JPanel panelAggiuntaProgramma;
	private JLabel labelProgrammi;
	private JLabel labelAggiungiProgramma;
	private JButton bottoneAggiungiProgramma;
	private JPanel panelTabellaProgrammi;
	
	
	//TABELLA PROGRAMMI GIORNALIERI-------------------------------
	private JPanel panelTabellaProgrammiGiornalieri;
	private JLabel labelProgrammiGiornalieri;
	private JScrollPane scrollPaneTabellaProgrammiGiornalieri;
	private JTable tabellaProgrammiGiornalieri;
	private DefaultTableModel modelTabellaProgrammiGiornalieri;
	private Object[] rowDataGiornalieri;
	
	//TABELLA PROGRAMMI SETTIMANALI-------------------------------
	private JPanel panelTabellaProgrammiSettimanali;
	private JLabel labelProgrammiSettimanali;
	private JScrollPane scrollPaneTabellaProgrammiSettimanali;
	private JTable tabellaProgrammiSettimanali;
	private DefaultTableModel modelTabellaProgrammiSettimanali;
	private Object[] rowDataSettimanali;
	
	
	
	public ProgrammiView(JLayeredPane panelPrincipale, Simulazione s, ControllerProgramma controllerProgramma) {
		this.panelPrincipale = panelPrincipale;
		this.controllerProgramma = controllerProgramma;
		this.s = s;
		
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
		
		panelAggiungiProgramma = new AggiuntaProgrammaView(panelPrincipale, this.controllerProgramma.getCasa(), this.controllerProgramma);
		
		panelAggiuntaProgramma = new JPanel();
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
		String[] colonneSettimanali = { "Tipo", "ID", "Elemento", "Visualizza", "Elimina"};
		modelTabellaProgrammiSettimanali.setColumnIdentifiers(colonneSettimanali);
		((DefaultTableCellRenderer)tabellaProgrammiSettimanali.getDefaultRenderer(Object.class)).setHorizontalAlignment(JLabel.CENTER);
		
		
		//PROGRAMMI GIORNALIERI-------------------------------------------
		tabellaProgrammiGiornalieri = new JTable();
		tabellaProgrammiGiornalieri.setFont(new Font("Arial", Font.PLAIN, 11));
		tabellaProgrammiGiornalieri.setEnabled(false);
		tabellaProgrammiGiornalieri.setRowHeight(40);
		scrollPaneTabellaProgrammiGiornalieri = new JScrollPane(tabellaProgrammiGiornalieri);
		scrollPaneTabellaProgrammiGiornalieri.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		modelTabellaProgrammiGiornalieri = (DefaultTableModel) tabellaProgrammiGiornalieri.getModel();
		String[] colonneGiornalieri = { "Tipo", "ID", "Elemento", "Inizio", "Fine", "Valore Settato", "Elimina"};
		modelTabellaProgrammiGiornalieri.setColumnIdentifiers(colonneGiornalieri);
		((DefaultTableCellRenderer)tabellaProgrammiGiornalieri.getDefaultRenderer(Object.class)).setHorizontalAlignment(JLabel.CENTER);

		
		viewTabellaProgrammiGiornalieri();
		viewTabellaProgrammiSettimanali();
		
		setLayoutProgrammi();
		
		gestioneProgrammi();
	}
	
	public void setLayoutProgrammi() {		
		GroupLayout glpanel = new GroupLayout(panelAggiuntaProgramma);
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
		panelAggiuntaProgramma.setLayout(glpanel);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(labelProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelAggiuntaProgramma, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(labelProgrammi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelAggiuntaProgramma, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
		);
		
		
		GroupLayout gl_panel = new GroupLayout(panelTabellaProgrammiSettimanali);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(labelProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(45)
					.addComponent(scrollPaneTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
					.addGap(56))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addComponent(labelProgrammiSettimanali, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(scrollPaneTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTabellaProgrammiSettimanali.setLayout(gl_panel);
		
		
		GroupLayout gl_panelTabellaProgrammi = new GroupLayout(panelTabellaProgrammi);
		gl_panelTabellaProgrammi.setHorizontalGroup(
			gl_panelTabellaProgrammi.createParallelGroup(Alignment.LEADING)
				.addComponent(panelTabellaProgrammiGiornalieri, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
		);
		gl_panelTabellaProgrammi.setVerticalGroup(
			gl_panelTabellaProgrammi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTabellaProgrammi.createSequentialGroup()
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
		panelTabellaProgrammi.setLayout(gl_panelTabellaProgrammi);
		setLayout(groupLayout);
	}
	
	public void gestioneProgrammi() {
		bottoneAggiungiProgramma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelAggiungiProgramma);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
			}
		});
		tabellaProgrammiGiornalieri.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				int column = tabellaProgrammiGiornalieri.columnAtPoint(point);
				int row = tabellaProgrammiGiornalieri.rowAtPoint(point);
				int idProgramma = (int) tabellaProgrammiGiornalieri.getModel().getValueAt(row, 1);
				 
				//(new Alert()).errore("ciao", "Column header #" + column + " is clicked, riga "+ row);
				String tipoProgramma = (String) tabellaProgrammiGiornalieri.getModel().getValueAt(row, 0);
				Programma p = controllerProgramma.getProgramma(idProgramma);
				
				//GESTIONE CANCELLAZIONE
				if(column == 6) {
					if(p instanceof ProgrammaGiornaliero) {
						if(tipoProgramma.equals(p.getClass().getSimpleName())) {
							controllerProgramma.eliminaProgrammaGiornaliero(p);
							(new Alert()).info("Il ProgrammaGiornaliero è stato eliminato", "Operazione effettuata con successo");
							
							//panelModificaSettimanale = new ProgrammaSettimanaleView(panelPrincipale, controllerProgramma, ((ProgrammaSettimanale) p));
							rimuoviRigheTabellaProgrammiGiornalieri();
							viewTabellaProgrammiGiornalieri();						
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
		
		int numeroProgrammi = listaProgrammi.size();
		
		if(numeroProgrammi != 0) {
			rowDataGiornalieri = new Object[7];
			
			for(Programma p : listaProgrammi) {
				if(p instanceof ProgrammaGiornaliero) {
					Object e = p.getElemento();
					rowDataGiornalieri[0] = p.getClass().getSimpleName();
					rowDataGiornalieri[1] = p.getId();
					
					rowDataGiornalieri[2] = e.getClass().getSimpleName();
					
					rowDataGiornalieri[3] = ((ProgrammaGiornaliero) p).getInizio();
					rowDataGiornalieri[4] = ((ProgrammaGiornaliero) p).getFine();
					
					if(((ProgrammaGiornaliero) p).getValoreDiSetting() == 0.0)
						rowDataGiornalieri[5] = "";
					else
						rowDataGiornalieri[5] = ((ProgrammaGiornaliero) p).getValoreDiSetting();
					
					rowDataGiornalieri[6] = "Elimina";
					
					modelTabellaProgrammiGiornalieri.addRow(rowDataGiornalieri);
				}
			}
			
		}
		else {
			//(new Alert()).info("La stanza non contiene oggetti", "Information");
		}
	}
	
	public void viewTabellaProgrammiSettimanali() {
		tabellaProgrammiGiornalieri.setVisible(true);
		List<Programma> listaProgrammi = controllerProgramma.getAllProgrammi();
		
		int numeroProgrammi = listaProgrammi.size();
		
		if(numeroProgrammi != 0) {
			rowDataSettimanali = new Object[5];
			
			for(Programma p : listaProgrammi) {
				if(p instanceof ProgrammaSettimanale) {
					rowDataSettimanali[0] = p.getClass().getSimpleName();
					rowDataSettimanali[1] = p.getId();
					
					
					rowDataSettimanali[3] = "Visualizza";
					rowDataSettimanali[4] = "Elimina";
					
					modelTabellaProgrammiGiornalieri.addRow(rowDataGiornalieri);
				}
			}
			
		}
		else {
			//(new Alert()).info("La stanza non contiene oggetti", "Information");
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
}
