package se.nackademin.client;

import se.nackademin.io.eventrouters.ClientEventRouter;
import se.nackademin.io.eventrouters.ServerEventRouter;
import se.nackademin.protocol.Protocol;
import se.nackademin.server.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private final int port = 1337;

	public Client() {
		System.out.println("Client started.");
		try (var socket = new Socket(InetAddress.getLocalHost(), port)) {
			while (true) {

				System.out.println("Assigning IO streams.");
				var eventRouter = new ClientEventRouter(socket);

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