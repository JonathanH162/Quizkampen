package quizkampen.server;

import quizkampen.server.states.InitialState;
import quizkampen.server.states.State;
import quizkampen.server.events.Event;

public class Protocol {
    private State currentState = new InitialState();

    public void transition(Event event) {
        currentState = event.processMe(currentState);
    }
}
