package se.nackademin.events;

import se.nackademin.server.io.EventRouter;
import se.nackademin.server.states.State;

public class ClientReady extends Event{

    protected ClientReady(Integer destination, Integer source, Object data) {
        super(destination, source, data);
    }

    @Override
    public State processMe(State state, EventRouter eventRouter) {
        return state.process(this);
    }
}
