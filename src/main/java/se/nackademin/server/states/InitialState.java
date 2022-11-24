package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.StateOLD;

/**
 * The initial state of the server. Sends ID's to the clients to that they know what to put as sender when they send
 * events to the server.
 */
public class InitialState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event) {
		return null;
	}

}

/*		var eventToClientOne = Event.toClientOne(EventType.NEW_ID, HostId.CLIENT_ONE);
		var eventToClientTwo = Event.toClientTwo(EventType.NEW_ID, HostId.CLIENT_TWO);

		eventManager.sendEvent(eventToClientOne);
		eventManager.sendEvent(eventToClientTwo);

		return new EndState(eventManager);*/