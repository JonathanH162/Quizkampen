package se.nackademin.server.states;

import se.nackademin.model.events.ReadyEvent;
import se.nackademin.model.events.SetSourceIdEvent;
import se.nackademin.io.eventrouters.EventRouter;
import se.nackademin.model.State;

public class InitialState implements State {

	@Override
	public State process(SetSourceIdEvent event, EventRouter eventRouter) {
		return null;
	}

	@Override
	public State process(ReadyEvent event, EventRouter eventRouter) {
		return null;
	}

}
