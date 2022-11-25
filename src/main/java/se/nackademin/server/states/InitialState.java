package se.nackademin.server.states;

import se.nackademin.io.Event;
import se.nackademin.io.eventmanagers.ServerEventManager;

/**
 * The initial state of the server. Sends ID's to the clients to that they know what to put as sender when they send
 * events to the server.
 */
public class InitialState implements ServerState {

	@Override
	public ServerState transitionToNextState(Event event, ServerEventManager eventManager) {
		return null;
	}

}
// Server
// serversocket server = new server(port)
// clientOne = server.accept()
// clientTwo = server.accept()
// Thread clientHandler = new ClientHandler(clientOne, clientTwo))
// cH.start();





//	public ServerState transitionToNextState(Event event, ServerEventManager eventManager) {

// serversocket server = new server(port)
// clientOne = server.accept()
// clientTwo = server.accept()
// new Thread(new ServerStateMachine(clientOne,clientTwo)).start;
// skicka nytt initialevent
// return new initialstate


//		if (event.getEventType().equals(EventType.INITIAL_EVENT)) {
//			Socket clientOne = server.accept()
//          Socket clientTwo = server.accept();
//          eventManager.connect(clientOne, clientTwo);
//
//          eventManager.sendEvent(new Event(EventType.TWO_PLAYER_CONNECT, CLIENTONE.ID,CLIENTTWO.TWO, new Object()));
//
//		} else if (event.getEventType().equals(EventType.CLIENT_DISCONNECT)) {
//			// do something else
//		}
//		return new InitialState();
//	}

/*		var eventToClientOne = Event.toClientOne(EventType.NEW_ID, HostId.CLIENT_ONE);
		var eventToClientTwo = Event.toClientTwo(EventType.NEW_ID, HostId.CLIENT_TWO);

		eventManager.sendEvent(eventToClientOne);
		eventManager.sendEvent(eventToClientTwo);

		return new EndState(eventManager);*/