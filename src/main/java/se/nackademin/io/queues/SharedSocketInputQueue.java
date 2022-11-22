package se.nackademin.io.queues;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SharedSocketInputQueue<T> {

	private final BlockingQueue<T> sharedQueue = new LinkedBlockingQueue<>();

	public SharedSocketInputQueue(Socket socketOne, Socket socketTwo) {
		new Thread(new SocketInputQueue<T>(socketOne, sharedQueue)).start();
		new Thread(new SocketInputQueue<T>(socketTwo, sharedQueue)).start();
	}

	public T take() {
		try {
			return sharedQueue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(T e) {
		try {
			sharedQueue.put(e);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
