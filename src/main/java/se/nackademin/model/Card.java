package se.nackademin.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class Card {

    @JsonDeserialize
    private String question;
    @JsonDeserialize
    private List<String> possibleAnswer;
    @JsonDeserialize
    private String correctAnswer;

    public Card() {

    }

    public Card(String question, List<String> possibleAnswer, String correctAnswer) {
        this.question = question;
        this.possibleAnswer = possibleAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getPossibleAnswer() {
        return possibleAnswer;
    }

    public void setPossibleAnswer(List<String> possibleAnswer) {
        this.possibleAnswer = possibleAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
