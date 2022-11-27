package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;

public class SuccessfulConnectionState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		switch (event.getEventType()) {
			case TWO_PLAYERS_CONNECTED -> {
				view.lobbyScreen();
				return new LobbyState();
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}
}

