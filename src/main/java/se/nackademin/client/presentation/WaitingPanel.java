package se.nackademin.client.presentation;

import javax.swing.*;

public class WaitingPanel extends JPanel {
	private final JLabel waitingLabel;

	public WaitingPanel(String text) {
		waitingLabel = new JLabel(text);
		add(waitingLabel);
	}

}

