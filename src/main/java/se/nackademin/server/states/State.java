package se.nackademin.server.states;

import se.nackademin.events.ClientOneReady;
import se.nackademin.events.ClientTwoReady;

public interface State {

    State process(ClientOneReady clientOneReady);

    State process(ClientTwoReady clientTwoReady);

    // TODO add all possible events.
}
