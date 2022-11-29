package se.nackademin.client.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;

public class ClientStateMachine {

    private ClientState currentState;
    private final ClientEventRepository eventRepository = new ClientEventRepository();
    private final View view = new View(eventRepository);

    private static final Logger logger = LogManager.getLogger(ClientStateMachine.class);

    public ClientStateMachine(ClientState currentState) {
        this.currentState = currentState;
    }

    public void run() {
        logger.info("StateMachine started.");
        eventRepository.add(Event.toSelf(EventType.INITIAL));

        while (true) {

            logger.info("Current state: " + currentState.getClass());
            var event = eventRepository.get();
            logger.info("Event found: " + event.getEventType());

            currentState = currentState.transitionToNextState(event, view, eventRepository);
        }
    }

}

