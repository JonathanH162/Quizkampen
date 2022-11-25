package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.ServerEventManager;

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
	public ServerState transitionToNextState(Event event, ServerEventManager eventManager) {
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
