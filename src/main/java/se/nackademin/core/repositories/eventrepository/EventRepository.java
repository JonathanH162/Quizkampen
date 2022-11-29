package se.nackademin.core.repositories.eventrepository;

import se.nackademin.core.repositories.eventrepository.models.Event;


public interface EventRepository {

	Event get();

	void add(Event event);

}
