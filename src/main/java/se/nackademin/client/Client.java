package se.nackademin.client;

import se.nackademin.client.clientstatemachine.ClientStateMachine;
import se.nackademin.client.clientstatemachine.InitialState;

public class Client {

	public static void main(String[] args) {
		new Client().run();
	}

	private void run() {

		new ClientStateMachine(new InitialState()).run();
	}

}



