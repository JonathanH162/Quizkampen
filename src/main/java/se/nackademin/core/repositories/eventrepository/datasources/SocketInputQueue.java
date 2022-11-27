package se.nackademin.core.repositories.eventrepository.datasources;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketInputQueue<T> implements Runnable {

	private ObjectInputStream objectInputStream;
	private final BlockingQueue<T> receivedObjectsQueue;
	private boolean connected = false;

	public SocketInputQueue() {
		this.receivedObjectsQueue = new LinkedBlockingQueue<>();
	}

	public SocketInputQueue(BlockingQueue<T> receivedObjectsQueue) {
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

	public T take() {
		try {
			return receivedObjectsQueue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(T object) {
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
					T receivedObject = (T) objectInputStream.readObject();
					receivedObjectsQueue.put(receivedObject);
				}
			} catch (InterruptedException | ClassNotFoundException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
