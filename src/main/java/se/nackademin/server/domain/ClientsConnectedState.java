package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.server.data.ServerEventRepository;

public class ClientsConnectedState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, ServerEventRepository eventManager) {
		switch (event.getEventType()) {
			case INITIAL_EVENT-> {
				eventManager.sendEvent(new Event(EventType.TWO_PLAYERS_CONNECTED, HostId.SERVER, HostId.CLIENT_ONE, HostId.CLIENT_ONE));
				eventManager.sendEvent(new Event(EventType.TWO_PLAYERS_CONNECTED, HostId.SERVER, HostId.CLIENT_TWO, HostId.CLIENT_TWO));
				return new NewRoundButtonPressedState();
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}
}
