package se.nackademin.client.presentation;

import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionPanel extends JPanel {
	private final QuestionRepositoryService questionService = new QuestionRepositoryService();
	ClientEventRepository clientEventRepository = new ClientEventRepository();

	ArrayList<JButton> answerButtonList = new ArrayList<>();
	private final JLabel questionLabel;
	private final JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

	public QuestionPanel() {
		setLayout(new GridLayout(2, 2));

		questionLabel = new JLabel();

		add(questionLabel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);

	}
	public void addAnswerButtonsToList(java.util.List<JButton> buttons, List<String> buttonNameList) { //Lägger till svarsknappar i en lista
		if (!buttons.isEmpty()) {
			buttons.clear();
		}
		for (int i = 0; i < buttonNameList.size(); i++) {
			final String answer = buttonNameList.get(i);
			var button = new JButton(answer);
			button.addActionListener((e) -> clientEventRepository.add(Event.toSelf(EventType.ANSWER_CHOSEN_BUTTON, button.getText())));
			buttons.add(button);
		}
	}
	public void addButtonsToPanel(List<JButton> buttons, JPanel buttonPanel) { //Skapar upp knappar i respektive panel ifrån en lista av knappar
		for (JButton button : buttons) {
			buttonPanel.add(button);
			button.setPreferredSize(new Dimension(100, 100));
		}
	}

	public JLabel getQuestionLabel() {
		return questionLabel;
	}
	public List<JButton> getAnswerButtonList() {
		return answerButtonList;
	}
	public JPanel getButtonPanel() {
		return buttonPanel;
	}

}
