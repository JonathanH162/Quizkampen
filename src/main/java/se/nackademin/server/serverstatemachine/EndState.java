package se.nackademin.server.serverstatemachine;

import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.ServerEventRepository;

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
