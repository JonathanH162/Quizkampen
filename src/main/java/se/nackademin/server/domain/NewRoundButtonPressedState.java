package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.server.data.ServerEventRepository;

public class NewRoundButtonPressedState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, ServerEventRepository eventManager) {
		return null;
	}
}
