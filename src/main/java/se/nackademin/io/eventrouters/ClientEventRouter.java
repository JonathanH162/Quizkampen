package se.nackademin.io.eventrouters;

import se.nackademin.model.events.Event;
import se.nackademin.io.EventReceiver;
import se.nackademin.io.EventSender;
import se.nackademin.io.Source;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientEventRouter implements EventRouter {

	private final BlockingQueue<Event> outboundEvents = new LinkedBlockingQueue<>();
	private final BlockingQueue<Event> inboundEvents = new LinkedBlockingQueue<>();
	private Source clientId;

	public ClientEventRouter(Socket socket) {
		new Thread(new EventSender(socket, outboundEvents)).start();
		new Thread(new EventReceiver(socket, inboundEvents)).start();
	}

	public void setSourceId(Source sourceId) {
		this.clientId = sourceId;
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
			event.setSource(clientId);
			switch (event.getDestination()) {
				case SELF -> inboundEvents.put(event);
				case SERVER -> outboundEvents.put(event);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
