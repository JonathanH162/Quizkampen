package se.nackademin.server.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.datasources.SharedSocketInputQueue;
import se.nackademin.core.repositories.eventrepository.datasources.SocketOutputQueue;

import java.net.Socket;

public class ServerEventRepository implements EventRepository {

	private SocketOutputQueue clientOneSocketOutputQueue;
	private SocketOutputQueue clientTwoSocketOutputQueue;
	private final SharedSocketInputQueue clientSharedSocketInputQueue = new SharedSocketInputQueue();
	private static final Logger logger = LogManager.getLogger(ServerEventRepository.class);


	public void connect(Socket clientOne, Socket clientTwo) {
		clientOneSocketOutputQueue = new SocketOutputQueue(clientOne);
		clientTwoSocketOutputQueue = new SocketOutputQueue(clientTwo);
		new Thread(clientOneSocketOutputQueue).start();
		new Thread(clientTwoSocketOutputQueue).start();
		clientSharedSocketInputQueue.connect(clientOne, clientTwo);
	}

	public Event get() {
		return clientSharedSocketInputQueue.take();
	}

	public void add(Event event) {
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
