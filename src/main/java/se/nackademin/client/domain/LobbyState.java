package se.nackademin.client.domain;

import se.nackademin.client.presentation.*;
import se.nackademin.core.EventLog;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;

public class LobbyState implements ClientState {
	private final CategoryPanel categoryPanel;
	private final LobbyPanel lobbyPanel = new LobbyPanel();
	private String category;

	public LobbyState(EventRepository eventRepository) {
		this.categoryPanel = new CategoryPanel(eventRepository);
	}

	@Override
	public ClientState transitionToNextState(Event event, View view, EventRepository eventRepository) {
		switch (event.getEventType()) {
			case WAITING_FOR_CHOICE -> {
				view.showPanel(new WaitingPanel("Väntar på att motståndaren ska välja kategori."));
				return new QuestionState(eventRepository);
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
				category = (String) event.getData();
				eventRepository.add(Event.toServer(EventType.CATEGORY_CHOSEN, category));
				return new QuestionState(eventRepository);
			}

			case CATEGORY_CHOSEN -> {
				return new QuestionState(eventRepository);
			}
			case ROUND_FINISHED -> {
				var eventLog = (EventLog) event.getData();
				view.showPanel(ScorePanel.create(eventLog,eventRepository));
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				lobbyPanel.setPlayButtonListener((e) -> eventRepository.add(Event.toSelf(EventType.SHOW_CATEGORIES_BUTTON)));
				view.showPanel(lobbyPanel);

				return new LobbyState(eventRepository);
			}
			case GAME_FINISHED -> {
				var eventLog = (EventLog) event.getData();
				view.showPanel(ScorePanel.create(eventLog,eventRepository));
				return this;
			}
				default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

}
