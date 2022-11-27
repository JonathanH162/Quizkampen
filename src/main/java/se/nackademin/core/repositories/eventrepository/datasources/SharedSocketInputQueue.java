package se.nackademin.core.repositories.eventrepository.datasources;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SharedSocketInputQueue<T> {

	private final BlockingQueue<T> sharedQueue = new LinkedBlockingQueue<>();
	SocketInputQueue<T> socketInputQueueOne = new SocketInputQueue<>(sharedQueue);
	SocketInputQueue<T> socketInputQueueTwo = new SocketInputQueue<>(sharedQueue);

	public SharedSocketInputQueue() {
		new Thread(socketInputQueueOne).start();
		new Thread(socketInputQueueTwo).start();
	}

	public void connect(Socket socketOne, Socket socketTwo){
		socketInputQueueOne.connect(socketOne);
		socketInputQueueTwo.connect(socketTwo);
	}

	public T take() {
		try {
			return sharedQueue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(T object) {
		try {
			sharedQueue.put(object);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
