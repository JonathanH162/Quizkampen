package se.nackademin.io.eventrouters;

import se.nackademin.model.events.Event;
import se.nackademin.io.Source;

public interface EventRouter {

	void setSourceId(Source sourceId);

	Event getEvent();

	void sendEvent(Event event);

}
