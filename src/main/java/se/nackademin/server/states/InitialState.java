package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.HostId;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

/**
 * The initial state of the server. Sends ID's to the clients to that they know what to put as sender when they send
 * events to the server.
 */
public class InitialState extends State {

	public InitialState(EventManager eventManager) {
		super(eventManager);
	}

	@Override
	public State transitionToNextState() {
		var eventToClientOne = Event.toClientOne(EventType.NEW_ID, HostId.CLIENT_ONE);
		var eventToClientTwo = Event.toClientTwo(EventType.NEW_ID, HostId.CLIENT_TWO);

		eventManager.sendEvent(eventToClientOne);
		eventManager.sendEvent(eventToClientTwo);

		return new EndState(eventManager);
	}

}
