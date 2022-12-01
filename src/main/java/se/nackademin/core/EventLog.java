package se.nackademin.core;

import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.utils.ConfigProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EventLog implements Serializable {

	private final List<Event> events = new ArrayList<>();
	private final ConfigProperties configProperties = new ConfigProperties();

	public void log(Event event) {
		events.add(event);
	}

	private Long countEventType(EventType eventType) {
		return events.stream()
				.filter((event -> event.getEventType().equals(eventType)))
				.count();
	}

	public HostId getNextToChoose() {
		var count = countEventType(EventType.NEXT_TO_CHOOSE);
		return (count % 2 == 0) ? HostId.CLIENT_ONE : HostId.CLIENT_TWO;
	}

	public HostId getWaitingForChoice() {
		return (getNextToChoose().equals(HostId.CLIENT_ONE)) ? HostId.CLIENT_TWO : HostId.CLIENT_ONE;
	}

	public boolean bothPlayersCompletedRound() {
		var count = countEventType(EventType.ROUND_FINISHED);
		return (count % 2 == 0);
	}

	public Long getNumberOfCompletedRounds() {
		var count = countEventType(EventType.ROUND_FINISHED);
		return count / 2;
	}

	public boolean gameIsFinished() {
		var roundsSoFar = getNumberOfCompletedRounds();
		var roundsPerGame = configProperties.getNumberOfRound();
		return roundsSoFar == roundsPerGame;
	}

	public Integer getPlayerPointsForRound(HostId player, Integer round) {
		var counter = new AtomicInteger(1);
		return events.stream()
				.filter((event -> event.getSource().equals(player)))
				.filter(event -> event.getEventType().equals(EventType.ROUND_FINISHED))
				.filter(event -> counter.getAndIncrement() == round)
				.map(event -> (List<Integer>) event.getData())
				.map((list) -> Collections.frequency(list, true))
				.findFirst()
				.orElseThrow();
	}

	public HashMap<Integer, Integer> getPointsForAllRoundsSoFar(HostId player) {
		var points = new HashMap<Integer, Integer>();
		var numberOfCompletedRounds = getNumberOfCompletedRounds();
		for (int round = 1; round <= numberOfCompletedRounds; round++) {
			points.put(round, getPlayerPointsForRound(player, round));
		}
		return points;
	}

	public Integer getTotalPointsForAllRoundsSoFar(HostId player) {
		return getPointsForAllRoundsSoFar(player).values().stream()
				.mapToInt(Integer::intValue)
				.sum();
	}

}
