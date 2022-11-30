package se.nackademin.server.domain;

import se.nackademin.core.EventLog;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;
import se.nackademin.core.utils.ConfigProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerGame implements ServerState {

	QuestionRepositoryService questionService = new QuestionRepositoryService();
	ConfigProperties properties = new ConfigProperties();
	// private HashMap<HostId, List<Integer>> points = new HashMap<>();

	@Override
	public ServerState transitionToNextState(Event event, EventRepository eventRepository, EventLog eventLog) {
		switch (event.getEventType()) {
			case START_ROUND -> {
				eventRepository.add(Event.toClient(EventType.NEXT_TO_CHOOSE, eventLog.getNextToChoose()));
				eventRepository.add(Event.toClient(EventType.WAITING_FOR_CHOICE, eventLog.getWaitingForChoice()));
				return this;
			}
			case CATEGORY_CHOSEN -> {
				String category = event.getData().toString();
				var questions = questionService.getAllQuestionInCategory(category);
				var numberOfQuestionsNeeded = properties.getNumberOfQuestion();
				List<String> questionsToBeUsed = new ArrayList<>(questions.subList(0, numberOfQuestionsNeeded));
				eventRepository.add(Event.toBothClients(EventType.SHOW_QUESTION, questionsToBeUsed));
				return this;
			}
			case ROUND_FINISHED -> {
/*                var clientResults = (List<Boolean>) event.getData();
                var clientPoints = Collections.frequency(clientResults,true);

                if (!points.containsKey(event.getSource())) {
                    points.put(event.getSource(), new ArrayList<>());
                }
                points.get(event.getSource()).add(clientPoints);
                */

				if (eventLog.bothPlayersCompletedRound() && !eventLog.gameIsFinished()) {
					eventRepository.add(Event.toBothClients(EventType.ROUND_FINISHED, eventLog));
					waitForAWhile();
				}

				if (eventLog.gameIsFinished()) {
					eventRepository.add(Event.toBothClients(EventType.GAME_FINISHED, eventLog));
					Thread.currentThread().interrupt();
				}

				eventRepository.add(Event.toSelf(EventType.START_ROUND));
				return this;
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}

	private static void waitForAWhile() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
