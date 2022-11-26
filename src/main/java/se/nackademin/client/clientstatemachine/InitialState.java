package se.nackademin.client.clientstatemachine;

import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.client.view.View;
import se.nackademin.repositories.eventrepository.ClientEventRepository;

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
