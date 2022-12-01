package se.nackademin.core.repositories.eventrepository.datasources;

import se.nackademin.core.repositories.eventrepository.models.Event;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketOutputQueue implements Runnable {

	private final ObjectOutputStream objectOutputStream;
	private final BlockingQueue<Event> objectsToSendQueue = new LinkedBlockingQueue<>();

	public SocketOutputQueue(Socket socket) {
		try {
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(Event object) {
		try {
			objectsToSendQueue.put(object);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	@SuppressWarnings({"InfiniteLoopStatement"})
	public void run() {
		while (true) {
			try {
				Event objectToSend = objectsToSendQueue.take();
				objectOutputStream.writeObject(objectToSend);
				objectOutputStream.flush();
				objectOutputStream.reset();
			} catch (IOException | InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
