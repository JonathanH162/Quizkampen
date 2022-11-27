package se.nackademin.client;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;

import java.io.*;

public class Test {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		var event = Event.toServer(EventType.INITIAL_EVENT, EventType.INITIAL_EVENT);

		// Serialize the event.
		var byteArrayOutputStream = new ByteArrayOutputStream();
		var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(event);

		// Deserialize the event.
		var bytes = byteArrayOutputStream.toByteArray();
		var byteArrayInputStream = new ByteArrayInputStream(bytes);
		var objectInputStream = new ObjectInputStream(byteArrayInputStream);
		var deserializedEvent = (Event) objectInputStream.readObject();

		System.out.println(deserializedEvent);
	}

}
