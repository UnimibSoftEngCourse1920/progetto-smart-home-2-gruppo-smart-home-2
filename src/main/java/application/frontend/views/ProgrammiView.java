package application.frontend.views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;









import javax.swing.LayoutStyle.ComponentPlacement;






import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProgrammiView extends JPanel {
	private JLayeredPane panelPrincipale;
	private JPanel panelTabellaProgrammi;
	
	public ProgrammiView(JLayeredPane panelPrincipale) {
		this.panelPrincipale = panelPrincipale;
		
		panelTabellaProgrammi = new JPanel();
		
		JLabel lblProgrammi = new JLabel("PROGRAMMI");
		lblProgrammi.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgrammi.setFont(new Font("Arial", Font.PLAIN, 25));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelTabellaProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(lblProgrammi, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
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
		
		
		
		
	}
}
