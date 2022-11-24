package se.nackademin.client;

import org.apache.logging.log4j.Level;
import se.nackademin.TestLog;
import se.nackademin.client.states.ClientStateMachine;
import se.nackademin.client.states.InitialState;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Client {

	private final int port = 1337;
	private static final Logger logger = LogManager.getLogger(Client.class);
	//private static Logger logger = LogManager.getRootLogger();

	public static void main(String[] args) {
		new Client().run();
	}

	private void run() {

		//logger.debug("Debug log message");
		//logger.debug("{}", this::blabla);

		try {
			int i = 1/0;
			System.out.println(i);

		} catch (ArithmeticException e) {
			logger.debug("{}", e);
		}

		new ClientStateMachine(new InitialState()).run();
	}

}

/*		System.out.println("Client started.");
		try (var socket = new Socket(InetAddress.getLocalHost().getHostAddress(), port)) {
			while (true) {

				System.out.println("Assigning IO streams.");
				var eventManager = new ClientEventManager(socket);

				System.out.println("Starting protocol thread.");
				var initialState = new InitialState(eventManager);
				new Thread(new Protocol(initialState)).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}*/



