package se.nackademin.client.states;

import se.nackademin.io.Event;
import se.nackademin.client.view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InitialState implements ClientState {


	@Override
	public void transitionToNextState() {
		BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
		var view = new View(eventQueue);

		new WelcomeScreenState(view,eventQueue).transitionToNextState();
	}






/*	@Override
	public State transitionToNextState() {
		var event = eventManager.getNextEvent();
		switch (event.getEventType()) {
			case NEW_ID -> {
				System.out.println("New ID received by client.");

				var newId = (HostId) event.getData();
				eventManager.setSourceId(newId);

				return new ReceivedIdState(eventManager);
			}
			default -> throw new RuntimeException("Unexpected event: " + event);
		}
	}*/

}
