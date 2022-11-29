package se.nackademin.client.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.utils.ConfigProperties;

import java.io.IOException;
import java.net.Socket;

public class WelcomeScreenState implements ClientState {

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		switch (event.getEventType()) {
			case SHOW_GUI -> view.initiateView();
			case START_BUTTON_PRESSED -> connectToServer(view, eventRepository);
			case CONNECTION_SUCCESS -> {
				view.lobbyScreen();
				return new LobbyState();
			}
			case CONNECTION_FAILED -> {
				sleepFiveSeconds();
				return this;
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
		return null;
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
			return this;
		}
		eventRepository.sendEvent(Event.toSelf(EventType.CONNECTION_SUCCESS));
		return this;
	}

	private void sleepFiveSeconds() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}


}
