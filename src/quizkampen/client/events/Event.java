package quizkampen.client.events;

import quizkampen.client.states.State;

public interface Event {
    State processMe(State state);
}
