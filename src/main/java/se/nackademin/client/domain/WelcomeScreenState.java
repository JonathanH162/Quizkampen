package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.utils.ConfigProperties;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class WelcomeScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		return switch (event.getEventType()) {
			case START_BUTTON_PRESSED -> connectToServer(view, eventRepository);
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		};
	}

	private ClientState connectToServer(View view, ClientEventRepository eventRepository) {
		view.getStartButton().setVisible(false);
		view.getWelcomeLabel().setText("Connecting to Server...");
		try {
			var properties = new ConfigProperties();
			eventRepository.connect(new Socket(properties.getServerIp(), properties.getServerPort()));

		} catch (IOException e) {
			view.getWelcomeLabel().setText("Connection failed");
			eventRepository.sendEvent(Event.toSelf(EventType.CONNECTION_FAILED));
			sleepFiveSeconds();
			return new InitialState();
		}
		return new SuccessfulConnectionState();
	}

	private void sleepFiveSeconds() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}


}
