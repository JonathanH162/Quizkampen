package se.nackademin.client.states;

import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

public class ReceivedIdState extends State {

	public ReceivedIdState(EventManager eventManager) {
		super(eventManager);
	}

	@Override
	public void setEventManager(EventManager eventManager) {

	}

	@Override
	public State transitionToNextState() {
/*		System.out.println("Client is waiting for the next event.");
		var event = eventManager.getNextEvent();
		switch (event.getEventType()) {
			case GAME_OVER -> {
				return new EndState(eventManager);
			}
			default -> throw new RuntimeException("Unexpected event: " + event);
		}*/
		return null;
	}
}
