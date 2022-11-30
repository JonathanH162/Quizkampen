package se.nackademin.server.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.datasources.SharedSocketInputQueue;
import se.nackademin.core.repositories.eventrepository.datasources.SocketOutputQueue;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerEventRepository implements EventRepository {

	private SocketOutputQueue clientOneSocketOutputQueue;
	private SocketOutputQueue clientTwoSocketOutputQueue;
	private final SharedSocketInputQueue clientSharedSocketInputQueue = new SharedSocketInputQueue();
	private static final Logger logger = LogManager.getLogger(ServerEventRepository.class);

	@Override
	public HostId getHostId() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void setSourceId(HostId data) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void connect(Socket socket) {
		throw new RuntimeException("Not implemented");
	}

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
		if (event.getDestination().equals(HostId.BOTH_CLIENTS)) {
			splitEventIntoTwo(event).forEach(this::prepareAndSend);
		} else {
			prepareAndSend(event);
		}
	}

	private void prepareAndSend(Event event) {
		event.setSource(HostId.SERVER);
		setHostIdIfEmpty(event);
		putIntoQueueForSending(event);
	}

	private void putIntoQueueForSending(Event event) {
		switch (event.getDestination()) {
			case SELF -> clientSharedSocketInputQueue.put(event);
			case CLIENT_ONE -> clientOneSocketOutputQueue.put(event);
			case CLIENT_TWO -> clientTwoSocketOutputQueue.put(event);
		}
	}

	private void setHostIdIfEmpty(Event event) {
		if (event.getData().equals(HostId.EMPTY)) {
			event.setData(event.getDestination());
		}
	}

	private List<Event> splitEventIntoTwo(Event event) {
		List<Event> eventList = new ArrayList<>();
		eventList.add(Event.toClient(event.getEventType(),HostId.CLIENT_ONE,event.getData()));
		eventList.add(Event.toClient(event.getEventType(),HostId.CLIENT_TWO,event.getData()));
		return eventList;
	}

}
