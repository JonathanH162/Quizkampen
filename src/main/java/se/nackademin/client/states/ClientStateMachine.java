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

	private final BlockingQueue<Event> eventBlockingQueue = new LinkedBlockingQueue<>();
	private ClientState currentState;
	private final View view = new View(eventBlockingQueue);

	private static final Logger logger = LogManager.getLogger(ClientStateMachine.class);

	public ClientStateMachine(ClientState currentState) {
		this.currentState = currentState;
	}

	public void run() {
		logger.debug("StateMachine started.");
		try {
			eventBlockingQueue.put(new Event(EventType.INITIAL_EVENT, HostId.EMPTY, HostId.EMPTY, new Object()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			try {

				logger.debug("Current state: " + currentState.getClass());
				logger.debug("Looking for events..");
				var event = eventBlockingQueue.take();
				logger.debug("Event found: " + event.getEventType());

				currentState = currentState.transitionToNextState(event, view);

			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}

}

