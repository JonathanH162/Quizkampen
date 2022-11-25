package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.HostId;
import se.nackademin.io.eventmanagers.ServerEventManager;

public class ClientsConnectedState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, ServerEventManager eventManager) {
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
