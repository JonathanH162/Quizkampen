package se.nackademin.client.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends JPanel {
	private final JPanel namePanel = new JPanel();
	JButton playButton = new JButton("Ny Runda");


	public LobbyPanel() {
		setLayout(new BorderLayout());
		add(namePanel, BorderLayout.NORTH);
		add(playButton, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}

	public void setPlayButtonListener(ActionListener actionListener) {
		playButton.addActionListener(actionListener);
	}
}
