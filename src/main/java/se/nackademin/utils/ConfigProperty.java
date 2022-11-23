package se.nackademin.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperty {

    static final String CONFIG_FILE = ConfigProperty.class.getClassLoader().getResource("config.properties").getFile();
    private int numberOfQuestion;
    private int numberOfRound;

    private Properties p;

    public ConfigProperty() {
        p = new Properties();
        try {
            p.load(new FileInputStream(CONFIG_FILE));
            numberOfQuestion = Integer.parseInt(p.getProperty("number_of_question"));
            numberOfRound = Integer.parseInt(p.getProperty("number_of_round"));
        } catch (IOException e) {
            System.out.println("Property file dos not exist!");
        }
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public int getNumberOfRound() {
        return numberOfRound;
    }
}
