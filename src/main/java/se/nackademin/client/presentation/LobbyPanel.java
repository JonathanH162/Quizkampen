package se.nackademin.client.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends JPanel {
	JButton playButton = new JButton("Ny Runda");


	public LobbyPanel() {


		add(playButton);
		revalidate();
		repaint();
	}

	public void setPlayButtonListener(ActionListener actionListener) {
		playButton.addActionListener(actionListener);
	}
}
