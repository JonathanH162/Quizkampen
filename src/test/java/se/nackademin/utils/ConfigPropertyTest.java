package se.nackademin.utils;

import org.junit.jupiter.api.Test;
import se.nackademin.core.utils.ConfigProperty;

import static org.junit.jupiter.api.Assertions.*;

class ConfigPropertyTest {

    ConfigProperty sut = new ConfigProperty();

    @Test
    void should_read_correct_value_of_config_properties_file() {
        int actualNumberOfQuestion = sut.getNumberOfQuestion();
        int actualNumberOfRound = sut.getNumberOfRound();

        assertEquals(2, actualNumberOfQuestion);
        assertEquals(3, actualNumberOfRound);

    }
}