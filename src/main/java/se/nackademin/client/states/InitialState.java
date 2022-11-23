package se.nackademin.client.states;

import se.nackademin.io.HostId;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

public class InitialState extends State {

	public InitialState(EventManager eventManager) {
		super(eventManager);
	}

	@Override
	public State transitionToNextState() {
		var event = eventManager.getNextEvent();
		switch (event.getEventType()) {
			case NEW_ID -> {
				System.out.println("New ID received by client.");

				var newId = (HostId) event.getData();
				eventManager.setSourceId(newId);

				return new ReceivedIdState(eventManager);
			}
			default -> throw new RuntimeException("Unexpected event: " + event);
		}
	}

}
