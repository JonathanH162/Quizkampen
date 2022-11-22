package se.nackademin.server.states;

import se.nackademin.events.ClientReady;

public interface State {

    State process(ClientReady clientReady);

    // TODO add all possible events.
}
