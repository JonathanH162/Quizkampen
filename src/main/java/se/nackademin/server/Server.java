package se.nackademin.server;

import se.nackademin.server.states.InitialState;
import se.nackademin.io.eventmanagers.ServerEventManager;
import se.nackademin.server.states.ServerStateMachine;
//import se.nackademin.protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		new Server().run();
	}

	private void run() {

		new ServerStateMachine(new InitialState()).run();

	}

}