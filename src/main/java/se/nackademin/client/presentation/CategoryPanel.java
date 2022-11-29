package se.nackademin.client.presentation;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel extends JPanel {
	private final QuestionRepositoryService questionService = new QuestionRepositoryService();
	List<JButton> categoryButtonList = new ArrayList<>();
	private final JLabel categoryLabel = new JLabel();
	private final JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
	private final EventRepository eventRepository;

	public CategoryPanel(EventRepository eventRepository) {
		this.eventRepository = eventRepository;

		setLayout(new BorderLayout());
		add(categoryLabel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		addCategoryButtonsToList(categoryButtonList, questionService.getAllCategoriesName());
		addButtonsToPanel(categoryButtonList, buttonPanel);

	}
	public void addCategoryButtonsToList(java.util.List<JButton> buttons, List<String> buttonNameList) { //Lägger till kategori knappar i en lista
		if (!buttons.isEmpty()) {
			buttons.clear();
		}
		for (int i = 0; i < buttonNameList.size(); i++) {
			final String categoryName = buttonNameList.get(i);
			var button = new JButton(categoryName);
			button.addActionListener((e) -> eventRepository.add(Event.toSelf(EventType.CATEGORY_CHOSEN_BUTTON,
					categoryName)));
			buttons.add(button);
		}
	}
	public void addButtonsToPanel(List<JButton> buttons, JPanel buttonPanel) { //Skapar upp knappar i respektive panel ifrån en lista av knappar
		for (JButton button : buttons) {
			buttonPanel.add(button);
			button.setPreferredSize(new Dimension(100, 100));
		}
	}

	public JLabel getCategoryLabel() {
		return categoryLabel;
	}

}
