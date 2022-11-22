package se.nackademin.questionRepo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundManagerTest {

    RoundManager roundManager = new RoundManager();
    @Test
    void name() {
        roundManager.getRoundQuestions("Historia").forEach(item -> System.out.println(item.getQuestion()));

    }
}