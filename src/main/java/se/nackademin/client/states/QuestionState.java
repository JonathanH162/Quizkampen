package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ClientEventManager;

public class QuestionState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager) {
		return null;
	}
}
