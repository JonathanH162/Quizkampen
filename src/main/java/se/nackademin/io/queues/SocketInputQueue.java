package se.nackademin.io.queues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.io.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketInputQueue implements Runnable {

    private ObjectInputStream objectInputStream;
    private BlockingQueue<Event> receivedObjectsQueue;
    private boolean connected = false;

    private static final Logger logger = LogManager.getLogger(SocketInputQueue.class);


    public SocketInputQueue() {
        this.receivedObjectsQueue = new LinkedBlockingQueue<>();
    }

    public SocketInputQueue(Socket socket, BlockingQueue<Event> receivedObjectsQueue) {
        try {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.receivedObjectsQueue = receivedObjectsQueue;
        } catch (IOException e) {
            logger.error("In creating SocketInputQueue occur some problem: {}", e.getMessage());
        }
    }

    public void connect(Socket socket) {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            connected = true;
        } catch (IOException e) {
            logger.error("Somthing goes wrong in connecting a socket: {}", e.getMessage());
        }
    }

    public Event take() {
        try {
            return receivedObjectsQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("receivedObjectsQueue.take() throw InterruptedException: {}", e.getMessage());
            return null;
        }
    }

    public void put(Event event) {
        try {
            receivedObjectsQueue.put(event);
        } catch (InterruptedException e) {
            logger.error("receivedObjectsQueue.put({}) goes wrong: {}", event, e.getMessage());
        }
    }

    public BlockingQueue<Event> getReceivedObjectsQueue() {
        return receivedObjectsQueue;
    }

    @Override
    @SuppressWarnings({"unchecked", "InfiniteLoopStatement"})
    public void run() {
        if (connected) {
            while (true) {
                try {
                    receivedObjectsQueue.put((Event) objectInputStream.readObject());
                } catch (InterruptedException | ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
