package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;

public class EndState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, EventRepository eventRepository) {
		Thread.currentThread().interrupt();
		return this;
	}

}
