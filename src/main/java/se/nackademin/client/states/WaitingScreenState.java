package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.ClientEventManager;

public class WaitingScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager) {
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
