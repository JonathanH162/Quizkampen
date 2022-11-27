package se.nackademin.core.repositories.eventrepository.datasources;

import se.nackademin.core.repositories.eventrepository.models.Event;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SharedSocketInputQueue {

	private final BlockingQueue<Event> sharedQueue = new LinkedBlockingQueue<>();

	public void connect(Socket socketOne, Socket socketTwo){
		new Thread(new SocketInputQueue(socketOne, sharedQueue)).start();
		new Thread(new SocketInputQueue(socketTwo, sharedQueue)).start();
	}

	public Event take() {
		try {
			return sharedQueue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(Event e) {
		try {
			sharedQueue.put(e);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
