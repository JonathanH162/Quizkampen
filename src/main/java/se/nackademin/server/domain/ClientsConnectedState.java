package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;

public class ClientsConnectedState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, EventRepository eventRepository) {
		switch (event.getEventType()) {
			case INITIAL_EVENT-> {
				eventRepository.sendEvent(Event.toClient(EventType.TWO_PLAYERS_CONNECTED, HostId.CLIENT_ONE, HostId.CLIENT_ONE));
				eventRepository.sendEvent(Event.toClient(EventType.TWO_PLAYERS_CONNECTED, HostId.CLIENT_TWO, HostId.CLIENT_TWO));
				return new NewRoundButtonPressedState();
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}
}
