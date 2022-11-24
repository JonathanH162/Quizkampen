package se.nackademin.client.states;

import se.nackademin.io.Event;
import se.nackademin.client.view.View;


public class InitialState implements ClientState {


	@Override
	public ClientState transitionToNextState(Event event, View view) {
		return null;
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
