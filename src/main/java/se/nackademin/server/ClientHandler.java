package se.nackademin.server;

public class ClientHandler implements Runnable {
private final ClientConnection clientConnectionOne;
private final ClientConnection clientConnectionTwo;
    public ClientHandler(ClientConnection clientConnectionOne, ClientConnection clientConnectionTwo) {
        this.clientConnectionOne = clientConnectionOne;
        this.clientConnectionTwo = clientConnectionTwo;
    }

    @Override
    public void run() {
    }
}
