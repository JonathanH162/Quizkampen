package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;
import se.nackademin.core.utils.ConfigProperties;

public class LobbyState implements ClientState {
	private final QuestionRepositoryService questionService = new QuestionRepositoryService();
	private final EventRepository eventRepository = new ClientEventRepository();
	private final ConfigProperties configProperties = new ConfigProperties();
	private final CategoryPanel categoryPanel = new CategoryPanel(eventRepository);
	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		switch (event.getEventType()) {
			case WAITING_FOR_CHOICE -> {
				view.showWaitingPanel();
				eventRepository.setSourceId((HostId) event.getData());
				view.getPlayButton().setVisible(false);
				JOptionPane.showMessageDialog(null, "Motståndarens tur att välja kategori, var god vänta.");
				return this;
			}
			case NEXT_TO_CHOOSE -> {
				eventRepository.setSourceId((HostId) event.getData());
				view.getPlayButton().setVisible(true);
				return this;
			}

			case SHOW_CATEGORIES_BUTTON -> {
				view.categoryScreen();
				return this;
			}

			case CATEGORY_CHOSEN_BUTTON -> {
				var selectedCategory = (String) event.getData();
				eventRepository.add(Event.toServer(EventType.CATEGORY_CHOSEN, selectedCategory));
				return this;
			}

			case CATEGORY_CHOSEN -> {
				return new QuestionState();
			}
			case ROUND_FINISHED -> {
				System.out.println(event.getData());
				return null;
			}
				default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

}
