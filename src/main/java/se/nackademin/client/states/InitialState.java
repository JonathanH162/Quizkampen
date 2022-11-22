package se.nackademin.client.states;

import se.nackademin.events.*;
import se.nackademin.io.Destination;
import se.nackademin.io.Source;
import se.nackademin.io.eventrouters.EventRouter;
import se.nackademin.model.State;
import se.nackademin.model.events.ReadyEvent;
import se.nackademin.model.events.SetSourceIdEvent;

public class InitialState implements State {

    @Override
    public State process(SetSourceIdEvent event, EventRouter eventRouter) {

        // Get this client's ID to client 1 or client 2
        eventRouter.setSourceId((Source) event.getData());

        // Return the next state
        return new ClientReadyState();
    }

    @Override
    public State process(ReadyEvent event, EventRouter eventRouter) {

        // Tell the server that this client is ready
        eventRouter.sendEvent(new ReadyEvent(Destination.SERVER,new Object()));

        // TODO return the next state
        return null;
    }

}
