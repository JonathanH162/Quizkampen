package se.nackademin.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Properties;

public class ConfigProperties {

    static final String CONFIG_FILE = Objects.requireNonNull(ConfigProperties.class.getClassLoader().getResource("config.properties")).getFile();
    private final Properties properties = new Properties();
    private static final Logger logger = LogManager.getLogger(ConfigProperties.class);

    public ConfigProperties() {
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            logger.info("Could not load the properties file. Will use default values.");
        }
    }

    public int getNumberOfQuestion() {
        return Integer.parseInt((String) properties.getOrDefault("number_of_question", 1));
    }

    public int getNumberOfRound() {
        return Integer.parseInt((String) properties.getOrDefault("number_of_round", 2));
    }

    public InetAddress getServerIp() {
        try {
            return InetAddress.getByName((String) properties.getOrDefault("server_ip", "127.0.0.1"));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public int getServerPort() {
        return Integer.parseInt((String) properties.getOrDefault("server_port", 1337));
    }
}
