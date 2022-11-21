package quizkampen.server.states;

import quizkampen.server.events.*;

public interface State {

    State process(ClientOneReady clientOneReady);

    State process(ClientTwoReady clientTwoReady);

    // TODO add all possible events.
}
