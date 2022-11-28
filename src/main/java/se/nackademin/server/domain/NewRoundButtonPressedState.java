package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;

public class NewRoundButtonPressedState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, EventRepository eventRepository) {
		return null;
	}
}
