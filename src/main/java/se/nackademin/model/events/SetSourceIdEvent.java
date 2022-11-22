package se.nackademin.model.events;

import se.nackademin.io.Destination;
import se.nackademin.io.eventrouters.EventRouter;
import se.nackademin.model.State;

public class SetSourceIdEvent extends Event {

	public SetSourceIdEvent(Destination destination, Object data) {
		super(destination, data);
	}

	@Override
	public State processMe(State state, EventRouter eventRouter) {
		return state.process(this, eventRouter);
	}

}
