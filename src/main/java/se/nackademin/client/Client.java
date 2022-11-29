package se.nackademin.client;

import se.nackademin.client.domain.ClientStateMachine;
import se.nackademin.client.domain.WelcomeScreenState;

public class Client implements Runnable{

	public static void main(String[] args) {
		new Client().run();
	}

	public void run() {
		new ClientStateMachine(new WelcomeScreenState()).run();
	}

}



