package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

import java.util.concurrent.BlockingQueue;

public class SuccessfulConnectionState extends State {

	public SuccessfulConnectionState(View view, BlockingQueue<Event> eventQueue) {
		super(view, eventQueue);
	}

	@Override
	public void setEventManager(EventManager eventManager) {

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