package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ServerEventManager;

public class NewRoundButtonPressedState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, ServerEventManager eventManager) {
		return null;
	}
}
