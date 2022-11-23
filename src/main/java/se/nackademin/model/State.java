package se.nackademin.model;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.EventManager;

import java.util.concurrent.BlockingQueue;

public abstract class State {




    public State(View view, BlockingQueue<Event> eventQueue) {
    }

    public State(EventManager eventManager) {
        setEventManager(eventManager);
    }

    public abstract void setEventManager(EventManager eventManager);

    public abstract void transitionToNextState();

}
