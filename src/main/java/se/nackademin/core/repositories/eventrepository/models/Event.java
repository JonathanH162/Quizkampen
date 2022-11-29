package se.nackademin.core.repositories.eventrepository.models;

import java.io.Serializable;

public class Event implements Serializable {

	private final EventType eventType;
	private final HostId destination;
	private HostId source;
	private final Object data;

	public void setSource(HostId source) {
		this.source = source;
	}

	public HostId getDestination() {
		return destination;
	}

	public HostId getSource() {
		return source;
	}

	public Object getData() {
		return data;
	}

	public EventType getEventType() {
		return eventType;
	}

	public static Event toSelf(EventType eventType) {
		return new Event(eventType, HostId.SELF, HostId.SELF, HostId.EMPTY);
	}

	public static Event toSelf(EventType eventType, Object data) {
		return new Event(eventType, HostId.SELF, HostId.SELF, data);
	}

	public static Event toClient(EventType eventType, HostId client) {
		return new Event(eventType, client, HostId.EMPTY, HostId.EMPTY);
	}

	public static Event toClient(EventType eventType, HostId client, Object data) {
		return new Event(eventType, client, HostId.EMPTY, data);
	}

	public static Event toServer(EventType eventType) {
		return new Event(eventType, HostId.SERVER, HostId.EMPTY, HostId.EMPTY);
	}

	public static Event toServer(EventType eventType, Object data) {
		return new Event(eventType, HostId.SERVER, HostId.EMPTY, data);
	}

	private Event(EventType eventType, HostId destination, HostId source, Object data) {
		this.eventType = eventType;
		this.destination = destination;
		this.source = source;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Event{" + "eventType=" + eventType + ", destination=" + destination + ", source=" + source + ", data="
				+ data + '}';
	}

}
