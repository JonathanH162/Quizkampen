package se.nackademin.client.clientstatemachine;

import se.nackademin.client.view.View;
import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.models.EventType;
import se.nackademin.repositories.eventrepository.ClientEventRepository;

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
