package se.nackademin.server.serverstatemachine;

import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.ServerEventRepository;

public class NewRoundButtonPressedState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, ServerEventRepository eventManager) {
		return null;
	}
}
