package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;

public class CategoryWaitingState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view) {
		return null;
	}

}

