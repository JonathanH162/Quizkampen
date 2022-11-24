package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ClientEventManager;
import se.nackademin.io.eventmanagers.EventManager;
import se.nackademin.model.StateOLD;

import java.util.concurrent.BlockingQueue;

public class LobbyState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager) {
		return null;
	}

}
