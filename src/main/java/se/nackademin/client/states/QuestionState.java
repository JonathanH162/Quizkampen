package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ClientEventManager;
import se.nackademin.io.eventmanagers.EventManager;

public class QuestionState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view) {
		return null;
	}
}
