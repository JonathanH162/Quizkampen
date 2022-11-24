package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
public class WelcomeScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view) {
		return null;
	}

//	@Override
//	public void transitionToNextState() {
//		try {
//			var event = eventQueue.take();
//
//			if (event.getEventType().equals(EventType.START_BUTTON_PRESSED)) {
//				// TODO return next state
//				System.out.println("sldkfjslkdjfdlksj");
//				new WaitingScreenState(view,eventQueue).transitionToNextState();
//			}
//
//			throw new RuntimeException("Unexpected event: " + event);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
//	}

}
