package se.nackademin.server;

import se.nackademin.server.serverstatemachine.InitialState;
import se.nackademin.server.serverstatemachine.ServerStateMachine;

public class Server {

	public static void main(String[] args) {
		new ServerStateMachine(new InitialState()).run();
	}
}