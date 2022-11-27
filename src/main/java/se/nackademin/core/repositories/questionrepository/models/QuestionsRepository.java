package se.nackademin.core.repositories.questionrepository.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class QuestionsRepository {

    @JsonDeserialize
    private List<Category> categories;

    public QuestionsRepository() {

    }

    public QuestionsRepository(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
