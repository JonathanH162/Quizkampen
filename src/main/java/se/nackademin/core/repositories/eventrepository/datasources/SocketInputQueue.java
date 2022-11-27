package se.nackademin.core.repositories.eventrepository.datasources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketInputQueue<T> implements Runnable {

    private ObjectInputStream objectInputStream;
    private BlockingQueue<T> receivedObjectsQueue;
    private boolean connected = false;

    private static final Logger logger = LogManager.getLogger(SocketInputQueue.class);


    public SocketInputQueue() {
        this.receivedObjectsQueue = new LinkedBlockingQueue<>();
    }

    public SocketInputQueue(Socket socket, BlockingQueue<T> receivedObjectsQueue) {
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

    public T take() {
        try {
            return receivedObjectsQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("receivedObjectsQueue.take() throw InterruptedException: {}", e.getMessage());
            return null;
        }
    }

    public void put(T object) {
        try {
            receivedObjectsQueue.put(object);
        } catch (InterruptedException e) {
            logger.error("receivedObjectsQueue.put({}) goes wrong: {}", object, e.getMessage());
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "InfiniteLoopStatement"})
    public void run() {
        if (connected) {
            while (true) {
                try {
                    receivedObjectsQueue.put((T) objectInputStream.readObject());
                } catch (InterruptedException | ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
