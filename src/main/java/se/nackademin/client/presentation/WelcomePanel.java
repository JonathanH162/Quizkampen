package se.nackademin.client.presentation;


import javax.swing.*;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {
	private final JButton startButton;
	private final JLabel welcomeLabel;


	public WelcomePanel() {
		startButton = new JButton("Nytt spel");
		welcomeLabel = new JLabel("Välkommen till Quizkampen", SwingConstants.CENTER);

		add(welcomeLabel);
		add(startButton);
	}
	public JLabel getWelcomeLabel () {
		return welcomeLabel;
	}
	public void setStartButtonListener(ActionListener actionListener) {
		startButton.addActionListener(actionListener);
	}
	public JButton getStartButton () {
		return startButton;
	}


}
