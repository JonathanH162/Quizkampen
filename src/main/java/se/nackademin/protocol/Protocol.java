package se.nackademin.protocol;

import se.nackademin.model.State;

public class Protocol implements Runnable {

	private State currentState;

	public Protocol(State initialState) {
		this.currentState = initialState;
	}

	@Override
	public void run() {
		while (true) {
			currentState = currentState.transitionToNextState();
		}
	}

}
