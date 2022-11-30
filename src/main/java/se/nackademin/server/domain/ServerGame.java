package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;
import se.nackademin.core.utils.ConfigProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ServerGame implements ServerState{
    QuestionRepositoryService questionService = new QuestionRepositoryService();
    ConfigProperties properties = new ConfigProperties();
    private boolean clientOneShouldStart = true;
    private int roundsDone;
    private HashMap<HostId, List<Integer>> points = new HashMap<>();
    private final Predicate<HashMap<HostId, List<Integer>>> bothPlayersAreDone = (points) -> points.keySet().size() == 2;
    private final Predicate<Integer> gameIsNotFinished = (roundsDone) -> roundsDone <= properties.getNumberOfRound();
    private HostId nextToChoose = HostId.CLIENT_ONE;
    private HostId waitingForChoice = HostId.CLIENT_TWO;

    @Override
    public ServerState transitionToNextState(Event event, EventRepository eventRepository) {
        switch (event.getEventType()) {
            case START_ROUND -> {
                //currentPlayer = ??
                eventRepository.add(Event.toClient(EventType.NEXT_TO_CHOOSE, HostId.CLIENT_ONE, HostId.CLIENT_ONE));
                eventRepository.add(Event.toClient(EventType.WAITING_FOR_CHOICE, HostId.CLIENT_TWO, HostId.CLIENT_TWO));
                return this;
            }
            case CATEGORY_CHOSEN -> {
                String category = event.getData().toString();
                var questions = questionService.getAllQuestionInCategory(category);
                var numberOfQuestionsNeeded = properties.getNumberOfQuestion();
                List<String> questionsToBeUsed = new ArrayList<>(questions.subList(0, numberOfQuestionsNeeded));
                eventRepository.add(Event.toClient(EventType.SHOW_QUESTION, HostId.CLIENT_ONE, questionsToBeUsed));
                eventRepository.add(Event.toClient(EventType.SHOW_QUESTION, HostId.CLIENT_TWO, questionsToBeUsed));
                return this;
            }
            case ROUND_FINISHED -> {
                var clientResults = (List<Boolean>) event.getData();
                var clientPoints = Collections.frequency(clientResults,true);

                if (!points.containsKey(event.getSource())) {
                    points.put(event.getSource(), new ArrayList<>());
                }
                points.get(event.getSource()).add(clientPoints);

                //  If both players are done

                if (bothPlayersAreDone.test(points)){
                    if (gameIsNotFinished.test(roundsDone)) {
                        eventRepository.add(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_ONE, points));
                        eventRepository.add(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_TWO, points));
                        roundsDone++;
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        eventRepository.add(Event.toSelf(EventType.START_ROUND));
                        return this;
                    } else {
                        eventRepository.add(Event.toClient(EventType.GAME_FINISHED, HostId.CLIENT_ONE, points));
                        eventRepository.add(Event.toClient(EventType.GAME_FINISHED, HostId.CLIENT_ONE, points));
                        Thread.currentThread().interrupt();

                    }
                }

                eventRepository.add(Event.toServer(EventType.START_ROUND));

                return this;
            }
            default -> throw new RuntimeException("Event not handled: " + event.getEventType());
        }
    }


}
