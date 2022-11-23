package se.nackademin.model;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.EventManager;

import java.util.concurrent.BlockingQueue;

public abstract class State {
    protected View view;
    protected BlockingQueue<Event> eventQueue;




    public State(View view, BlockingQueue<Event> eventQueue) {
        this.view = view;
        this.eventQueue = eventQueue;
    }

    public State(EventManager eventManager) {
        setEventManager(eventManager);
    }

    public abstract void setEventManager(EventManager eventManager);

    public abstract void transitionToNextState();

}
