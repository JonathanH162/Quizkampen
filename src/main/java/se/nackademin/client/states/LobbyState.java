package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

import java.util.concurrent.BlockingQueue;

public class LobbyState extends State {
	public LobbyState(View view, BlockingQueue<Event> eventQueue) {
		super(view, eventQueue);
	}

	@Override
	public void setEventManager(EventManager eventManager) {

	}

	@Override
	public void transitionToNextState() {
		try {
			var event = eventQueue.take();

			//			if (event.getEventType().equals(EventType.CONNECTION_SUCCESS)) {
			//				System.out.println("sldkfjslkdjfdlksj");
			//				new SuccessfulConnectionState(view,eventQueue).transitionToNextState();
			//			}
			//			else if (event.getEventType().equals(EventType.CONNECTION_FAILED)) {
			//				System.out.println("FAIL");
			//				new UnsuccessfulConnectionState(view,eventQueue).transitionToNextState();
			//			}

			throw new RuntimeException("Unexpected event: " + event);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
