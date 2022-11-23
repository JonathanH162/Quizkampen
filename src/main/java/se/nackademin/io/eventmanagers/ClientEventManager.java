package se.nackademin.io.eventmanagers;

import se.nackademin.io.*;
import se.nackademin.io.Event;
import se.nackademin.io.queues.SocketInputQueue;
import se.nackademin.io.queues.SocketOutputQueue;

import java.net.Socket;

public class ClientEventManager implements EventManager {

	private final SocketOutputQueue<Event> socketOutputQueue;
	private final SocketInputQueue<Event> socketInputQueue;
	private HostId clientId;

	public ClientEventManager(Socket socket) {
		this.socketOutputQueue = new SocketOutputQueue<>(socket);
		this.socketInputQueue = new SocketInputQueue<>(socket);
		new Thread(socketOutputQueue).start();
		new Thread(socketInputQueue).start();
	}

	public void setSourceId(HostId hostIdId) {
		this.clientId = hostIdId;
	}

	public Event getNextEvent() {
		return socketInputQueue.take();
	}

	public void sendEvent(Event event) {
		event.setSource(clientId);
		switch (event.getDestination()) {
			case CLIENT_ONE, CLIENT_TWO -> socketInputQueue.put(event);
			case SERVER -> socketOutputQueue.put(event);
		}
	}

}
