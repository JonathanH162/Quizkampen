package se.nackademin.client.domain;

import se.nackademin.client.presentation.LobbyPanel;
import se.nackademin.client.presentation.View;
import se.nackademin.client.presentation.WelcomePanel;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.utils.ConfigProperties;

import java.io.IOException;
import java.net.Socket;

public class WelcomeScreenState implements ClientState {
	private final WelcomePanel welcomePanel = new WelcomePanel();
	private final LobbyPanel lobbyPanel = new LobbyPanel();
	@Override
	public ClientState transitionToNextState(Event event, View view, EventRepository eventRepository) {
		switch (event.getEventType()) {
			case INITIAL -> {
				view.showPanel(welcomePanel);
				welcomePanel.setStartButtonListener((e) -> eventRepository.add(Event.toSelf(EventType.START_BUTTON_PRESSED)));
				return this;
			}
			case START_BUTTON_PRESSED -> { connectToServer(view, eventRepository); return this; }
			case CONNECTION_SUCCESS -> {
				lobbyPanel.setPlayButtonListener((e) -> eventRepository.add(Event.toSelf(EventType.SHOW_CATEGORIES_BUTTON)));
				view.showPanel(lobbyPanel);
				return new LobbyState(eventRepository);
			}
			case CONNECTION_FAILED -> {
				sleepFiveSeconds();
				return this;
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

	private ClientState connectToServer(View view, EventRepository eventRepository) {
		view.showPanel(welcomePanel);
		welcomePanel.getStartButton().setVisible(false);//Varför får den vara false?
		welcomePanel.getWelcomeLabel().setText("Connecting to Server...");
		try {
			var properties = new ConfigProperties();
			eventRepository.connect(new Socket(properties.getServerIp(), properties.getServerPort()));

		} catch (IOException e) {
			welcomePanel.getWelcomeLabel().setText("Connection failed");
			eventRepository.add(Event.toSelf(EventType.CONNECTION_FAILED));
			return this;
		}
		eventRepository.add(Event.toSelf(EventType.CONNECTION_SUCCESS));
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
