package se.nackademin.client;

import se.nackademin.client.states.ClientStateMachine;
import se.nackademin.client.states.InitialState;

public class Client {

	public static void main(String[] args) {
		new Client().run();
	}

	private void run() {

		new ClientStateMachine(new InitialState()).run();
	}

}



