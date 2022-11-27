package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;

public class WaitingScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		//event = eventQueue.take();

		if (event.getEventType().equals(EventType.CONNECTION_SUCCESS)) {
			view.getWelcomeLabel().setText("Connection Successful");
			return new SuccessfulConnectionState();
			//new SuccessfulConnectionState(view,eventQueue).transitionToNextState();
		} else if (event.getEventType().equals(EventType.CONNECTION_FAILED)) {
			view.getWelcomeLabel().setText("Connection Failed");
			try {
				Thread.sleep(5000);
				return new InitialState();
			} catch (InterruptedException ie) {
				System.out.println("Thread error");
			}

			throw new RuntimeException("Unexpected event: " + event.getEventType());
		}
		return null;
	}

}
