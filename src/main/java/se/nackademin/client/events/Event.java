package se.nackademin.client.events;


import se.nackademin.client.states.State;

public interface Event {
    State processMe(State state);
}
