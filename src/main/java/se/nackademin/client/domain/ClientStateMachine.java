package se.nackademin.client.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;

public class ClientStateMachine {

    private ClientState currentState;
    private final EventRepository eventRepository = new ClientEventRepository();
    private final View view = new View(eventRepository);
    private Event lastEvent = Event.empty();

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


            if (eventShouldBeHandled(event)){
                lastEvent = event;
                currentState = currentState.transitionToNextState(event, view, eventRepository);
            }
        }
    }

    private boolean eventShouldBeHandled(Event event){
        // Returns true only if incoming event is not the same as last event.
        return !event.equals(lastEvent);
    }

}

