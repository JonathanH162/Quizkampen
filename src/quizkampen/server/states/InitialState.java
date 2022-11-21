package quizkampen.server.states;

import quizkampen.server.events.ClientOneReady;
import quizkampen.server.events.ClientTwoReady;

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
