package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.server.data.ServerEventRepository;

public class EndState implements ServerState {
	@Override
	public ServerState transitionToNextState(Event event, ServerEventRepository eventManager) {
		return null;
	}

/*	@Override
	public ServerState transitionToNextState(Event event) {
		System.out.println("Endstate reached by server.");
		Thread.currentThread().interrupt();
		return null;
	}*/

}
