package se.nackademin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.nackademin.client.Client;

public class TestLog {
	private static Logger logger = LogManager.getLogger(TestLog.class);
	//private static Logger logger = LogManager.getLogger("se.nackademin");

	public static void main(String[] args) {
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.error("Error log message");
	}

	public static Logger getLogger(String name) {
		return logger;
	}

}
