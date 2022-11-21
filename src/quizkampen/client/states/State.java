package quizkampen.client.states;

import quizkampen.client.events.TestEvent;

public interface State {
    State process(TestEvent eventName);

    // TODO add all possible events.
}
