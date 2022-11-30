package se.nackademin.client.domain;

import se.nackademin.client.presentation.CategoryPanel;
import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class LobbyState implements ClientState {
	private final QuestionRepositoryService questionService = new QuestionRepositoryService();
	private final EventRepository eventRepository;
	private final ConfigProperties configProperties = new ConfigProperties();
	private final CategoryPanel categoryPanel;

	public LobbyState(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
		this.categoryPanel = new CategoryPanel(eventRepository);
	}

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		switch (event.getEventType()) {
			case WAITING_FOR_CHOICE -> {
				view.showWaitingPanel();
				eventRepository.setSourceId((HostId) event.getData());
				return new QuestionState();
			}
			case NEXT_TO_CHOOSE -> {
				eventRepository.setSourceId((HostId) event.getData());
				return this;
			}

			case SHOW_CATEGORIES_BUTTON -> {
				view.showPanel(categoryPanel);
				return this;
			}

			case CATEGORY_CHOSEN_BUTTON -> {
				var selectedCategory = (String) event.getData();
				eventRepository.add(Event.toServer(EventType.CATEGORY_CHOSEN, selectedCategory));
				return new QuestionState();
			}

			case CATEGORY_CHOSEN -> {
				return new QuestionState();
			}
			case ROUND_FINISHED -> {
				System.out.println(event.getData());
				return null;
			}
			case GAME_FINISHED -> {
				// TODO
				return this;
			}
				default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

}
