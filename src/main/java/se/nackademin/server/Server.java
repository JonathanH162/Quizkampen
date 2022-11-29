package se.nackademin.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.utils.ConfigProperties;
import se.nackademin.server.domain.ServerGame;
import se.nackademin.server.domain.ServerStateMachine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	private final ServerSocket serverSocket;
	private static final Logger logger = LogManager.getLogger(Server.class);


	public Server() {
		var serverPort = new ConfigProperties().getServerPort();
		try {
			this.serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		new Server().run();

	}

	public void run() {
		while (true) {
			new Thread(new ServerStateMachine(new ServerGame(), getClientSocket(), getClientSocket(), EventType.START_ROUND)).start();
		}
	}

}