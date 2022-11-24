package se.nackademin.server.states;

import se.nackademin.io.Event;

public class EndState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event) {
		System.out.println("Endstate reached by server.");
		Thread.currentThread().interrupt();
		return null;
	}

}
