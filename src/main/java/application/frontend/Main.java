package application.frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.controllers.ControllerProgramma;
import application.controllers.Simulazione;
import application.frontend.views.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	private JPanel contentPane;
	private JPanel panelMenu;
	private JButton bottoneProgrammi;
	private JLayeredPane panelPrincipale;
	private ProgrammiView panelProgrammi;
	private StanzeView panelStanze;
	private JButton bottoneStanze;
	JLabel lblNewLabel_1;
	Simulazione s;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		creazioneContentPane();
	}

	public void creazioneContentPane() {
		panelMenu = new JPanel();
		panelPrincipale = new JLayeredPane();
		panelPrincipale.setLayout(new BorderLayout(0, 0));
		bottoneProgrammi = new JButton("Programmi");
		bottoneStanze = new JButton("Stanze");
		panelProgrammi = new ProgrammiView(panelPrincipale);
		panelStanze = new StanzeView();
		 s = new Simulazione(new ControllerProgramma());
		 Timer timerSim = new Timer();
		 timerSim.schedule(s, 1000, 1000);
		
		setLayoutMenu();
		
		gestionePanelMenu();
		
		startClock();
	}
	
	public void setLayoutMenu() {
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelMenu, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelPrincipale, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelMenu, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
				.addComponent(panelPrincipale, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		
		JLabel lblNewLabel = new JLabel("SMART HOME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		
		lblNewLabel_1 = new JLabel("New label");
		
         
        
		
		GroupLayout gl_panelMenu = new GroupLayout(panelMenu);
		gl_panelMenu.setHorizontalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addComponent(bottoneProgrammi, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addComponent(bottoneStanze, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panelMenu.setVerticalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(bottoneProgrammi, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneStanze, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panelMenu.setLayout(gl_panelMenu);
	}
	
	public void gestionePanelMenu() {		
		bottoneProgrammi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelProgrammi);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
				//panelProgrammi.setVisible(true);
			}
		});
		bottoneStanze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelStanze);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
				//panelStanze.setVisible(true);
			}
		});
	}
	
	public void startClock() {
		javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setText(s.getOra().toString());
			}
		});
		
		timer.start();
	}
}
