import se.nackademin.client.Client;
import se.nackademin.server.Server;

public class Tester {

	public static void main(String[] args) {
		new Thread(new Server()).start();
		sleep();
		new Thread(new Client()).start();
		sleep();
		new Thread(new Client()).start();
	}

	private static void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
