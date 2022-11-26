package se.nackademin.client.clientstatemachine;

import se.nackademin.client.view.View;
import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.ClientEventRepository;

public class UnsuccessfulConnectionState implements ClientState {


	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
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
