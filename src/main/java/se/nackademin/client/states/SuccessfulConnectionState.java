package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;

public class SuccessfulConnectionState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view) {
		return null;
	}

	@Override
	public void transitionToNextState() {
		try {
			var event = eventQueue.take();
			if (event.getEventType().equals(EventType.TWO_PLAYER_CONNECTED)) {
				System.out.println("Anslutningen lyckades");
				new LobbyState(view,eventQueue).transitionToNextState();

			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}