package se.nackademin.server.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.core.EventLog;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.server.data.ServerEventRepository;

import java.net.Socket;

public class ServerStateMachine implements Runnable{
	private final EventLog eventLog = new EventLog();
	private ServerState currentState;
	private final ServerEventRepository eventRepository = new ServerEventRepository();
	private Event lastEvent = Event.empty();

	private static final Logger logger = LogManager.getLogger(ServerStateMachine.class);

	public ServerStateMachine(ServerState currentState, Socket clientOne, Socket clientTwo, EventType eventType) {
		this.currentState = currentState;
		eventRepository.connect(clientOne,clientTwo);
		eventRepository.add(Event.toSelf(eventType));
	}

	public void run() {
		logger.info("ServerStateMachine started.");
		while (true) {

			logger.info("Current state: " + currentState.getClass());
			var event = eventRepository.get();
			logger.info("Event found: " + event.getEventType());

			if (eventShouldBeHandled(event)){
				lastEvent = event;
				eventLog.log(event);
				currentState = currentState.transitionToNextState(event, eventRepository, eventLog);
			}

		}
	}

	private boolean eventShouldBeHandled(Event event){
		// Returns true only if incoming event is not the same as last event.
		return !event.equals(lastEvent);
	}


}
