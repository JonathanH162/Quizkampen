package se.nackademin.client.domain;

import se.nackademin.client.presentation.*;
import se.nackademin.core.EventLog;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyState implements ClientState {
	private final EventRepository eventRepository;
	private final CategoryPanel categoryPanel;
	private final LobbyPanel lobbyPanel = new LobbyPanel();
	private String category;
	private int roundCounter;

	public LobbyState(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
		this.categoryPanel = new CategoryPanel(eventRepository);
	}

	@Override
	public ClientState transitionToNextState(Event event, View view, EventRepository eventRepository) {
		switch (event.getEventType()) {
			case WAITING_FOR_CHOICE -> {
				view.showWaitingPanel();
				eventRepository.setSourceId((HostId) event.getData());
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
				var thisPlayer = eventRepository.getHostId();
				var otherPlayer = (thisPlayer.equals(HostId.CLIENT_ONE)) ? HostId.CLIENT_TWO : HostId.CLIENT_ONE;
				var thisPlayerSum = eventLog.getTotalPointsForAllRoundsSoFar(thisPlayer);
				var otherPlayerSum = eventLog.getTotalPointsForAllRoundsSoFar(otherPlayer);

				// var tempMap = (HashMap<HostId, List<Integer>>) event.getData();

				// TODO Send whatever is needed to getScorePanel
				// T ex: eventLog.getPointsForAllRoundsSoFar()

				// lobbyPanel.getScorePanel().display(tempMap);

				lobbyPanel.setPlayerSum(1, thisPlayerSum);
				lobbyPanel.setPlayerSum(2, otherPlayerSum);
				lobbyPanel.setPlayButtonListener((e) -> eventRepository.add(Event.toSelf(EventType.SHOW_CATEGORIES_BUTTON)));
				view.showPanel(lobbyPanel);


				//NewScorePanel scorePanel = new NewScorePanel();
				//view.showPanel(scorePanel);

				//view.showPanel(lobbyPanel);


				return new LobbyState(eventRepository);
			}
			case GAME_FINISHED -> {
				// TODO skicka till en slutlig resultatskärm samt räkna ut vem som har vunnit.
				return this;
			}
				default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

}
