package se.nackademin.repositories.eventrepository;

import se.nackademin.repositories.eventrepository.models.Event;


public interface EventRepository {

	Event getEvent();

	void sendEvent(Event event);

}
