package se.nackademin.server;

import se.nackademin.io.eventrouters.ServerEventRouter;
import se.nackademin.protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server() {
		System.out.println("Server started.");
		try (var serverSocket = new ServerSocket(1337)) {
			while (true) {
				Socket clientOne = serverSocket.accept();
				System.out.println(clientOne + " has connected.");

				Socket clientTwo = serverSocket.accept();
				System.out.println(clientTwo + " has connected.");

				System.out.println("Assigning IO streams.");
				var eventRouter = new ServerEventRouter(clientOne, clientTwo);

				System.out.println("Assigning client ID's");

				System.out.println("Starting protocol thread.");
				new Thread(new Protocol(eventRouter)).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		new Server();
	}

}