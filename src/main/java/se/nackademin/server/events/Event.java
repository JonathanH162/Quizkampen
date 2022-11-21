package se.nackademin.server.events;

import se.nackademin.server.states.State;

public interface Event {

    State processMe(State state);

}
