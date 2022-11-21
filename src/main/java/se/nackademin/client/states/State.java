package se.nackademin.client.states;

import se.nackademin.client.events.TestEvent;

public interface State {
    State process(TestEvent eventName);

    // TODO add all possible events.
}
