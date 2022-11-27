package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;

public class WaitingScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {

		switch (event.getEventType()) {
			case CONNECTION_SUCCESS -> {
				view.getWelcomeLabel().setText("Connection Successful");
				return new SuccessfulConnectionState();
			}
			case CONNECTION_FAILED -> {
				view.getWelcomeLabel().setText("Connection Failed");
				sleepFiveSeconds();
				return new InitialState();
			}
		}
		throw new RuntimeException("Unexpected event: " + event.getEventType());
	}

	private void sleepFiveSeconds() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}

}
