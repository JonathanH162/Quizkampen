package se.nackademin.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.nackademin.client.Client;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.eventrepository.models.HostId;
import se.nackademin.server.domain.ServerStateMachine;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class EventLogTest {

	EventLog eventLog = new EventLog();
	List<Event> eventListOne;
	List<Event> eventListTwo;
	List<Boolean> playerOneAnswerResults = List.of(true,false,false,true); // 2 / 4
	List<Boolean> playerTwoAnswerResults = List.of(false,false,false,true); // 1 / 4

	@BeforeEach
	void setUp() {
		eventListOne = new ArrayList<>();
		eventListOne.add(new Event(EventType.START_ROUND, HostId.SELF, HostId.SERVER, HostId.SELF));
		eventListOne.add(new Event(EventType.START_ROUND, HostId.SELF, HostId.SERVER, HostId.SELF));
		eventListOne.add(new Event(EventType.NEXT_TO_CHOOSE, HostId.CLIENT_ONE, HostId.SERVER, HostId.CLIENT_ONE));
		eventListOne.add(new Event(EventType.WAITING_FOR_CHOICE, HostId.CLIENT_ONE, HostId.SERVER, HostId.CLIENT_ONE));
		eventListOne.add(new Event(EventType.CATEGORY_CHOSEN, HostId.SERVER, HostId.CLIENT_ONE, "Historia"));
		eventListOne.add(new Event(EventType.SHOW_QUESTION, HostId.CLIENT_ONE, HostId.SERVER, List.of("Vilken historisk person är känd som 'Il Duce'?", "Var ligger den katolska nunnan Moder Teresa begraven?", "Vad användes Judestjärnan till under Andra världskriget?")));
		eventListOne.add(new Event(EventType.SHOW_QUESTION, HostId.CLIENT_TWO, HostId.SERVER, List.of("Vilken historisk person är känd som 'Il Duce'?", "Var ligger den katolska nunnan Moder Teresa begraven?", "Vad användes Judestjärnan till under Andra världskriget?")));
		eventListOne.add(new Event(EventType.ROUND_FINISHED, HostId.SERVER, HostId.CLIENT_TWO, playerTwoAnswerResults));
		eventListOne.add(new Event(EventType.START_ROUND, HostId.SELF, HostId.SERVER, HostId.SELF));
		eventListOne.add(new Event(EventType.START_ROUND, HostId.SELF, HostId.SERVER, HostId.SELF));
		eventListOne.add(new Event(EventType.NEXT_TO_CHOOSE, HostId.CLIENT_TWO, HostId.SERVER, HostId.CLIENT_TWO));
		eventListOne.add(new Event(EventType.WAITING_FOR_CHOICE, HostId.CLIENT_TWO, HostId.SERVER, HostId.CLIENT_TWO));
		eventListOne.add(new Event(EventType.ROUND_FINISHED, HostId.SERVER, HostId.CLIENT_ONE, playerOneAnswerResults));
		eventListOne.add(new Event(EventType.ROUND_FINISHED, HostId.CLIENT_ONE, HostId.SERVER, new ArrayList<Event>()));
		eventListOne.add(new Event(EventType.ROUND_FINISHED, HostId.CLIENT_TWO, HostId.SERVER, new ArrayList<Event>()));
		eventListOne.add(new Event(EventType.START_ROUND, HostId.SELF, HostId.SERVER, HostId.SELF));
		eventListOne.add(new Event(EventType.START_ROUND, HostId.SELF, HostId.SERVER, HostId.SELF));
		eventListOne.add(new Event(EventType.NEXT_TO_CHOOSE, HostId.CLIENT_ONE, HostId.SERVER, HostId.CLIENT_ONE));
		eventListOne.add(new Event(EventType.WAITING_FOR_CHOICE, HostId.CLIENT_ONE, HostId.SERVER, HostId.CLIENT_ONE));

		eventListTwo = new ArrayList<>(eventListOne);

	}


	@Test
	void getNextToChoose() {
	}

	@Test
	void getWaitingForChoice() {
	}

	@Test
	void bothPlayersCompletedRound() {
	}

	@Test
	void getNumberOfCompletedRounds() {
	}

	@Test
	void gameIsFinished() {
	}

	@Test
	void getPlayerPointsForRound() {
	}

	@Test
	void getPointsForAllRoundsSoFar() {
	}

	@Test
	void getTotalPointsForAllRoundsSoFar() {
	}

}