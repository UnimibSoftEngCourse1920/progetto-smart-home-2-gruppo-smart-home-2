package application.frontend.views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import application.frontend.Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Button;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProgrammiView extends JPanel {
	private JLayeredPane panelPrincipale;
	private JPanel panelTabellaProgrammi;
	private JButton bottone;
	
	public ProgrammiView(JLayeredPane panelPrincipale) {
		this.panelPrincipale = panelPrincipale;
		ProgrammaView panelStanze = new ProgrammaView(panelPrincipale);
		
		panelTabellaProgrammi = new JPanel();
		
		JLabel lblProgrammi = new JLabel("PROGRAMMI");
		lblProgrammi.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgrammi.setFont(new Font("Arial", Font.PLAIN, 25));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(lblProgrammi, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblProgrammi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		bottone = new JButton("New button");
		bottone.addActionListener(new ActionListener() {
			//funziona anche su bottoni fuori dal panel
			public void actionPerformed(ActionEvent e) {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelStanze);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
			}
		});
		panelTabellaProgrammi.add(bottone);
		
	}
}
