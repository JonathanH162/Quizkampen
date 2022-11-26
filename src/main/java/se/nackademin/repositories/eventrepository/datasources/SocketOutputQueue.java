package se.nackademin.repositories.eventrepository.datasources;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketOutputQueue<T> implements Runnable {

	private final ObjectOutputStream objectOutputStream;
	private final BlockingQueue<T> objectsToSendQueue = new LinkedBlockingQueue<>();

	public SocketOutputQueue(Socket socket) {
		try {
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void put(T e) {
		try {
			objectsToSendQueue.put(e);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	@SuppressWarnings({"InfiniteLoopStatement"})
	public void run() {
		while (true) {
			try {
				objectOutputStream.writeObject(objectsToSendQueue.take());
			} catch (IOException | InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
