package se.nackademin.core.repositories.eventrepository.datasources;

import se.nackademin.core.repositories.eventrepository.models.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketInputQueue implements Runnable {

	private ObjectInputStream objectInputStream;
	private final BlockingQueue<Event> receivedObjectsQueue;
	private boolean connected = false;

	public SocketInputQueue() {
		this.receivedObjectsQueue = new LinkedBlockingQueue<>();
	}

	public SocketInputQueue(BlockingQueue<Event> receivedObjectsQueue) {
		this.receivedObjectsQueue = receivedObjectsQueue;
	}

	public void connect(Socket socket) {
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			connected = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Event take() {
		try {
			return receivedObjectsQueue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(Event object) {
		try {
			receivedObjectsQueue.put(object);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@SuppressWarnings({"unchecked", "InfiniteLoopStatement"})
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				if (connected) {
					synchronized (objectInputStream) {
						Event receivedObject = (Event) objectInputStream.readObject();
						receivedObjectsQueue.put(receivedObject);
					}

				}
			} catch (InterruptedException | ClassNotFoundException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
