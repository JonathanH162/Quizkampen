package se.nackademin.client;

import se.nackademin.client.domain.ClientStateMachine;
import se.nackademin.client.domain.InitialState;

public class Client {

	public static void main(String[] args) {
		new Client().run();
	}

	private void run() {

		new ClientStateMachine(new InitialState()).run();
	}

}



