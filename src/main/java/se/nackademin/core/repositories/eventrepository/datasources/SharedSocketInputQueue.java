package se.nackademin.core.repositories.eventrepository.datasources;

import se.nackademin.core.repositories.eventrepository.models.Event;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SharedSocketInputQueue {

	private final BlockingQueue<Event> sharedQueue = new LinkedBlockingQueue<>();
	SocketInputQueue socketInputQueueOne = new SocketInputQueue(sharedQueue);
	SocketInputQueue socketInputQueueTwo = new SocketInputQueue(sharedQueue);

	public SharedSocketInputQueue() {
		new Thread(socketInputQueueOne).start();
		new Thread(socketInputQueueTwo).start();
	}

	public void connect(Socket socketOne, Socket socketTwo){
		socketInputQueueOne.connect(socketOne);
		socketInputQueueTwo.connect(socketTwo);
	}

	public Event take() {
		try {
			return sharedQueue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(Event object) {
		try {
			sharedQueue.put(object);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
