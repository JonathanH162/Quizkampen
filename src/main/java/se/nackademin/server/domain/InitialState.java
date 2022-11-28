package se.nackademin.server.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.utils.ConfigProperties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InitialState implements ServerState {

	private final ServerSocket serverSocket;
	private static final Logger logger = LogManager.getLogger(InitialState.class);


	public InitialState() {
		try {
			var serverPort = new ConfigProperties().getServerPort();
			this.serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServerState transitionToNextState(Event event, EventRepository eventRepository) {
		switch (event.getEventType()) {
			case INITIAL_EVENT -> {
				new Thread(new ServerStateMachine(new ClientsConnectedState(), getClientSocket(), getClientSocket(),
						EventType.INITIAL_EVENT)).start();
				return this;
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

	private Socket getClientSocket() {
		try {
			Socket client = serverSocket.accept();
			logger.info("Client connected: " + client);
			return client;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
