package se.nackademin.model;

import se.nackademin.io.eventmanagers.EventManager;

public abstract class State {

    protected final EventManager eventManager;

    public State(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public abstract State transitionToNextState();

}
