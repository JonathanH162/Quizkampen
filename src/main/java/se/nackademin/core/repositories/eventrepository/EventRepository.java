package se.nackademin.core.repositories.eventrepository;

import se.nackademin.core.repositories.eventrepository.models.Event;


public interface EventRepository {

	Event getEvent();

	void sendEvent(Event event);

}
