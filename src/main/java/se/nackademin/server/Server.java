package se.nackademin.server;

import se.nackademin.server.io.EventRouter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;

	public Server() throws IOException {
		serverSocket = new ServerSocket(1337);
		System.out.println("Server started.");
		try {
			while (true) {
				Socket clientOne = serverSocket.accept();
				System.out.println(clientOne + " has connected.");

				Socket clientTwo = serverSocket.accept();
				System.out.println(clientTwo + " has connected.");

				System.out.println("Assigning IO streams.");
				var eventRouter = new EventRouter(clientOne, clientTwo);

				System.out.println("Starting client-handler thread.");
				new Thread(new Protocol(eventRouter)).start();
			}
		} finally {
			serverSocket.close();
		}
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}

}