package application.frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import application.controllers.ControllerCasa;
import application.controllers.ControllerProgramma;
import application.controllers.Simulazione;
import application.frontend.support.Alert;
import application.frontend.views.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.Timer;
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
	private JButton bottoneMenuRobotPulizia;
	private JButton bottoneMenuAllarme;
	
	private JLabel clock;
	private JLabel labelSmartHome;
	
	//VIEW----------------------------------
	private ProgrammiView panelProgrammi;
	private StanzeView panelStanze;
	private HomepageView panelHomepage;
	private RobotPuliziaView panelRobot;
	private AllarmeView panelAllarme;
	
	//DOMINIO---------------------------------
	private transient Simulazione s;
	private transient ControllerCasa casa;
	private transient ControllerProgramma controllerProgramma;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
				try {
					MainJFrame mainJFrame = new MainJFrame();
					mainJFrame.setVisible(true);
					mainJFrame.setExtendedState(mainJFrame.getExtendedState() | java.awt.Frame.MAXIMIZED_BOTH);
					
				} catch (Exception e) {
					(new Alert()).errore("Problema caricamento programma", "Errore");
				}
		});
	}
	
	public MainJFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		inizializzazione();
	}

	public void inizializzazione() {
		casa = new ControllerCasa(this);
		casa.addRobot(casa.getStanza("Cucina"));
		controllerProgramma = new ControllerProgramma();
		controllerProgramma.setCasa(casa);
		
		//creazioneProgrammi
		controllerProgramma.creazioneProgrammi();
		
		panelMenu = new JPanel();
		panelPrincipale = new JLayeredPane();
		panelPrincipale.setLayout(new BorderLayout(0, 0));
		
		bottoneMenuProgrammi = new JButton("Programmi");
		bottoneMenuStanze = new JButton("Stanze");
		bottoneMenuRobotPulizia = new JButton("Robot Pulizia");
		bottoneMenuAllarme = new JButton("Allarme");
		panelProgrammi = new ProgrammiView(panelPrincipale, controllerProgramma);
		panelStanze = new StanzeView(casa);
		panelHomepage = new HomepageView();
		panelRobot = new RobotPuliziaView(casa);
		panelAllarme = new AllarmeView(casa);
		
		labelSmartHome = new JLabel("SMART HOME");
		labelSmartHome.setHorizontalAlignment(SwingConstants.CENTER);
		labelSmartHome.setFont(new Font("Arial", Font.PLAIN, 25));
		
		s = new Simulazione(controllerProgramma);
		Timer timerSim = new Timer();
		timerSim.schedule(s, 1000, 1000);
		
		clock = new JLabel(s.getGiorno() + ": "+"00:00");
		clock.setEnabled(false);
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		
		setLayoutMenu();
		
		gestioneMenu();
		
		startClock();
		
		viewHomepage();
	}
	
	public void setLayoutMenu() {
		GroupLayout glcontentPane = new GroupLayout(contentPane);
		glcontentPane.setHorizontalGroup(
				glcontentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(glcontentPane.createSequentialGroup()
					.addComponent(panelMenu, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelPrincipale, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
		);
		glcontentPane.setVerticalGroup(
				glcontentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelMenu, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
				.addComponent(panelPrincipale, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
		);
		contentPane.setLayout(glcontentPane);

		GroupLayout glpanelMenu = new GroupLayout(panelMenu);
		glpanelMenu.setHorizontalGroup(
			glpanelMenu.createParallelGroup(Alignment.LEADING)
				.addComponent(labelSmartHome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
				.addComponent(clock, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
				.addGroup(glpanelMenu.createSequentialGroup()
					.addGroup(glpanelMenu.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(bottoneMenuProgrammi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(bottoneMenuStanze, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(bottoneMenuRobotPulizia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(glpanelMenu.createSequentialGroup()
					.addComponent(bottoneMenuAllarme, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		glpanelMenu.setVerticalGroup(
			glpanelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(glpanelMenu.createSequentialGroup()
					.addGap(23)
					.addComponent(labelSmartHome, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clock, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneMenuProgrammi, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneMenuStanze, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneMenuRobotPulizia, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottoneMenuAllarme, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panelMenu.setLayout(glpanelMenu);
		
	}
	
	public void gestioneMenu() {		
		bottoneMenuProgrammi.addActionListener(e ->{
				panelPrincipale.removeAll();
				panelPrincipale.add(panelProgrammi);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
		});
		bottoneMenuStanze.addActionListener(e -> {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelStanze);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
			
		});
		labelSmartHome.addMouseListener(new MouseAdapter() { 
			@Override
		    public void mouseClicked(MouseEvent e)  {  
		    	viewHomepage();
		    }  
		}); 
		bottoneMenuRobotPulizia.addActionListener(e -> {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelRobot);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
		});
		bottoneMenuAllarme.addActionListener(e -> {
				panelPrincipale.removeAll();
				panelPrincipale.add(panelAllarme);
				panelPrincipale.repaint();
				panelPrincipale.revalidate();
		});
	}
	
	public void viewHomepage() {
		panelPrincipale.removeAll();
		panelPrincipale.add(panelHomepage);
		panelPrincipale.repaint();
		panelPrincipale.revalidate();
	}
	
	public void startClock() {
		javax.swing.Timer timer = new javax.swing.Timer(1000, e -> 
				clock.setText(s.getGiorno() + ": "+ s.getOra().toString()));
		
		timer.start();
	}
	
	public RobotPuliziaView getRobotView() {
		return this.panelRobot;
	}

	public AllarmeView getPanelAllarme() {
		return panelAllarme;
	}
	
	public ProgrammiView getPanelProgrammi() {
		return this.panelProgrammi;
	}
	
	public Simulazione getSimulazione() {
		return this.s;
	}
	
	public StanzeView getPanelStanze() {
		return this.panelStanze;
	}
}
