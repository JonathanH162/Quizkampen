package se.nackademin.model;

import se.nackademin.model.events.ReadyEvent;
import se.nackademin.model.events.SetSourceIdEvent;
import se.nackademin.io.eventrouters.EventRouter;

public interface State {

    State process(SetSourceIdEvent event, EventRouter eventRouter);
    State process(ReadyEvent event, EventRouter eventRouter);

    // TODO add all possible events.
}
