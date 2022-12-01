package se.nackademin.client.presentation;

import javax.swing.*;
import java.awt.*;

import se.nackademin.core.repositories.eventrepository.EventRepository;

public class View extends JFrame {

	JButton startButton;
	JLabel welcomeLabel;
	EventRepository eventRepository;
	private JPanel currentPanel = new JPanel();


	public View(EventRepository eventRepository) throws HeadlessException {
		this.eventRepository = eventRepository;
		setTitle("Quizkampen");
		setSize(350, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void showPanel(JPanel panel) {
		currentPanel.setVisible(false);
		remove(currentPanel);
		currentPanel = panel;
		add(currentPanel);
		currentPanel.setVisible(true);
		repaint();
		revalidate();
	}
}
