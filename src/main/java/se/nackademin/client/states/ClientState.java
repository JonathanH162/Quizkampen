package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;

interface ClientState {

		ClientState transitionToNextState(Event event, View view);
}
