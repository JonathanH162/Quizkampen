package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ServerEventManager;

public class EndState implements ServerState {
	@Override
	public ServerState transitionToNextState(Event event, ServerEventManager eventManager) {
		return null;
	}

	@Override
	public ServerState transitionToNextState(Event event) {
		System.out.println("Endstate reached by server.");
		Thread.currentThread().interrupt();
		return null;
	}

}
