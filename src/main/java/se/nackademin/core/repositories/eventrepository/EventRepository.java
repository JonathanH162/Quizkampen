package se.nackademin.core.repositories.eventrepository;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.HostId;

import java.net.Socket;


public interface EventRepository {

	Event get();

	void add(Event event);

    void setSourceId(HostId data);

	HostId getHostId();

	void connect(Socket socket);
}
