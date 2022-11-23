package se.nackademin.server.states;

import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

public class EndState extends State {

	public EndState(EventManager eventManager) {
		super(eventManager);
	}

	@Override
	public State transitionToNextState() {
		System.out.println("Endstate reached by server.");
		Thread.currentThread().interrupt();
		return null;
	}

}
