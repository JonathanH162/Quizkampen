package se.nackademin.client.states;

import se.nackademin.client.view.View;
import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.HostId;
import se.nackademin.io.eventmanagers.ClientEventManager;

import javax.swing.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class WelcomeScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventManager eventManager) {
		if (event.getEventType().equals(EventType.START_BUTTON_PRESSED)) {
			//view.getPlayButton().setVisible(false);
			//view.getWelcomeLabel().setText("Connecting to server..");
			try {
				eventManager.activate(new Socket(InetAddress.getLocalHost(), 1337));
			} catch (UnknownHostException e) {
				e.printStackTrace();
				// ServerSocket server = new ServerSocket(1337)
				// Socket client = server.accept();
			} catch (ConnectException ce) {
				eventManager.sendEvent(new Event(EventType.CONNECTION_FAILED, HostId.SELF,HostId.SELF, new Object()));

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
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
