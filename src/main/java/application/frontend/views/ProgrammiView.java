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
import javax.swing.SwingConstants;

import application.controllers.ControllerProgramma;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import java.awt.Color;

public class ProgrammiView extends JPanel {
	private JLayeredPane panelPrincipale;
	private ControllerProgramma controllerProgramma;
	private JLabel labelProgrammi;
	private DefaultTableModel modelTabellaProgrammi;
	private JButton bottoneAggiungiProgramma;
	private JLabel labelAggiungiProgramma;
	private JPanel panelAggiungiProgramma;
	private JPanel panelTabellaProgrammi;
	private JScrollPane scrollPaneTabellaProgrammi;
	private JTable tabellaProgrammi;
	
	public ProgrammiView(JLayeredPane panelPrincipale) {
		this.panelPrincipale = panelPrincipale;
		controllerProgramma = new ControllerProgramma();
		
		inizializzazione();
	}
	public void inizializzazione() {
		labelProgrammi = new JLabel("PROGRAMMI");
		labelProgrammi.setHorizontalAlignment(SwingConstants.CENTER);
		labelProgrammi.setFont(new Font("Arial", Font.PLAIN, 25));
		bottoneAggiungiProgramma = new JButton("Aggiungi");
		labelAggiungiProgramma = new JLabel("Nuovo programma:");
		panelAggiungiProgramma = new JPanel();
		
		panelTabellaProgrammi = new JPanel();
		
		tabellaProgrammi = new JTable();
		tabellaProgrammi.setFont(new Font("Arial", Font.PLAIN, 11));
		
		tabellaProgrammi.setEnabled(false);
		
		tabellaProgrammi.setRowHeight(40);
		
		scrollPaneTabellaProgrammi = new JScrollPane(tabellaProgrammi);
		scrollPaneTabellaProgrammi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		
		String[] colonne = {"Elemento", "ID", "Visualizza", "Elimina"};
		modelTabellaProgrammi = (DefaultTableModel) tabellaProgrammi.getModel();
		modelTabellaProgrammi.setColumnIdentifiers(colonne);
		
		
		setLayoutProgrammi();
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
		GroupLayout gl_panelTabellaProgrammi = new GroupLayout(panelTabellaProgrammi);
		gl_panelTabellaProgrammi.setHorizontalGroup(
			gl_panelTabellaProgrammi.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTabellaProgrammi.createSequentialGroup()
					.addGap(73)
					.addComponent(scrollPaneTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
					.addGap(63))
		);
		gl_panelTabellaProgrammi.setVerticalGroup(
			gl_panelTabellaProgrammi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTabellaProgrammi.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollPaneTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
					.addGap(58))
		);
		panelTabellaProgrammi.setLayout(gl_panelTabellaProgrammi);
		setLayout(groupLayout);
	}
}
