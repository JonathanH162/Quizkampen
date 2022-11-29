package se.nackademin.client.domain;

import se.nackademin.client.data.ClientEventRepository;
import se.nackademin.client.presentation.View;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewQuestionState implements ClientState {
    QuestionRepositoryService questionService = new QuestionRepositoryService();
    // private List<String> questions = new ArrayList<>();
    private final List<Boolean> answerResults = new ArrayList<>();
    private String currentQuestion;
    private List<String> remainingQuestions = Collections.emptyList();

    @Override
    public ClientState transitionToNextState(Event event, View view, ClientEventRepository eventRepository) {
        switch (event.getEventType()) {
            case SHOW_QUESTION -> {
                if (!remainingQuestions.isEmpty()) {
                    remainingQuestions = (List<String>) event.getData();
                }

                // Ta en fråga
                // Sätt currentQuestion till frågan
                currentQuestion = remainingQuestions.remove(0);

                // Visa frågan
                view.getQuestionLabel().setText(currentQuestion);
                view.questionScreen();

            }
            case ANSWER_CHOSEN_BUTTON -> {

                // Kolla om svaret är rätt
                var correctAnswer = questionService.getCorrectAnswer(currentQuestion);
                var result = event.getData().equals(correctAnswer);

                answerResults.add(result);

                // Uppdatera vyn baserat på om svaret var rätt eller fel
                updateViewBasedOnResult(view,event,result,correctAnswer);

                sleepFiveSeconds();
                if(remainingQuestions.isEmpty()) {
                    eventRepository.sendEvent(Event.toServer(EventType.ROUND_FINISHED,answerResults));
                } else {
                    eventRepository.sendEvent(Event.toSelf(EventType.SHOW_QUESTION));
                    return this;
                }

            }
            default -> throw new RuntimeException("Event not handled: " + event.getEventType());
        }
    }

    private void sleepFiveSeconds() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private void updateViewBasedOnResult(View view, Event event, Boolean result, String correctAnswer){
        if (result) {
            for (int i = 0; i <view.getAnswerButtonList().size() ; i++) {
                if (view.getAnswerButtonList().get(i).getText().equals(correctAnswer)) {
                    view.getAnswerButtonList().get(i).setBackground(Color.green);
                    view.revalidate();
                    view.repaint();
                    //Om det inte uppdateras ändra till självstående knappar.
                }
            }
        }
        else {
            for (int i = 0; i <view.getAnswerButtonList().size() ; i++) {
                if (view.getAnswerButtonList().get(i).getText().equals(event.getData())) {
                    view.getAnswerButtonList().get(i).setBackground(Color.red);
                    view.revalidate();
                    view.repaint();
                    //Om det inte uppdateras ändra till självstående knappar.
                }
            }
        }
    }
}
