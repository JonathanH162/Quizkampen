package se.nackademin.server;

import se.nackademin.server.states.InitialState;
import se.nackademin.io.eventmanagers.ServerEventManager;
import se.nackademin.protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		new Server().run();
	}

	private void run() {
		System.out.println("Server started.");
		try (var serverSocket = new ServerSocket(1337)) {
			while (true) {
				Socket clientOne = serverSocket.accept();
				System.out.println(clientOne + " has connected.");

				Socket clientTwo = serverSocket.accept();
				System.out.println(clientTwo + " has connected.");

				System.out.println("Assigning IO streams.");
				var eventManager = new ServerEventManager(clientOne, clientTwo);

				System.out.println("Starting protocol thread.");
				var initialState = new InitialState(eventManager);
				new Thread(new Protocol(initialState)).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}