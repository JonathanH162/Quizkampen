package se.nackademin.client.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;

public class View extends JFrame {

	JButton startButton;
	JLabel welcomeLabel;
	ClientEventRepository eventRepository;
	private JPanel currentPanel = new JPanel();


	public View(ClientEventRepository eventRepository) throws HeadlessException {
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

	public void showWelcomePanel() {
		showPanel(new WelcomePanel());
	}

	public void showWaitingPanel() {
		showPanel(new WaitingPanel("Väntar på att motståndaren ska välja kategori."));
	}

	public void showCategoryPanel() {
		showPanel(new CategoryPanel(eventRepository));
	}
	public void showLobbyPanel() {
		showPanel(new LobbyPanel());
	}
	public void showQuestionPanel(String question) {
                		showPanel(new QuestionPanel(eventRepository));
	}

	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}
	public JButton getStartButton() {
		return startButton;
	}
	public JPanel getCurrentPanel() {
		return currentPanel;
	}




}
