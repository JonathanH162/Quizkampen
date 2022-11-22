package se.nackademin.io.eventmanagers;

import se.nackademin.io.HostId;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.queues.SharedSocketInputQueue;
import se.nackademin.io.queues.SocketOutputQueue;

import java.net.Socket;

public class ServerEventManager implements EventManager {

	private final SocketOutputQueue<Event> clientOneSocketOutputQueue;
	private final SocketOutputQueue<Event> clientTwoSocketOutputQueue;
	private final SharedSocketInputQueue<Event> clientSharedSocketInputQueue;

	private HostId hostId = HostId.SERVER;

	public ServerEventManager(Socket socketOne, Socket socketTwo) {
		this.clientOneSocketOutputQueue = new SocketOutputQueue<>(socketOne);
		this.clientTwoSocketOutputQueue = new SocketOutputQueue<>(socketTwo);
		new Thread(clientOneSocketOutputQueue).start();
		new Thread(clientTwoSocketOutputQueue).start();

		this.clientSharedSocketInputQueue = new SharedSocketInputQueue<>(socketOne,socketTwo);

		sendEvent(Event.toClientOne(EventType.NEW_ID, HostId.CLIENT_ONE));
		sendEvent(Event.toClientTwo(EventType.NEW_ID, HostId.CLIENT_TWO));
	}

	public void setSourceId(HostId hostIdId) {
		this.hostId = hostIdId;
	}

	public Event getEvent() {
		return clientSharedSocketInputQueue.take();
	}

	public void sendEvent(Event event) {
		event.setSource(hostId);
		putIntoQueueForSending(event);
	}

	private void putIntoQueueForSending(Event event) {
		switch (event.getDestination()) {
			case SERVER -> clientSharedSocketInputQueue.put(event);
			case CLIENT_ONE -> clientOneSocketOutputQueue.put(event);
			case CLIENT_TWO -> clientTwoSocketOutputQueue.put(event);
		}
	}

}
