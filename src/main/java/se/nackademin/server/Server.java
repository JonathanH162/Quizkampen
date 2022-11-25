package se.nackademin.server;

import se.nackademin.server.states.InitialState;
import se.nackademin.server.states.ServerStateMachine;

public class Server {

	public static void main(String[] args) {
		new ServerStateMachine(new InitialState()).run();
	}
}