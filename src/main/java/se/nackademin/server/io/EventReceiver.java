package se.nackademin.server.io;

import se.nackademin.events.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class EventReceiver implements Runnable{
	private final ObjectInputStream objectInputStream;
	private final BlockingQueue<Event> eventQueue;

	public EventReceiver(Socket socket, BlockingQueue<Event> eventQueue) {
		this.eventQueue = eventQueue;
		try {
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				eventQueue.put((Event) objectInputStream.readObject());
			} catch (InterruptedException | ClassNotFoundException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
