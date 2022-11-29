package se.nackademin.client.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.repositories.eventrepository.models.HostId;
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

	public View(ClientEventRepository clientEventManager) throws HeadlessException {
		this.clientEventManager = clientEventManager;
	}

	public void initiateView() {
		getContentPane().removeAll();
		startPanel = new JPanel();
		startButton = new JButton("Nytt spel");
		welcomeLabel = new JLabel("Välkommen till Quizkampen");

		add(startPanel);
		startPanel.add(welcomeLabel);
		startPanel.add(startButton);

		startButton.addActionListener(e -> clientEventManager.sendEvent(Event.toSelf(EventType.START_BUTTON_PRESSED)));
		setTitle("Quizkampen");
		setSize(350, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void lobbyScreen() {
		this.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(lobbyScreenPanel);
		mainPanel.add(questionScreenPanel);
		mainPanel.add(categoryScreenPanel);

		lobbyScreenPanel.setLayout(new BorderLayout());
		lobbyScreenPanel.add(namePanel, BorderLayout.NORTH);
		lobbyScreenPanel.add(categoryAndPointPanel, BorderLayout.CENTER);
		lobbyScreenPanel.add(playButton, BorderLayout.SOUTH);
		playButton.addActionListener(this);
		namePanel.setLayout(new BorderLayout());
		namePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		pointPanel1.setLayout(new BorderLayout());
		pointPanel2.setLayout(new BorderLayout());
		pointPanel3.setLayout(new BorderLayout());
		namePanel.add(nameLabel1, BorderLayout.WEST);
		nameLabel1.setFont(font);
		namePanel.add(nameLabel2, BorderLayout.EAST);
		nameLabel2.setFont(font);
		categoryAndPointPanel.setLayout(new GridLayout(4, 1));
		categoryAndPointPanel.add(addPointPanel(pointPanel1));
		categoryAndPointPanel.add(addPointPanel(pointPanel2));
		categoryAndPointPanel.add(addPointPanel(pointPanel3));
		totalScorePanel.setLayout(new BorderLayout());
		totalScorePanel.add(totalScoreLabel, BorderLayout.CENTER);
		totalScorePanel.add(totalScoreCounter1, BorderLayout.EAST);
		totalScorePanel.add(totalScoreCounter2, BorderLayout.WEST);
		totalScorePanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		categoryAndPointPanel.add(totalScorePanel);

		questionScreenPanel.setLayout(new BorderLayout());
		questionLabel.setFont(font);
		questionScreenPanel.add(questionLabel, BorderLayout.NORTH);
		questionScreenPanel.add(answerButtonPanel, BorderLayout.CENTER);
		answerButtonPanel.setLayout(new GridLayout(2, 2));



		categoryScreenPanel.setLayout(new BorderLayout());
		categoryLabel.setFont(font);
		categoryScreenPanel.add(categoryLabel, BorderLayout.NORTH);
		categoryScreenPanel.add(categoryButtonPanel, BorderLayout.CENTER);
		categoryButtonPanel.setLayout(new GridLayout(2, 3));


		setTitle("Quizkampen");
		setSize(350, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		pointPanel1.setVisible(false);
		pointPanel2.setVisible(false);
		pointPanel3.setVisible(false);
		questionScreenPanel.setVisible(false);
		categoryScreenPanel.setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
		repaint();
	}

	public void questionScreen() {
		// Sätter värde innan på questionlabel med frågan som ska ställas, görs i questionState
		addAnswerButtonsToList(answerButtonList, questionService.getAllPossibleAnswers(questionLabel.getText()));
		addButtonsToPanel(answerButtonList, answerButtonPanel);
		lobbyScreenPanel.setVisible(false);
		categoryScreenPanel.setVisible(false);
		questionScreenPanel.setVisible(true);
	}

	public void categoryScreen() {
		addCategoryButtonsToList(categoryButtonList, questionService.getAllCategoriesName());
		addButtonsToPanel(categoryButtonList, categoryButtonPanel);
		lobbyScreenPanel.setVisible(false);
		categoryScreenPanel.setVisible(true);
		questionScreenPanel.setVisible(false);
	}


	public JPanel addPointPanel(JPanel pointPanel) {
		pointPanel.add(new JLabel(String.valueOf(player1RoundPoints)), BorderLayout.WEST);
		pointPanel.add(new JLabel(String.valueOf(player2RoundPoints)), BorderLayout.EAST);
		pointPanel.add(new JLabel(category, SwingConstants.CENTER), BorderLayout.CENTER);
		pointPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		return pointPanel;
	}

	public void addCategoryButtonsToList(java.util.List<JButton> buttons,List<String> buttonNameList) { //Lägger till kategori knappar i en lista
		if (!buttons.isEmpty()) {
			buttons.clear();
		}
		for (int i = 0; i < buttonNameList.size(); i++) {
			final String categoryName = buttonNameList.get(i);
			var button = new JButton(categoryName);
			button.addActionListener((e) -> clientEventManager.sendEvent(Event.toSelf(EventType.CATEGORY_CHOSEN_BUTTON, categoryName)));
			buttons.add(button);
		}
	}
	public void addAnswerButtonsToList(java.util.List<JButton> buttons,List<String> buttonNameList) { //Lägger till svarsknappar i en lista
		if (!buttons.isEmpty()) {
			buttons.clear();
		}
		for (int i = 0; i < buttonNameList.size(); i++) {
			final String answer = buttonNameList.get(i);
			var button = new JButton(answer);
			button.addActionListener((e) -> clientEventManager.sendEvent(Event.toSelf(EventType.ANSWER_CHOSEN_BUTTON, button.getText())));
			buttons.add(button);
		}
	}

	public void addButtonsToPanel(List<JButton> buttons, JPanel buttonPanel) { //Skapar upp knappar i respektive panel ifrån en lista av knappar
		for (JButton button : buttons) {
			buttonPanel.add(button);
			button.setPreferredSize(new Dimension(100, 100));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			clientEventManager.sendEvent(Event.toSelf(EventType.SHOW_CATEGORIES_BUTTON));
			revalidate();
			repaint();
		}
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
