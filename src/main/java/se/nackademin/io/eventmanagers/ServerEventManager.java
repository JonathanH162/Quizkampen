package se.nackademin.io.eventmanagers;

import se.nackademin.io.HostId;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.queues.SharedSocketInputQueue;
import se.nackademin.io.queues.SocketInputQueue;
import se.nackademin.io.queues.SocketOutputQueue;

import java.net.Socket;

public class ServerEventManager implements EventManager {

	private SocketOutputQueue<Event> clientOneSocketOutputQueue;
	private SocketOutputQueue<Event> clientTwoSocketOutputQueue;
	private final SharedSocketInputQueue clientSharedSocketInputQueue = new SharedSocketInputQueue();
	private HostId hostId = HostId.SERVER;

	public void connect(Socket clientOne, Socket clientTwo) {
		clientOneSocketOutputQueue = new SocketOutputQueue<>(clientOne);
		new Thread(clientOneSocketOutputQueue).start();
		clientTwoSocketOutputQueue = new SocketOutputQueue<>(clientOne);
		new Thread(clientTwoSocketOutputQueue).start();
		clientSharedSocketInputQueue.connect(clientOne, clientTwo);
	}

	public void setSourceId(HostId hostIdId) {
		this.hostId = hostIdId;
	}

	public Event getNextEvent() {
		return clientSharedSocketInputQueue.take();
	}

	public void sendEvent(Event event) {
		event.setSource(hostId);
		putIntoQueueForSending(event);
	}

	private void putIntoQueueForSending(Event event) {
		switch (event.getDestination()) {
			case SELF -> clientSharedSocketInputQueue.put(event);
			case CLIENT_ONE -> clientOneSocketOutputQueue.put(event);
			case CLIENT_TWO -> clientTwoSocketOutputQueue.put(event);
		}
	}

}
