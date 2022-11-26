package se.nackademin.server.serverstatemachine;

import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.ServerEventRepository;

interface ServerState{

	ServerState transitionToNextState(Event event, ServerEventRepository eventManager);
}
