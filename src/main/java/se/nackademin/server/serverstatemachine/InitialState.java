package se.nackademin.server.serverstatemachine;

import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.models.EventType;
import se.nackademin.repositories.eventrepository.ServerEventRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InitialState implements ServerState {

	private final ServerSocket serverSocket;

	public InitialState() {
		try {
			this.serverSocket = new ServerSocket(1337);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServerState transitionToNextState(Event event, ServerEventRepository eventManager) {
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
			System.out.println("Client connected: " + client);
			return client;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
