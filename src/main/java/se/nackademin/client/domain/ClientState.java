package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;

interface ClientState {

		ClientState transitionToNextState(Event event, View view, EventRepository eventRepository);
}
