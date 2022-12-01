package se.nackademin.client.presentation;

import javax.swing.*;
import java.awt.*;

import se.nackademin.core.repositories.eventrepository.EventRepository;

public class View extends JFrame {

	EventRepository eventRepository;
	private JPanel currentPanel = new JPanel();


	public View(EventRepository eventRepository) throws HeadlessException {
		this.eventRepository = eventRepository;
		setTitle("Quizkampen");
		setSize(450, 500);
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
