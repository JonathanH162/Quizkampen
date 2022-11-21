package se.nackademin.server.events;

import se.nackademin.server.states.State;

public class ClientOneReady implements Event{
    @Override
    public State processMe(State state) {
        return state.process(this);
    }
}
