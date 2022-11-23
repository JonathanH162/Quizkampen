package se.nackademin.io.eventmanagers;

import se.nackademin.io.Event;
import se.nackademin.io.HostId;

public interface EventManager {

	void setSourceId(HostId hostIdId);

	Event getNextEvent();

	void sendEvent(Event event);

}
