package se.nackademin.client.domain;

import se.nackademin.client.presentation.QuestionPanel;
import se.nackademin.client.presentation.View;
import se.nackademin.client.presentation.WaitingPanel;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.Event;
import se.nackademin.core.repositories.eventrepository.models.EventType;
import se.nackademin.core.repositories.questionrepository.QuestionRepositoryService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionState implements ClientState {
    QuestionRepositoryService questionService = new QuestionRepositoryService();
    private final List<Boolean> answerResults = new ArrayList<>();
    private String currentQuestion;
    private List<String> remainingQuestions = new ArrayList<>();
    private final QuestionPanel questionPanel;
    private final EventRepository eventRepository;

    public QuestionState(EventRepository eventRepository){
        this.eventRepository = eventRepository;
        this.questionPanel= new QuestionPanel(eventRepository);
    }

    @Override
    public ClientState transitionToNextState(Event event, View view, EventRepository eventRepository) {
        switch (event.getEventType()) {
            case SHOW_QUESTION -> {
                if (remainingQuestions.isEmpty()) {
                    remainingQuestions = (ArrayList<String>) event.getData();
                }

                currentQuestion = remainingQuestions.remove(0);

                questionPanel.getQuestionLabel().setText(currentQuestion);
                questionPanel.addAnswerButtonsToList(questionPanel.getAnswerButtonList(), questionService.getAllPossibleAnswers(currentQuestion));
                questionPanel.addButtonsToPanel(questionPanel.getAnswerButtonList(), questionPanel.getButtonPanel());

                view.showPanel(questionPanel);

                return this;
            }
            case ANSWER_CHOSEN_BUTTON -> {

                var correctAnswer = questionService.getCorrectAnswer(currentQuestion);
                var result = event.getData().equals(correctAnswer);

                answerResults.add(result);

                updateViewBasedOnResult(view,event,result,correctAnswer);

                sleepForAWhile();
                if(remainingQuestions.isEmpty()) {
                    eventRepository.add(Event.toServer(EventType.ROUND_FINISHED,answerResults));

                    view.showPanel(new WaitingPanel("Väntar på att motståndaren ska välja kategori."));
                    return new LobbyState(eventRepository);
                } else {
                    eventRepository.add(Event.toSelf(EventType.SHOW_QUESTION));
                    return this;
                }

            }
            default -> throw new RuntimeException("Event not handled: " + event.getEventType());
        }
    }
    private void sleepForAWhile() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
    private void updateViewBasedOnResult(View view, Event event, Boolean result, String correctAnswer){
        if (result) {
            for (int i = 0; i <questionPanel.getAnswerButtonList().size() ; i++) {
                if (questionPanel.getAnswerButtonList().get(i).getText().equals(correctAnswer)) {
                    questionPanel.getAnswerButtonList().get(i).setBackground(Color.green);
                    view.revalidate();
                    view.repaint();
                }
            }
        }
        else {
            for (int i = 0; i <questionPanel.getAnswerButtonList().size() ; i++) {
                if (questionPanel.getAnswerButtonList().get(i).getText().equals(event.getData())) {
                    questionPanel.getAnswerButtonList().get(i).setBackground(Color.red);
                    view.revalidate();
                    view.repaint();
                }
            }
        }
    }
}
