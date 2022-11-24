package se.nackademin.client.states;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.HostId;
import se.nackademin.io.eventmanagers.ClientEventManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientStateMachine {

	private ClientState currentState;
	private final ClientEventManager clientEventManager = new ClientEventManager();
	private final View view = new View(clientEventManager);

	private static final Logger logger = LogManager.getLogger(ClientStateMachine.class);

	public ClientStateMachine(ClientState currentState) {
		this.currentState = currentState;
	}

	public void run() {
		logger.debug("StateMachine started.");
			clientEventManager.sendEvent(Event.toSelf(EventType.INITIAL_EVENT));

		while (true) {

			logger.debug("Current state: " + currentState.getClass());
			logger.debug("Looking for events..");
			var event = clientEventManager.getNextEvent();
			logger.debug("Event found: " + event.getEventType());

			currentState = currentState.transitionToNextState(event, view, clientEventManager);
		}
	}

}

