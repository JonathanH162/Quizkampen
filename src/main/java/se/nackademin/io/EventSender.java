package se.nackademin.io;

import se.nackademin.model.events.Event;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class EventSender implements Runnable {
	private final ObjectOutputStream objectOutputStream;
	private final BlockingQueue<Event> eventsToSendQueue;

	public EventSender(Socket socket, BlockingQueue<Event> eventsToSendQueue) {
		try {
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.eventsToSendQueue = eventsToSendQueue;
	}

	@Override
	public void run() {
		try {
			objectOutputStream.writeObject(eventsToSendQueue.take());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
