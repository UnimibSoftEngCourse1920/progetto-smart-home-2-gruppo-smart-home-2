package application.frontend.views;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import application.backend.programmi.ProgrammaSettimanale;
import application.controllers.ControllerProgramma;

public class ProgrammaSettimanaleView extends JPanel {
	private JLayeredPane panelPrincipale;
	private ControllerProgramma controllerProgramma;
	private ProgrammaSettimanale programmaSettimanale;
	
	public ProgrammaSettimanaleView(JLayeredPane principale, ControllerProgramma programma, ProgrammaSettimanale pSettimanale) {
		this.panelPrincipale = principale;
		this.controllerProgramma = programma;
		this.programmaSettimanale = pSettimanale;
	}

}
