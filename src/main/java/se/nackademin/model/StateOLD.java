package se.nackademin.model;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.EventManager;

import java.util.concurrent.BlockingQueue;

public abstract class StateOLD {
    protected View view;
    protected BlockingQueue<Event> eventQueue;




    public StateOLD(View view, BlockingQueue<Event> eventQueue) {
        this.view = view;
        this.eventQueue = eventQueue;
    }

    public StateOLD(EventManager eventManager) {
        setEventManager(eventManager);
    }

    public abstract void setEventManager(EventManager eventManager);

    public abstract void transitionToNextState();

}
