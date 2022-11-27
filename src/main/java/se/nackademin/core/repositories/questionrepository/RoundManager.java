package se.nackademin.core.repositories.questionrepository;

import se.nackademin.core.repositories.questionrepository.models.RoundQuestion;
import se.nackademin.core.utils.ConfigProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundManager {

    private ConfigProperties property;
    private QuestionRepositoryService service;

    public RoundManager() {
        this.property = new ConfigProperties();
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
