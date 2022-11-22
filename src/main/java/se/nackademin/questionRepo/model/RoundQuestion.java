package se.nackademin.questionRepo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundQuestion {

    private String question;

    private String correctAnswer;
    private List<String> answerAlternative;

    public RoundQuestion(String question, List<String> answerAlternative, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answerAlternative = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswerAlternative() {
        Collections.shuffle(answerAlternative);
        return answerAlternative;
    }

    public void setAnswerAlternative(List<String> answerAlternative) {
        this.answerAlternative = answerAlternative;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
