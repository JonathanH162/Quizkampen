package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;

interface ServerState{

	ServerState transitionToNextState(Event event, EventRepository eventRepository);
}
