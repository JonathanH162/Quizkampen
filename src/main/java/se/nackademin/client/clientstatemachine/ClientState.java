package se.nackademin.client.clientstatemachine;

import se.nackademin.client.view.View;
import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.ClientEventRepository;

interface ClientState {

		ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository);
}
