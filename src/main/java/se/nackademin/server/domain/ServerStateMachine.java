package se.nackademin.server.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.server.data.ServerEventRepository;

import java.net.Socket;

public class ServerStateMachine implements Runnable{

	private ServerState currentState;
	private final ServerEventRepository eventRepository = new ServerEventRepository();

	private static final Logger logger = LogManager.getLogger(ServerStateMachine.class);

	public ServerStateMachine(ServerState currentState) {
		this.currentState = currentState;
		eventRepository.sendEvent(Event.toSelf(EventType.INITIAL_EVENT));
	}

	public ServerStateMachine(ServerState currentState, Socket clientOne, Socket clientTwo, EventType eventType) {
		this.currentState = currentState;
		this.eventRepository.connect(clientOne,clientTwo);
		eventRepository.sendEvent(Event.toSelf(eventType));
	}

	public void run() {
		logger.info("ServerStateMachine started.");
		while (true) {

			logger.info("Current state: " + currentState.getClass());
			logger.info("Looking for events..");
			var event = eventRepository.getEvent();
			logger.info("Event found: " + event.getEventType());

			currentState = currentState.transitionToNextState(event, eventRepository);
		}
	}


}
