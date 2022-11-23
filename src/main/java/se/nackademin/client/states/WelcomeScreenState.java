package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

import java.util.concurrent.BlockingQueue;

public class WelcomeScreenState extends State {

	private final View view;
	private final BlockingQueue<Event> eventQueue;

	public WelcomeScreenState(View view, BlockingQueue<Event> eventQueue) {
		this.view = view;
		this.eventQueue = eventQueue;
	}

	@Override
	public void setEventManager(EventManager eventManager) {

	}

	@Override
	public State transitionToNextState() {
		try {
			var event = eventQueue.take();

			if (event.getEventType().equals(EventType.START_BUTTON_PRESSED)) {
				// TODO return next state
			}

			throw new RuntimeException("Unexpected event: " + event);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
