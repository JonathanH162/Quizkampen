package se.nackademin.questionRepo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class Category {

    @JsonDeserialize
    private String name;
    @JsonDeserialize
    private List<Card> cards;

    public Category() {
    }

    public Category(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
