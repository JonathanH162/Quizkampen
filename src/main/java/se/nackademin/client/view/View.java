package se.nackademin.client.view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.ClientEventManager;

public class View extends JFrame {
	private JPanel mainPanel;
	JButton playButton;
	JLabel welcomeLabel;
	BlockingQueue<Event> eventQueue;

	public View(BlockingQueue<Event> eventQueue) throws HeadlessException {
		this.eventQueue = eventQueue;
	}

	public void initiateView() {
		getContentPane().removeAll();
		mainPanel = new JPanel();
		playButton = new JButton("Nytt spel");
		welcomeLabel = new JLabel("Välkommen till Quizkampen");

		add(mainPanel);
		mainPanel.add(welcomeLabel);
		mainPanel.add(playButton);

		playButton.addActionListener(e -> {
			var event = new Event(EventType.START_BUTTON_PRESSED, HostId.EMPTY,HostId.EMPTY,new Object());
			try {
				eventQueue.put(event);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		});
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
		addButtonsToList(answerButtonList, 4, "Answer");
		addButtonsToPanel(answerButtonList, answerButtonPanel);

		categoryScreenPanel.setLayout(new BorderLayout());
		categoryLabel.setFont(font);
		categoryScreenPanel.add(categoryLabel, BorderLayout.NORTH);
		categoryScreenPanel.add(categoryButtonPanel, BorderLayout.CENTER);
		categoryButtonPanel.setLayout(new GridLayout(2, 3));
		addButtonsToList(categoryButtonList, 6, "Category");
		addButtonsToPanel(categoryButtonList, categoryButtonPanel);

		setTitle("Quizkampen");
		setSize(350, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		categoryScreenPanel.setVisible(false);
		questionScreenPanel.setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public JPanel addPointPanel(JPanel pointPanel) {
		pointPanel.add(new JLabel(String.valueOf(player1RoundPoints)), BorderLayout.WEST);
		pointPanel.add(new JLabel(String.valueOf(player2RoundPoints)), BorderLayout.EAST);
		pointPanel.add(new JLabel(category, SwingConstants.CENTER), BorderLayout.CENTER);
		pointPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		return pointPanel;
	}

	public void addButtonsToList(java.util.List<JButton> buttons, Integer amount, String buttonText) {
		if (!buttons.isEmpty()) {
			buttons.clear();
		}
		for (int i = 0; i < amount; i++) {
			buttons.add(new JButton(buttonText + " " + (i + 1)));
		}
	}

	public void addButtonsToPanel(List<JButton> buttons, JPanel buttonPanel) {
		for (JButton button : buttons) {
			buttonPanel.add(button);
			button.setPreferredSize(new Dimension(100, 100));
			button.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (JButton button : categoryButtonList) {
			if (e.getSource() == button) {
				categoryScreenPanel.setVisible(false);
				questionScreenPanel.setVisible(true);
				repaint();
				revalidate();
			}
			for (JButton jButton : answerButtonList) {
				if (e.getSource() == jButton) {
					questionScreenPanel.setVisible(false);
					lobbyScreenPanel.setVisible(true);
					repaint();
					revalidate();
				}
			}
		}
		if (e.getSource() == playButton) {
			lobbyScreenPanel.setVisible(false);
			categoryScreenPanel.setVisible(true);
			repaint();
			revalidate();
		}
	}
}
