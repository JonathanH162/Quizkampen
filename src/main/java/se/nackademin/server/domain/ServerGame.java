package se.nackademin.server.domain;

import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;
import se.nackademin.core.utils.ConfigProperties;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ServerGame implements ServerState{
    QuestionRepositoryService questionService = new QuestionRepositoryService();
    ConfigProperties properties = new ConfigProperties();
    private HostId currentPlayer;
    private boolean bothPlayersDone = false;
    private int roundsDone;
    private HashMap<HostId, List<Integer>> points = new HashMap<>();
    private Predicate<List<Integer>> bothPlayersAreDone = (points) -> points.size() % 2 == 0;

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
                var questionsToBeUsed = questions.subList(0, numberOfQuestionsNeeded - 1);
                // TODO Fix exception: https://stackoverflow.com/questions/26568205/resolve-a-java-util-arraylistsublist-notserializable-exception
                eventRepository.add(Event.toClient(EventType.SHOW_QUESTION, HostId.CLIENT_ONE, questionsToBeUsed));
                eventRepository.add(Event.toClient(EventType.SHOW_QUESTION, HostId.CLIENT_TWO, questionsToBeUsed));
                return this;
            }
            case ROUND_FINISHED -> {



                var clientResults = (List<Boolean>) event.getData();

                var clientPoints = Collections.frequency(clientResults,true);

                

                if (!points.containsKey(event.getSource())) {
                    points.put(event.getSource(), Collections.emptyList());
                }
                points.get(event.getSource()).add(clientPoints);

                // TODO


                roundsDone++;


                // Spara spelarens poäng.

                //event.getData() == [true, false, true]

                if (bothPlayersDone) {
                    eventRepository.add(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_ONE, points.get(1)));
                    eventRepository.add(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_ONE, points.get(0)));

                }

                // TODO Sparar statistiken som bifogats eventet.


/*


                Om båda spelarna är klara och det inte var sista omgången skickas ROUND_FINISHED till båda spelarna och statistik bifogas i eventet.

                Om båda spelarna är klara och det var sista omgången skickas GAME_FINISHED till båda spelarna och statistik bifogas i eventet. Tråden avslutas.*/



/*                if (bothPlayersDone && roundsDone <= properties.getNumberOfRound()) {

                    eventRepository.add(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_ONE, Statistics));
                    eventRepository.add(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_TWO, Statistics));
                }
                else {
                    eventRepository.add(Event.toClient(EventType.GAME_FINISHED, HostId.CLIENT_ONE, Statistics));
                    eventRepository.add(Event.toClient(EventType.GAME_FINISHED, HostId.CLIENT_ONE, Statistics));
                    Thread.currentThread().interrupt();
                }*/



            }


            default -> throw new RuntimeException("Event not handled: " + event.getEventType());
        }
        return null;
    }



}
