package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ServerEventManager;

interface ServerState{

	ServerState transitionToNextState(Event event, ServerEventManager eventManager);
}
