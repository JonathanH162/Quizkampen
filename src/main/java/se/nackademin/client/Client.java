package se.nackademin.client;

import se.nackademin.io.eventmanagers.ClientEventManager;
import se.nackademin.protocol.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private final int port = 1337;

	public Client() {
		System.out.println("Client started.");
		try (var socket = new Socket(InetAddress.getLocalHost(), port)) {
			while (true) {

				System.out.println("Assigning IO streams.");
				var eventRouter = new ClientEventManager(socket);

				System.out.println("Starting protocol thread.");
				new Thread(new Protocol(eventRouter)).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		new Client();
	}

}