package se.nackademin.protocol;

import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.server.states.InitialState;
import se.nackademin.model.State;

public class Protocol implements Runnable {

	private State currentState;

	public Protocol(EventManager eventManager) {
		this.currentState = new InitialState(eventManager);
	}

	@Override
	public void run() {
		while (true) {
			currentState = currentState.transitionToNextState();
		}
	}

}
