package se.nackademin.server.io;

import se.nackademin.events.Event;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventRouter {

	private final BlockingQueue<Event> clientOneEventSink = new LinkedBlockingQueue<>();
	private final BlockingQueue<Event> clientTwoEventSink = new LinkedBlockingQueue<>();
	private final BlockingQueue<Event> clientEventSource = new LinkedBlockingQueue<>();

	public EventRouter(Socket socketOne, Socket socketTwo) {
		new Thread(new EventSender(socketOne, clientOneEventSink)).start();
		new Thread(new EventSender(socketTwo, clientTwoEventSink)).start();
		new Thread(new EventReceiver(socketOne, clientEventSource)).start();
		new Thread(new EventReceiver(socketTwo, clientEventSource)).start();
	}

	public Event getEvent() {
		try {
			return clientEventSource.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendEvent(Event event) {
		try {
			switch (event.getDestination()) {
				case 0 -> clientEventSource.put(event);
				case 1 -> clientOneEventSink.put(event);
				case 2 -> clientTwoEventSink.put(event);
				default -> throw new RuntimeException("Invalid destination in event: " + event);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
