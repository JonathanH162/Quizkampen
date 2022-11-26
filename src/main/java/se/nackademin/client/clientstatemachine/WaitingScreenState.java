package se.nackademin.client.clientstatemachine;

import se.nackademin.client.view.View;
import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.models.EventType;
import se.nackademin.repositories.eventrepository.ClientEventRepository;

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
