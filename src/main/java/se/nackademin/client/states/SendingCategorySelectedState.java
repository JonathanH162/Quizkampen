package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;

public class SendingCategorySelectedState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view) {
		return null;
	}
/*	public void transitionToNextState() {
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
