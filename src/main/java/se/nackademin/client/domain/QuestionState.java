package se.nackademin.client.domain;

import com.fasterxml.jackson.core.JsonParser;
import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;
import se.nackademin.core.repositories.questionrepository.models.Category;
import se.nackademin.core.repositories.questionrepository.models.QuestionsRepository;
import se.nackademin.core.utils.ConfigProperties;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionState implements ClientState {

	private final QuestionRepositoryService questionService = new QuestionRepositoryService();
	private final ConfigProperties configProperties = new ConfigProperties();

	@Override
	public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
		switch (event.getEventType()) {
			case CATEGORY_CHOSEN -> {

				// GET CATEGORY FROM EVENT
				var category = (String) event.getData();

				// GET QUESTIONS FROM THAT CATEGORY
				var questions = questionService.getAllQuestionInCategory(category);

				// GET THE NUMBER OF QUESTIONS FROM CONFIG
				var numberOfQuestionsNeeded = configProperties.getNumberOfQuestion();

				// Randomize the list of questions
				Collections.shuffle(questions);

				// Take the needed number of questions
				var questionsToBeUsed = questions.subList(0, numberOfQuestionsNeeded - 1);

				// TODO rest of steps
				//Choose a question to be used
				String question1 = questions.get(0);
				String question2 = questions.get(1);
				String question3 = questions.get(2);

				// SET QUESTIONLABEL TO QUESTION
				view.getQuestionLabel().setText("");
				//View fixar med svaren till respektive fråga.
				view.questionScreen();

				// SET ACTIONLISTENERS -> WRONG/RIGHT ANSWER
				questionService.getCorrectAnswer("");
				questionService.getAllPossibleAnswers("");

				// SET ACTIONLISTENER -> NEXT QUESTION


				//eventRepository.sendEvent(Event.toServer(EventType.ROUND_FINISHED));
				eventRepository.sendEvent(Event.toSelf(EventType.WAITING_FOR_CHOICE));
				return new LobbyState();

			}
			case WAITING_FOR_CHOICE -> {
				//view.waitForCategory();
				return new LobbyState();
				// TODO Informera användaren att motståndaren väljer kategori.
				//view.waitForCategory();
			}
			default -> throw new RuntimeException("Event not handled: " + event.getEventType());
		}
	}
	}
