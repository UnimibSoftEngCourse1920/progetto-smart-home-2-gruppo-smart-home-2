package application.frontend.views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;









import javax.swing.LayoutStyle.ComponentPlacement;
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

import application.backend.dominio.Finestra;
import application.backend.dominio.Lampada;
import application.backend.dominio.Stanza;
import application.backend.dominio.Tapparella;
import application.backend.programmi.Programma;
import application.backend.programmi.ProgrammaGiornaliero;
import application.backend.programmi.ProgrammaSettimanale;
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
	private JLayeredPane panelPrincipale;
	private ControllerProgramma controllerProgramma;
	private JLabel labelProgrammi;
	private DefaultTableModel modelTabellaProgrammiGiornalieri;
	private JButton bottoneAggiungiProgramma;
	private JLabel labelAggiungiProgramma;
	private JPanel panelAggiungiProgramma;
	private JPanel panelTabellaProgrammi;
	private ProgrammaSettimanaleView panelModificaSettimanale;
	private JPanel panelTabellaProgrammiGiornalieri;
	private JScrollPane scrollPaneTabellaProgrammiGiornalieri;
	private JTable tabellaProgrammiGiornalieri;
	private JLabel labelProgrammiGiornalieri;
	private AggiungiProgrammaView panelProgramma;
	private Object[] rowData;
	private JPanel panelTabellaProgrammiSettimanali;
	private JScrollPane scrollPaneTabellaProgrammiSettimanali;
	private JLabel labelProgrammiSettimanali;
	
	public ProgrammiView(JLayeredPane panelPrincipale, Simulazione s, ControllerProgramma controllerProgramma) {
		this.panelPrincipale = panelPrincipale;
		this.controllerProgramma = controllerProgramma;
		this.s = s;
		
		inizializzazione();
	}
	public void inizializzazione() {
		labelProgrammi = new JLabel("PROGRAMMI");
		labelProgrammi.setHorizontalAlignment(SwingConstants.CENTER);
		labelProgrammi.setFont(new Font("Arial", Font.PLAIN, 25));
		labelProgrammiGiornalieri = new JLabel("Tabella Programmi Giornalieri");
		labelProgrammiGiornalieri.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelProgrammiGiornalieri.setHorizontalAlignment(SwingConstants.CENTER);
		
		bottoneAggiungiProgramma = new JButton("Aggiungi");
		
		panelProgramma = new AggiungiProgrammaView(panelPrincipale, this.controllerProgramma.getCasa(), this.controllerProgramma);
		
		labelAggiungiProgramma = new JLabel("Nuovo programma:");
		panelAggiungiProgramma = new JPanel();
		
		panelTabellaProgrammi = new JPanel();
		panelTabellaProgrammiGiornalieri = new JPanel();
		
		tabellaProgrammiGiornalieri = new JTable();
		tabellaProgrammiGiornalieri.setFont(new Font("Arial", Font.PLAIN, 11));
		
		tabellaProgrammiGiornalieri.setEnabled(false);
		
		tabellaProgrammiGiornalieri.setRowHeight(40);
		
		scrollPaneTabellaProgrammiGiornalieri = new JScrollPane(tabellaProgrammiGiornalieri);
		scrollPaneTabellaProgrammiGiornalieri.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		modelTabellaProgrammiGiornalieri = (DefaultTableModel) tabellaProgrammiGiornalieri.getModel();
		
		
		
		
		String[] colonneGiornalieri = { "Tipo", "ID", "Elemento", "Inizio", "Fine", "Elimina"};
		modelTabellaProgrammiGiornalieri.setColumnIdentifiers(colonneGiornalieri);
		
		viewTabellaProgrammiGiornalieri();
		
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
				.addComponent(labelProgrammi, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
				.addComponent(panelAggiungiProgramma, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
				.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(labelProgrammi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelAggiungiProgramma, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
		);
		
		panelTabellaProgrammiSettimanali = new JPanel();
		scrollPaneTabellaProgrammiSettimanali = new JScrollPane((Component) null);
		scrollPaneTabellaProgrammiSettimanali.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		labelProgrammiSettimanali = new JLabel("Tabella Programmi Settimanali");
		labelProgrammiSettimanali.setHorizontalAlignment(SwingConstants.CENTER);
		labelProgrammiSettimanali.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panelTabellaProgrammiSettimanali);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(scrollPaneTabellaProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
					.addGap(38))
				.addComponent(labelProgrammiSettimanali, GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
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
					.addComponent(panelTabellaProgrammiGiornalieri, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabellaProgrammiSettimanali, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		GroupLayout glpanelGiornalieri = new GroupLayout(panelTabellaProgrammiGiornalieri);
		glpanelGiornalieri.setHorizontalGroup(
				glpanelGiornalieri.createParallelGroup(Alignment.LEADING)
				.addComponent(labelProgrammiGiornalieri, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addGroup(glpanelGiornalieri.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollPaneTabellaProgrammiGiornalieri, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
					.addGap(37))
		);
		glpanelGiornalieri.setVerticalGroup(
				glpanelGiornalieri.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelGiornalieri.createSequentialGroup()
					.addComponent(labelProgrammiGiornalieri, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPaneTabellaProgrammiGiornalieri, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panelTabellaProgrammiGiornalieri.setLayout(glpanelGiornalieri);
		panelTabellaProgrammi.setLayout(gl_panelTabellaProgrammi);
		setLayout(groupLayout);
	}
	
	public void gestioneProgrammi() {
		bottoneAggiungiProgramma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelProgramma);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
			}
		});
		/*
		tabellaProgrammi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			        Point point = e.getPoint();
			        int column = tabellaProgrammi.columnAtPoint(point);
			        int row = tabellaProgrammi.rowAtPoint(point);
			        int idProgramma = (int) tabellaProgrammi.getModel().getValueAt(row, 1);
			         
			        //(new Alert()).errore("ciao", "Column header #" + column + " is clicked, riga "+ row);
			        String tipoProgramma = (String) tabellaProgrammi.getModel().getValueAt(row, 0);
		        	int id = (int) tabellaProgrammi.getModel().getValueAt(row, 0);
		        	Programma p = controllerProgramma.getProgramma(idProgramma);
		        	
			        if(column == 3 && !((String) tabellaProgrammi.getModel().getValueAt(row, 3)).equals("")) {
			        	if(p instanceof ProgrammaSettimanale) {
			        		if(tipoProgramma.equals(p.getClass().getSimpleName())) {
								panelModificaSettimanale = new ProgrammaSettimanaleView(panelPrincipale, controllerProgramma, ((ProgrammaSettimanale) p));
								panelPrincipale.removeAll();
								panelPrincipale.add(panelModificaSettimanale);
								panelPrincipale.repaint();
								panelPrincipale.revalidate();
							}
							else
								(new Alert()).errore("Errore", "Errore");
						}
			        }
					
			   
			}
		});*/
	}
	
	public void viewTabellaProgrammiGiornalieri() {
		tabellaProgrammiGiornalieri.setVisible(true);
		List<Programma> listaProgrammi = controllerProgramma.getAllProgrammi();
		
		int numeroProgrammi = listaProgrammi.size();
		
		if(numeroProgrammi != 0) {
			rowData = new Object[3];
			
			for(Programma p : listaProgrammi) {
				if(p instanceof ProgrammaGiornaliero) {
					rowData[0] = p.getClass().getSimpleName();
					rowData[1] = p.getId();
					
					/*if(p instanceof Lavatrice) {
						
					}
					else if(p instanceof Lavastoviglie){
						
					}
					else{
					
					}*/
					
					rowData[3] = "Elimina";
					
					modelTabellaProgrammiGiornalieri.addRow(rowData);
				}
			}
			
		}
		else {
			//(new Alert()).info("La stanza non contiene oggetti", "Information");
		}
	}
}
