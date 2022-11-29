package se.nackademin.core.repositories.questionrepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.nackademin.core.repositories.questionrepository.models.Card;
import se.nackademin.core.repositories.questionrepository.models.Category;
import se.nackademin.core.repositories.questionrepository.models.QuestionsRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionRepositoryService {

    private List<Category> categories;

    public QuestionRepositoryService() {
        categories = getCategories("categories.json");
    }

    public List<Category> getCategories(String jsonFileName) {
        URL resource = this.getClass().getClassLoader().getResource(jsonFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionsRepository questionsRepository = null;
        try {
            questionsRepository = objectMapper.readValue(resource, QuestionsRepository.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionsRepository.getCategories();
    }

    public List<Category> getCategories() {
        return getCategories("categories.json");
    }

    public List<String> getAllCategoriesName() {
        List<String> categoriesName = new ArrayList<>();
        for (Category c : categories) {
            categoriesName.add(c.getName());
        }
        return categoriesName;
    }

    public List<String> getAllQuestionInCategory(String categoryName) {
        List<String> questions = new ArrayList<>();
        for (Category c : categories) {
            if (c.getName().equalsIgnoreCase(categoryName)) {
                for (Card s : c.getCards()) {
                    questions.add(s.getQuestion());
                }
            }
        }
        Collections.shuffle(questions);
        return questions;
    }
    public List<String> getAllPossibleAnswers(String question){
        for (Category category : categories) {
            for (Card card: category.getCards()) {
                if(card.getQuestion().equalsIgnoreCase(question)){
                    return card.getPossibleAnswer();
                }
            }
        }
        return null;
    }

    public String getCorrectAnswer(String question){
        for (Category category : categories) {
            for (Card card: category.getCards()) {
                if(card.getQuestion().equalsIgnoreCase(question)){
                    return card.getCorrectAnswer();
                }
            }
        }
        return null;
    }
}