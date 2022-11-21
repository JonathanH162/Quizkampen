package quizkampen.server.events;

import quizkampen.server.states.State;

public class ClientOneReady implements Event{
    @Override
    public State processMe(State state) {
        return state.process(this);
    }
}
