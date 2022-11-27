package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;

public class SendingCategorySelectedState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
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
	}*/

}
