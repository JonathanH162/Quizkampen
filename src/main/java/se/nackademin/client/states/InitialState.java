package se.nackademin.client.states;

import se.nackademin.io.Event;
import se.nackademin.client.view.View;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.ClientEventManager;

public class InitialState implements ClientState {
	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager) {

		switch (event.getEventType()) {
			case INITIAL_EVENT, CONNECTION_FAILED -> {
				view.initiateView();
				return new WelcomeScreenState();
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}
}
