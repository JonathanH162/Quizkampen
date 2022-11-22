package se.nackademin.model.events;

import se.nackademin.io.Destination;
import se.nackademin.io.eventrouters.EventRouter;
import se.nackademin.model.State;
import se.nackademin.io.Source;

public abstract class Event {

	private final Destination destination;
	private Source source;
	private final Object data;

	public Destination getDestination() {
		return destination;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Object getData() {
		return data;
	}

	protected Event(Destination destination, Object data) {
		this.destination = destination;
		this.source = Source.SELF;
		this.data = data;
	}

	public abstract State processMe(State state, EventRouter eventRouter);

}
