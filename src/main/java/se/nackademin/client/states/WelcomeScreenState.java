package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.HostId;
import se.nackademin.io.eventmanagers.ClientEventManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class WelcomeScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager) {
		return switch (event.getEventType()) {
			case START_BUTTON_PRESSED -> connectToServer(view, eventManager);
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		};
	}

	private ClientState connectToServer(View view, ClientEventManager eventManager) {
		view.getWelcomeLabel().setText("Connecting to Server");
		try {
			eventManager.connect(new Socket(InetAddress.getLocalHost(), 1337));
		} catch (IOException e) {
			view.getWelcomeLabel().setText("Connection failed");
			eventManager.sendEvent(new Event(EventType.CONNECTION_FAILED, HostId.SELF, HostId.SELF, new Object()));
			waitForReturn();
			return new InitialState();
		}
		return new SuccessfulConnectionState();
	}

	private void waitForReturn() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


}
