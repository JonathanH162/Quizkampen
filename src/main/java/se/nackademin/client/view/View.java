package se.nackademin.client.view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

import se.nackademin.io.Event;
import se.nackademin.io.EventType;
import se.nackademin.io.HostId;

public class View extends JFrame {
	private JPanel mainPanel = new JPanel();
	JButton playButton = new JButton("Nytt spel");
	JLabel welcomeLabel = new JLabel("Välkommen till Quizkampen");
	BlockingQueue<Event> eventQueue;

	public View(BlockingQueue<Event> eventQueue) throws HeadlessException {
		this.eventQueue = eventQueue;
		add(mainPanel);
		mainPanel.add(welcomeLabel);
		mainPanel.add(playButton);

		playButton.addActionListener(e -> {
			var event = new Event(EventType.START_BUTTON_PRESSED, HostId.EMPTY,HostId.EMPTY,new Object());
			try {
				eventQueue.put(event);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		});

		setTitle("Quizkampen");
		setSize(350,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
