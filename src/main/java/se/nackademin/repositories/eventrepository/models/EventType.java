package se.nackademin.repositories.eventrepository.models;

public enum EventType {
	NEW_ROUND_BUTTON_PRESSED,
	INITIAL_EVENT,
	TWO_PLAYERS_CONNECTED,
	CONNECTION_SUCCESS,
	CONNECTION_FAILED,
	START_BUTTON_PRESSED,
	READY, // The sender is ready.
	NEW_ID, // New ID given to the receiver.
	GAME_OVER // Game is over.
}
