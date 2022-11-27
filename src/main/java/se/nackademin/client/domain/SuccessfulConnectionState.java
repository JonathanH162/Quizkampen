package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;

public class SuccessfulConnectionState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		if (event.getEventType().equals(EventType.TWO_PLAYERS_CONNECTED)) {
			view.lobbyScreen();
			return new LobbyState();
		}
		return  null;
	}
}

/*
	public void transitionToNextState() {
		try {
			var event = eventQueue.take();
			if (event.getEventType().equals(EventType.TWO_PLAYER_CONNECTED)) {
				System.out.println("Anslutningen lyckades");
				new LobbyState(view,eventQueue).transitionToNextState();

			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}*/
