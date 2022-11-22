package se.nackademin.protocol;

import se.nackademin.io.eventrouters.EventRouter;
import se.nackademin.server.states.InitialState;
import se.nackademin.model.State;

public class Protocol implements Runnable {

	private State currentState = new InitialState();
	private final EventRouter eventRouter;

	public Protocol(EventRouter eventRouter) {
		this.eventRouter = eventRouter;
	}

	@Override
	public void run() {
		while (true) {
			currentState = eventRouter.getEvent().processMe(currentState, eventRouter);
		}
	}

}
