package se.nackademin.events;

import se.nackademin.server.io.EventRouter;
import se.nackademin.server.states.State;

public interface Event {

    State processMe(State state, EventRouter eventRouter);

    Integer getDestination();

    Integer getSource();

}
