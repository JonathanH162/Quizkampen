package se.nackademin.server.states;

import se.nackademin.io.Event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerStateMachine {

	private final BlockingQueue<Event> eventBlockingQueue = new LinkedBlockingQueue<>();
	private ServerState currentState;

	public ServerStateMachine(ServerState currentState) {
		this.currentState = currentState;
	}

	public void run() {
		while (true) {
			try {
				var event = eventBlockingQueue.take();
				currentState = currentState.transitionToNextState(event);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}

}
