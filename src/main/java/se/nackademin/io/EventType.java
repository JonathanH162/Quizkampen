package se.nackademin.io;

public enum EventType {
	START_BUTTON_PRESSED,
	READY, // The sender is ready.
	NEW_ID, // New ID given to the receiver.
	GAME_OVER // Game is over.
}
