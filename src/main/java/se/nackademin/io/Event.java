package se.nackademin.io;

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
		return new Event(eventType, HostId.SELF, HostId.SELF, new Object());
	}

	public static Event toClientOne(EventType eventType) {
		return new Event(eventType, HostId.CLIENT_ONE, HostId.EMPTY, new Object());
	}

	public static Event toClientOne(EventType eventType, Object data) {
		return new Event(eventType, HostId.CLIENT_ONE, HostId.EMPTY, data);
	}

	public static Event toClientTwo(EventType eventType) {
		return new Event(eventType, HostId.CLIENT_TWO, HostId.EMPTY, new Object());
	}

	public static Event toClientTwo(EventType eventType, Object data) {
		return new Event(eventType, HostId.CLIENT_TWO, HostId.EMPTY, data);
	}

	public static Event toServer(EventType eventType) {
		return new Event(eventType, HostId.SERVER, HostId.EMPTY, new Object());
	}

	public static Event toServer(EventType eventType, Object data) {
		return new Event(eventType, HostId.SERVER, HostId.EMPTY, data);
	}

	public Event(EventType eventType, HostId destination, HostId source, Object data) {
		this.eventType = eventType;
		this.destination = destination;
		this.source = source;
		this.data = data;
	}

}
