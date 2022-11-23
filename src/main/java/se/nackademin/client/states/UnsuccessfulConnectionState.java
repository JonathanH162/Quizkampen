package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.State;

import java.util.concurrent.BlockingQueue;

public class UnsuccessfulConnectionState extends State {


	public UnsuccessfulConnectionState(View view, BlockingQueue<Event> eventQueue) {
		super(view, eventQueue);
	}

	@Override
	public void setEventManager(EventManager eventManager) {

	}

	@Override
	public void transitionToNextState() {
		try {
			var event = eventQueue.take();
			System.out.println("Anslutningen misslyckades");
			new InitialState(view,eventQueue).transitionToNextState();


		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
