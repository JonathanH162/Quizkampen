package se.nackademin.server;

import se.nackademin.server.events.Event;
import se.nackademin.server.states.InitialState;
import se.nackademin.server.states.State;

public class Protocol {
    private State currentState = new InitialState();

    public void transition(Event event) {
        currentState = event.processMe(currentState);
    }
}
