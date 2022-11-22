package se.nackademin.client.states;

import se.nackademin.model.events.ReadyEvent;
import se.nackademin.model.events.SetSourceIdEvent;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

public class ClientReadyState implements State {

	@Override
	public State process(SetSourceIdEvent event, EventManager eventManager) {
		return null;
	}

	@Override
	public State process(ReadyEvent event, EventManager eventManager) {
		return null;
	}

}
