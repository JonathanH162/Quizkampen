package se.nackademin.server.states;

import se.nackademin.server.events.ClientOneReady;
import se.nackademin.server.events.ClientTwoReady;

public interface State {

    State process(ClientOneReady clientOneReady);

    State process(ClientTwoReady clientTwoReady);

    // TODO add all possible events.
}
