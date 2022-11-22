package se.nackademin.events;

import se.nackademin.server.io.EventRouter;
import se.nackademin.server.states.State;

public class ClientTwoReady implements Event{
    @Override
    public State processMe(State state, EventRouter eventRouter) {
        return state.process(this);
    }
}
