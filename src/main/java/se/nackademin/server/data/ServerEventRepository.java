package se.nackademin.server.data;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.datasources.SharedSocketInputQueue;
import se.nackademin.core.repositories.eventrepository.datasources.SocketOutputQueue;

import java.net.Socket;

public class ServerEventRepository implements EventRepository {

	private SocketOutputQueue<Event> clientOneSocketOutputQueue;
	private SocketOutputQueue<Event> clientTwoSocketOutputQueue;
	private final SharedSocketInputQueue<Event> clientSharedSocketInputQueue = new SharedSocketInputQueue<>();

	public void connect(Socket clientOne, Socket clientTwo) {
		clientOneSocketOutputQueue = new SocketOutputQueue<>(clientOne);
		new Thread(clientOneSocketOutputQueue).start();
		clientTwoSocketOutputQueue = new SocketOutputQueue<>(clientOne);
		new Thread(clientTwoSocketOutputQueue).start();
		clientSharedSocketInputQueue.connect(clientOne, clientTwo);
	}

	public Event getEvent() {
		return clientSharedSocketInputQueue.take();
	}

	public void sendEvent(Event event) {
		event.setSource(HostId.SERVER);
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
