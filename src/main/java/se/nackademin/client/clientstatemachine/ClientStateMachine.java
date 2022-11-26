package se.nackademin.client.clientstatemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.client.view.View;
import se.nackademin.repositories.eventrepository.models.Event;
import se.nackademin.repositories.eventrepository.models.EventType;
import se.nackademin.repositories.eventrepository.ClientEventRepository;

public class ClientStateMachine {

    private ClientState currentState;
    private final ClientEventRepository clientEventManager = new ClientEventRepository();
    private final View view = new View(clientEventManager);

    private static final Logger logger = LogManager.getLogger(ClientStateMachine.class);

    public ClientStateMachine(ClientState currentState) {
        this.currentState = currentState;
    }

    public void run() {
        logger.info("StateMachine started.");
        clientEventManager.sendEvent(Event.toSelf(EventType.INITIAL_EVENT));

        while (true) {

            logger.info("Current state: " + currentState.getClass());
            logger.info("Looking for events..");
            var event = clientEventManager.getEvent();
            logger.info("Event found: " + event.getEventType());

            currentState = currentState.transitionToNextState(event, view, clientEventManager);
        }
    }

}

