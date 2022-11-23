package se.nackademin.questionRepo;

import se.nackademin.questionRepo.model.RoundQuestion;
import se.nackademin.utils.ConfigProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundManager {

    private ConfigProperty property;
    private QuestionRepositoryService service;

    public RoundManager() {
        this.property = new ConfigProperty();
        this.service = new QuestionRepositoryService();
    }

    public List<RoundQuestion> getRoundQuestions(String categoryName) {
        List<RoundQuestion> roundQuestions = new ArrayList<>();

        List<String> allQuestionInCategory = service.getAllQuestionInCategory(categoryName);
        Collections.shuffle(allQuestionInCategory);

        for (int i = 0; i < property.getNumberOfQuestion(); i++) {
            String question = allQuestionInCategory.get(i);
            roundQuestions.add(new RoundQuestion(question, service.getAllPossibleAnswers(question), service.getCorrectAnswer(question)));
        }

        return roundQuestions;
    }
}
