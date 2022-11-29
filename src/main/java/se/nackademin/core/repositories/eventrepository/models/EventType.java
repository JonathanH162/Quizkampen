package se.nackademin.core.repositories.eventrepository.models;

public enum EventType {
	NEW_ROUND_BUTTON_PRESSED,
	INITIAL_EVENT,
	TWO_PLAYERS_CONNECTED,
	CONNECTION_SUCCESS,
	CONNECTION_FAILED,
	START_BUTTON_PRESSED,
	READY, // The sender is ready.
	NEW_ID, // New ID given to the receiver.
	ROUND_FINISHED, SHOW_QUESTION, GAME_FINISHED, GAME_OVER // Game is over.
}
