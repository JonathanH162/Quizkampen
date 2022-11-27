package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;

public class WaitingForStatisticsState implements ClientState{
	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		return null;
	}

}
