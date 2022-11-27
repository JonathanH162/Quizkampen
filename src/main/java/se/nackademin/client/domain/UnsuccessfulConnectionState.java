package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;

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
