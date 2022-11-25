package se.nackademin.io.eventmanagers;

import se.nackademin.io.*;
import se.nackademin.io.Event;
import se.nackademin.io.queues.SocketInputQueue;
import se.nackademin.io.queues.SocketOutputQueue;

import java.net.Socket;

public class ClientEventManager implements EventManager {

	private SocketOutputQueue<Event> socketOutputQueue;
	private SocketInputQueue socketInputQueue;
	private HostId clientId;

	public ClientEventManager() {
		this.socketInputQueue = new SocketInputQueue();
		new Thread(socketInputQueue).start();
	}

	public void connect(Socket socket) {
		socketOutputQueue = new SocketOutputQueue<>(socket);
		new Thread(socketOutputQueue).start();
		socketInputQueue.connect(socket);

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
			case SELF -> socketInputQueue.put(event);
			case SERVER -> socketOutputQueue.put(event);
		}
	}

}
