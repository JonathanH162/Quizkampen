package se.nackademin.server.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.nackademin.server.domain.ScoreTable;
import se.nackademin.common.ConfigProperty;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ScoreTableTest {

    ScoreTable sut;

    @Mock
    private ConfigProperty property;

    @BeforeEach
    void setUp() {
        doReturn(3).when(property).getNumberOfRound();
        doReturn(7).when(property).getNumberOfQuestion();
    }

    @Test
    void should_print_correct_amount_of_property_values() {
        sut = new ScoreTable(property);
        sut.print();
    }

    @Test
    void should_set_correctly() {
        sut = new ScoreTable(property);
        int tableCells = property.getNumberOfRound() * property.getNumberOfQuestion();

        Random random = new Random();
        for (int i = 0; i < tableCells-1; i++) {
            sut.setScore(random.nextBoolean());
        }
        sut.print();
    }

    @Test
    void throw_an_exception_if_setScore_calls_after_that_all_question_are_finished() {
        sut = new ScoreTable(property);
        int tableCells = property.getNumberOfRound() * property.getNumberOfQuestion();

        Random random = new Random();
        for (int i = 0; i < tableCells-1; i++) {
            sut.setScore(random.nextBoolean());
        }

        assertThrows(ArrayIndexOutOfBoundsException.class,()-> sut.setScore(true));
    }

    @Test
    void should_calculate_correct_total_score() {
        //given
        int expectedScore = 5;
        sut = initialTable(expectedScore);
        sut.print();

        //When
        int actualScore = sut.calculate();
        //Then
        assertEquals(expectedScore, actualScore);


    }

    private ScoreTable initialTable(int trueAmount) {
        ScoreTable scoreTable = new ScoreTable(property);
        for (int i = 0; i < trueAmount; i++) {
            scoreTable.setScore(true);
        }
        return scoreTable;
    }
}