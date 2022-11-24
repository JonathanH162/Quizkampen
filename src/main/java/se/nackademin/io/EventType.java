package se.nackademin.io;

public enum EventType {
	INITIAL_EVENT,
	TWO_PLAYER_CONNECTED,
	CONNECTION_SUCCESS,
	CONNECTION_FAILED,
	START_BUTTON_PRESSED,
	READY, // The sender is ready.
	NEW_ID, // New ID given to the receiver.
	GAME_OVER // Game is over.
}
