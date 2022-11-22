package se.nackademin.io.eventrouters;

import se.nackademin.model.events.Event;
import se.nackademin.model.events.SetSourceIdEvent;
import se.nackademin.io.Destination;
import se.nackademin.io.EventReceiver;
import se.nackademin.io.EventSender;
import se.nackademin.io.Source;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerEventRouter implements EventRouter {

	private final BlockingQueue<Event> eventsToClientOne = new LinkedBlockingQueue<>();
	private final BlockingQueue<Event> eventsToClientTwo = new LinkedBlockingQueue<>();
	private final BlockingQueue<Event> inboundEvents = new LinkedBlockingQueue<>();
	private Source sourceId = Source.SERVER;

	public ServerEventRouter(Socket socketOne, Socket socketTwo) {
		new Thread(new EventSender(socketOne, eventsToClientOne)).start();
		new Thread(new EventSender(socketTwo, eventsToClientTwo)).start();
		new Thread(new EventReceiver(socketOne, inboundEvents)).start();
		new Thread(new EventReceiver(socketTwo, inboundEvents)).start();

		// Let the clients know if they are client 1 or client 2.
		sendEvent(new SetSourceIdEvent(Destination.CLIENT_ONE,Source.CLIENT_ONE));
		sendEvent(new SetSourceIdEvent(Destination.CLIENT_TWO,Source.CLIENT_TWO));
	}

	public void setSourceId(Source sourceId) {
		this.sourceId = sourceId;
	}

	public Event getEvent() {
		try {
			return inboundEvents.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendEvent(Event event) {
		try {
			event.setSource(sourceId);
			switch (event.getDestination()) {
				case SELF -> inboundEvents.put(event);
				case CLIENT_ONE -> eventsToClientOne.put(event);
				case CLIENT_TWO -> eventsToClientTwo.put(event);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
