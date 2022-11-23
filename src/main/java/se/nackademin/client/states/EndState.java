package se.nackademin.client.states;

import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

public class EndState extends State {

	public EndState() {
		super();
	}

	@Override
	public void setEventManager(EventManager eventManager) {

	}

	@Override
	public void transitionToNextState() {
		System.out.println("Endstate reached by client.");
		Thread.currentThread().interrupt();
	}

}
