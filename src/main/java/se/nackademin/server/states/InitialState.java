package se.nackademin.server.states;

import se.nackademin.server.events.ClientOneReady;
import se.nackademin.server.events.ClientTwoReady;

public class InitialState implements State {

    @Override
    public State process(ClientOneReady clientOneReady) {
        // TODO Do stuff.
        // TODO Return the next state.
        return null;
    }

    @Override
    public State process(ClientTwoReady clientTwoReady) {
        // TODO Do stuff.
        // TODO Return the next state.
        return null;
    }

}
