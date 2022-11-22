package se.nackademin.io.queues;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketInputQueue<T> implements Runnable {

	private final ObjectInputStream objectInputStream;
	private final BlockingQueue<T> receivedObjectsQueue;

	public SocketInputQueue(Socket socket) {
		try {
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			this.receivedObjectsQueue = new LinkedBlockingQueue<>();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public SocketInputQueue(Socket socket, BlockingQueue<T> receivedObjectsQueue) {
		try {
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			this.receivedObjectsQueue = receivedObjectsQueue;
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

	public void put(T e) {
		try {
			receivedObjectsQueue.put(e);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

	public BlockingQueue<T> getReceivedObjectsQueue() {
		return receivedObjectsQueue;
	}

	@Override
	@SuppressWarnings({"unchecked", "InfiniteLoopStatement"})
	public void run() {
		while (true) {
			try {
				receivedObjectsQueue.put((T) objectInputStream.readObject());
			} catch (InterruptedException | ClassNotFoundException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
