package se.nackademin.events;

import se.nackademin.server.io.EventRouter;
import se.nackademin.server.states.State;

public abstract class Event {

    private final Integer destination;
    private final Integer source;
    private final Object data;

    public Integer getDestination() {
        return destination;
    }

    public Integer getSource() {
        return source;
    }

    public Object getData() {
        return data;
    }

    protected Event(Integer destination, Integer source, Object data) {
        this.destination = destination;
        this.source = source;
        this.data = data;
    }

    abstract State processMe(State state, EventRouter eventRouter);


}
