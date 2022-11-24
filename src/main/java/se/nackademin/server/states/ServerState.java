package se.nackademin.server.states;

import se.nackademin.io.Event;

interface ServerState{

	ServerState transitionToNextState(Event event);
}
