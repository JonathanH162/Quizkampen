package se.nackademin.server.domain;

import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.server.data.ServerEventRepository;

public class ServerGame {
    private HostId currentPlayer;
    public ServerState transitionToNextState(Event event, ServerEventRepository eventRepository) {
        switch (event.getEventType()) {
            case INITIAL_EVENT -> {
                //currentPlayer = ??
                eventRepository.sendEvent(Event.toClient(EventType.NEXT_TO_CHOOSE, HostId.CLIENT_ONE, HostId.CLIENT_TWO));
                eventRepository.sendEvent(Event.toClient(EventType.WAITING_FOR_CHOICE, HostId.CLIENT_TWO, HostId.CLIENT_ONE));
            }
            case CATEGORY_CHOSEN -> {
                eventRepository.sendEvent(Event.toClient(EventType.SHOW_QUESTION, HostId.CLIENT_ONE));
                eventRepository.sendEvent(Event.toClient(EventType.SHOW_QUESTION, HostId.CLIENT_TWO));
            }
            case ROUND_FINISHED -> {
                if (bothPlayersDone && rounds <= 3) {
                    eventRepository.sendEvent(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_ONE, Statistics));
                    eventRepository.sendEvent(Event.toClient(EventType.ROUND_FINISHED, HostId.CLIENT_TWO, Statistics));
                }
                else {
                    eventRepository.sendEvent(Event.toClient(EventType.GAME_FINISHED, HostId.CLIENT_ONE, Statistics));
                    eventRepository.sendEvent(Event.toClient(EventType.GAME_FINISHED, HostId.CLIENT_ONE, Statistics));
                    Thread.currentThread().interrupt();
                }
            }


            default -> throw new RuntimeException("Event not handled: " + event.getEventType());
        }
        return null;
    }
}
