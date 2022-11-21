package quizkampen.client.events;

import quizkampen.client.states.State;

public class TestEvent implements Event {

    @Override
    public State processMe(State state) {
        return state.process(this);
    }
}
