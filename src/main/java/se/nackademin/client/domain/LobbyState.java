package se.nackademin.client.domain;

import se.nackademin.client.presentation.CategoryPanel;
import se.nackademin.client.presentation.LobbyPanel;
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
	private final EventRepository eventRepository;
	private final CategoryPanel categoryPanel;
	private final LobbyPanel lobbyPanel = new LobbyPanel();
	private String category;

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
				var tempMap = (HashMap<HostId, List<Integer>>) event.getData();
				var thisPlayerPoints = tempMap.get(eventRepository.getHostId());
				HostId otherPlayerID = HostId.EMPTY;
				for (HostId key : tempMap.keySet()) {
					if (key != eventRepository.getHostId()) {
						otherPlayerID = key;
					}
				}
				var otherPlayerPoints = tempMap.get(otherPlayerID);

				System.out.println(thisPlayerPoints);
				System.out.println(otherPlayerPoints);

				var thisPlayerPointsLastRound = thisPlayerPoints.get(thisPlayerPoints.size()-1);
				var otherPlayerPointsLastRound = otherPlayerPoints.get(thisPlayerPoints.size()-1);
				var thisPlayerSum = thisPlayerPoints.stream().mapToInt(Integer::intValue).sum();
				var otherPlayerSum = otherPlayerPoints.stream().mapToInt(Integer::intValue).sum();



				lobbyPanel.addPointPanel(new JPanel(),thisPlayerPointsLastRound,otherPlayerPointsLastRound,category);
				//lobbyPanel.getTotalScoreCounter1().setText(String.valueOf(thisPlayerSum));
				//lobbyPanel.getTotalScoreCounter2().setText(String.valueOf(otherPlayerSum));
				lobbyPanel.setPlayerSum(1, thisPlayerSum);
				lobbyPanel.setPlayerSum(2, otherPlayerSum);
				lobbyPanel.setPlayButtonListener((e) -> eventRepository.add(Event.toSelf(EventType.SHOW_CATEGORIES_BUTTON)));
				view.showPanel(lobbyPanel);


				return new LobbyState(eventRepository);
			}
			case GAME_FINISHED -> {
				// TODO
				return this;
			}
				default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

}
