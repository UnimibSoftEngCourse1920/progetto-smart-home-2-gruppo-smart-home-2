package application.frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.backend.dominio.Stanza;
import application.controllers.ControllerCasa;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class MainJFrame extends JFrame {

	//COMPONENTI----------------------------
	private JPanel contentPane;
	private JPanel panelMenu;
	private JButton bottoneMenuProgrammi;
	private JLayeredPane panelPrincipale;
	private JButton bottoneMenuStanze;
	private JButton bottoneHomepageProgrammi;
	private JLabel clock;
	private JLabel labelSmartHome;
	
	//VIEW----------------------------------
	private ProgrammiView panelProgrammi;
	private StanzeView panelStanze;
	private HomepageView panelHomepage;
	
	//DOMINIO---------------------------------
	private Simulazione s;
	private ControllerCasa controllerCasa = new ControllerCasa(panelPrincipale);

	/**
	 * Create the frame.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame mainJFrame = new MainJFrame();
					mainJFrame.setVisible(true);
					mainJFrame.setExtendedState(mainJFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		inizializzazione();
	}

	public void inizializzazione() {
		panelMenu = new JPanel();
		panelPrincipale = new JLayeredPane();
		panelPrincipale.setLayout(new BorderLayout(0, 0));
		
		bottoneMenuProgrammi = new JButton("Programmi");
		bottoneMenuStanze = new JButton("Stanze");
		panelProgrammi = new ProgrammiView(panelPrincipale);
		panelStanze = new StanzeView(panelPrincipale);
		panelHomepage = new HomepageView(panelPrincipale);
		
		labelSmartHome = new JLabel("SMART HOME");
		labelSmartHome.setHorizontalAlignment(SwingConstants.CENTER);
		labelSmartHome.setFont(new Font("Arial", Font.PLAIN, 25));
		
		clock = new JLabel("CLOCK");
		clock.setEnabled(false);
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		
		s = new Simulazione(new ControllerProgramma());
		Timer timerSim = new Timer();
		timerSim.schedule(s, 1000, 1000);
		
		
		setLayoutMenu();
		
		gestioneMenu();
		
		startClock();
		
		viewHomepage();
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
		
		GroupLayout gl_panelMenu = new GroupLayout(panelMenu);
		gl_panelMenu.setHorizontalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addComponent(labelSmartHome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addComponent(clock, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addComponent(bottoneMenuProgrammi, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addComponent(bottoneMenuStanze, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		gl_panelMenu.setVerticalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addGap(23)
					.addComponent(labelSmartHome, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clock, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneMenuProgrammi, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneMenuStanze, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(161, Short.MAX_VALUE))
		);
		panelMenu.setLayout(gl_panelMenu);
		
	}
	
	public void gestioneMenu() {		
		bottoneMenuProgrammi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelProgrammi);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
				//panelProgrammi.setVisible(true);
			}
		});
		bottoneMenuStanze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelStanze);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
				//panelStanze.setVisible(true);
			}
		});
		labelSmartHome.addMouseListener(new MouseAdapter() {  
		    public void mouseClicked(MouseEvent e)  {  
		    	viewHomepage();
		    }  
		}); 
	}
	
	public void viewHomepage() {
		
		panelPrincipale.removeAll();
		panelPrincipale.add(panelHomepage);
		panelPrincipale.repaint();
		panelPrincipale.revalidate();
		//panelStanze.setVisible(true);
	}
	
	public void startClock() {
		javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clock.setText(s.getOra().toString());
			}
		});
		
		timer.start();
	}
}
