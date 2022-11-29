package se.nackademin.client.presentation;

import javax.swing.*;

public class WaitingPanel extends JPanel {
	private final JLabel waitingLabel = new JLabel("Väntar på att motståndaren ska välja kategori");

	public WaitingPanel() {
		add(waitingLabel);
	}


}
