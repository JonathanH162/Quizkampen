package se.nackademin.client.domain;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.presentation.View;
import se.nackademin.client.data.ClientEventRepository;

public class InitialState implements ClientState {
	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {

		switch (event.getEventType()) {
			case INITIAL_EVENT, CONNECTION_FAILED -> {
				view.initiateView();
				return new WelcomeScreenState();
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}
}
