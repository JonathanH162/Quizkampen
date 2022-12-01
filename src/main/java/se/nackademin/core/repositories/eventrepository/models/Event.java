package se.nackademin.core.repositories.eventrepository.models;

import java.io.Serializable;
import java.util.Objects;

public class Event implements Serializable {

	public static final long serialVersionUID = 1234567L;

	private final EventType eventType;
	private final HostId destination;
	private HostId source;
	private Object data;

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

	public static Event empty() {
		return new Event(EventType.EMPTY, HostId.EMPTY, HostId.EMPTY, HostId.EMPTY);
	}

	public static Event toSelf(EventType eventType) {
		return new Event(eventType, HostId.SELF, HostId.SELF, HostId.EMPTY);
	}

	public static Event toSelf(EventType eventType, Object data) {
		return new Event(eventType, HostId.SELF, HostId.SELF, data);
	}

	public static Event toBothClients(EventType eventType) {
		return new Event(eventType, HostId.BOTH_CLIENTS, HostId.EMPTY, HostId.EMPTY);
	}

	public static Event toBothClients(EventType eventType, Object data) {
		return new Event(eventType, HostId.BOTH_CLIENTS, HostId.EMPTY, data);
	}

	public static Event toClient(EventType eventType, HostId client) {
		return new Event(eventType, client, HostId.EMPTY, HostId.EMPTY);//Varför två HostId.EMPTY?
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

	public Event(EventType eventType, HostId destination, HostId source, Object data) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return eventType == event.eventType && destination == event.destination && source == event.source && Objects.equals(data, event.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventType, destination, source, data);
	}

	public void setData(Object object) {
		data = object;
	}

}
