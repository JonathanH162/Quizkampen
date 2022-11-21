package se.nackademin.client.events;

import se.nackademin.client.states.State;

public class TestEvent implements Event {

    @Override
    public State processMe(State state) {
        return state.process(this);
    }
}
