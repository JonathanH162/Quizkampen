package se.nackademin.client;

import se.nackademin.client.states.InitialState;
import se.nackademin.io.eventmanagers.ClientEventManager;
import se.nackademin.protocol.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private final int port = 1337;

	public static void main(String[] args) {
		new Client().run();
	}

	private void run() {
		System.out.println("Client started.");
		try (var socket = new Socket(InetAddress.getLocalHost().getHostAddress(), port)) {
			while (true) {

				System.out.println("Assigning IO streams.");
				var eventManager = new ClientEventManager(socket);

				System.out.println("Starting protocol thread.");
				var initialState = new InitialState(eventManager);
				new Thread(new Protocol(initialState)).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}