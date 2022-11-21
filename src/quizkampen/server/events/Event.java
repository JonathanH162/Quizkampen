package quizkampen.server.events;

import quizkampen.server.states.State;

public interface Event {

    State processMe(State state);

}
