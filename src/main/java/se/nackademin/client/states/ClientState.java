package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ClientEventManager;

interface ClientState {

		ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager);
}
