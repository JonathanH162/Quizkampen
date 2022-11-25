package se.nackademin.server.states;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.client.states.ClientStateMachine;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.ServerEventManager;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerStateMachine implements Runnable{

	private ServerState currentState;
	private ServerEventManager serverEventManager = new ServerEventManager();

	private static final Logger logger = LogManager.getLogger(ServerStateMachine.class);

	public ServerStateMachine(ServerState currentState) {
		this.currentState = currentState;
		serverEventManager.sendEvent(Event.toSelf(EventType.INITIAL_EVENT));
	}

	public ServerStateMachine(ServerState currentState, Socket clientOne, Socket clientTwo, EventType eventType) {
		this.currentState = currentState;
		this.serverEventManager.connect(clientOne,clientTwo);
		serverEventManager.sendEvent(Event.toSelf(eventType));
	}

	public void run() {
		logger.info("ServerStateMachine started.");
		while (true) {

			logger.info("Current state: " + currentState.getClass());
			logger.info("Looking for events..");
			var event = serverEventManager.getNextEvent();
			logger.info("Event found: " + event.getEventType());

			currentState = currentState.transitionToNextState(event, serverEventManager);
		}
	}


}
