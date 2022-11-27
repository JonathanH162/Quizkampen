package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.server.data.ServerEventRepository;

interface ServerState{

	ServerState transitionToNextState(Event event, ServerEventRepository eventRepository);
}
