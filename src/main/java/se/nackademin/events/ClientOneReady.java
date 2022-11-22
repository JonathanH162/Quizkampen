package se.nackademin.events;

import se.nackademin.server.io.EventRouter;
import se.nackademin.server.states.State;

public class ClientOneReady implements Event{
    @Override
    public State processMe(State state, EventRouter eventRouter) {
        return state.process(this);
    }
}
