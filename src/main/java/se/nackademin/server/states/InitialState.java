package se.nackademin.server.states;

import se.nackademin.client.states.ClientReadyState;
import se.nackademin.io.HostId;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

public class InitialState extends State {

	public InitialState(EventManager eventManager) {
		super(eventManager);
	}

	@Override
	public State transitionToNextState() {
		var event = eventManager.getEvent();
		switch (event.getEventType()) {
			case READY -> {
				eventManager.setSourceId((HostId) event.getData()); // Set this client's ID to the one in the event.
				return new ClientReadyState(); // Return the next state.
			}
			default -> throw new RuntimeException("Unexpected event: " + event);
		}
	}

}
