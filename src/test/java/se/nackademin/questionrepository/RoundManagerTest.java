package se.nackademin.questionrepository;

import org.junit.jupiter.api.Test;
import se.nackademin.repositories.questionrepository.RoundManager;

class RoundManagerTest {

    RoundManager roundManager = new RoundManager();
    @Test
    void name() {
        roundManager.getRoundQuestions("Historia").forEach(item -> System.out.println(item.getQuestion()));

    }
}