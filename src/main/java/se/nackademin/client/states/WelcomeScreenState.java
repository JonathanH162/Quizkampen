package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.eventmanagers.ClientEventManager;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class WelcomeScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view) {
		if (event.getEventType().equals(EventType.START_BUTTON_PRESSED)) {
			view.getPlayButton().setVisible(false);
			view.getWelcomeLabel().setText("Connecting to server..");
		}
		return new WaitingScreenState();
	}

//	@Override
//	public void transitionToNextState() {
//		try {
//			var event = eventQueue.take();
//
//			if (event.getEventType().equals(EventType.START_BUTTON_PRESSED)) {
//				// TODO return next state
//				System.out.println("sldkfjslkdjfdlksj");
//				new WaitingScreenState(view,eventQueue).transitionToNextState();
//			}
//
//			throw new RuntimeException("Unexpected event: " + event);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
//	}

}
