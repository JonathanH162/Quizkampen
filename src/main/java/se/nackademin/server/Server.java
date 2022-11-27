package se.nackademin.server;

import se.nackademin.server.domain.InitialState;
import se.nackademin.server.domain.ServerStateMachine;

public class Server {

	public static void main(String[] args) {
		new ServerStateMachine(new InitialState()).run();
	}

}