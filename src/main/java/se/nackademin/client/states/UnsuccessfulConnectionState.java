package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ClientEventManager;

public class UnsuccessfulConnectionState implements ClientState {


	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager) {
		//view.getWelcomeLabel().setText("FAIL");
		view.unsuccessfulConnectionScreen();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("thread dead");
		}
		return new InitialState();
	}

}
