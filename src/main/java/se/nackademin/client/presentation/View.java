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

public class View extends JFrame implements ActionListener {

	private final QuestionRepositoryService questionService = new QuestionRepositoryService();
	private JPanel startPanel;
	JButton startButton;
	JLabel welcomeLabel;
	ClientEventRepository clientEventManager;
	int player1RoundPoints = 0;
	int player2RoundPoints = 0;
	int player1TotalPoints = 0;
	int player2TotalPoints = 0;
	String category = "Category";
	Font font = new Font("", Font.PLAIN, 20);
	JPanel mainPanel = new JPanel();
	JPanel lobbyScreenPanel = new JPanel();
	JPanel questionScreenPanel = new JPanel();
	JPanel categoryScreenPanel = new JPanel();
	JPanel namePanel = new JPanel();
	JPanel totalScorePanel = new JPanel();
	JPanel pointPanel1 = new JPanel();
	JPanel pointPanel2 = new JPanel();
	JPanel pointPanel3 = new JPanel();
	JPanel categoryAndPointPanel = new JPanel();
	JLabel nameLabel1 = new JLabel("Spelare 1");
	JLabel nameLabel2 = new JLabel("Spelare 2");
	JLabel totalScoreLabel = new JLabel("Totalpoäng", SwingConstants.CENTER);
	JButton playButton = new JButton("Ny Runda");
	JLabel totalScoreCounter1 = new JLabel(String.valueOf(player1TotalPoints));
	JLabel totalScoreCounter2 = new JLabel(String.valueOf(player2TotalPoints));
	JPanel answerButtonPanel = new JPanel();
	JLabel questionLabel = new JLabel("Question", SwingConstants.CENTER);
	JPanel categoryButtonPanel = new JPanel();
	JLabel categoryLabel = new JLabel("Välj en Kategori", SwingConstants.CENTER);
	ArrayList<JButton> categoryButtonList = new ArrayList<>();
	ArrayList<JButton> answerButtonList = new ArrayList<>();

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
		showPanel(new WaitingPanel());
	}

	public void showCategoryPanel() {
		showPanel(new CategoryPanel(eventRepository));
	}
	public void showLobbyPanel() {
		showPanel(new LobbyPanel());
	}
	public void showQuestionPanel(String question) {
		showPanel(new QuestionPanel());
	}

	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}
	public JButton getStartButton() {
		return startButton;
	}
	public JButton getPlayButton() {
		return playButton;
	}
	public JLabel getQuestionLabel() {
		return questionLabel;
	}
	public List<JButton> getAnswerButtonList() {
		return answerButtonList;
	}
}
