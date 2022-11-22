package se.nackademin.server;

import se.nackademin.server.io.EventRouter;
import se.nackademin.server.states.InitialState;
import se.nackademin.server.states.State;

public class Protocol implements Runnable {
    private State currentState = new InitialState();

private final EventRouter eventRouter;

    public Protocol(EventRouter eventRouter) {
        this.eventRouter = eventRouter;
    }

    @Override
    public void run() {
        while(true) {
                currentState = eventRouter.getEvent().processMe(currentState, eventRouter);
        }
    }

}
