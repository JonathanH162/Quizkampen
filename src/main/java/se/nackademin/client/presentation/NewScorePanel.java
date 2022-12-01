package se.nackademin.client.presentation;

import se.nackademin.core.EventLog;
import se.nackademin.core.repositories.eventrepository.models.HostId;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewScorePanel extends JPanel {

	private NewScorePanel(){}

	public static NewScorePanel create(EventLog eventLog) {
		var scorePanel = new NewScorePanel();
		var playerOneHashmap = eventLog.getPointsForAllRoundsSoFar(HostId.CLIENT_ONE);
		var playerTwoHashmap = eventLog.getPointsForAllRoundsSoFar(HostId.CLIENT_TWO);
		var playerOneLabels = createLabels(playerOneHashmap);
		var playerTwoLabels = createLabels(playerTwoHashmap);

		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(playerOneLabels.size(),1));
		topPanel.setLayout(new BorderLayout());
		bottomPanel.setLayout(new BorderLayout());
		topPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		var firstPlayerName = (eventRepository.getHostId().equals(HostId.CLIENT_ONE)) ? "Du" : "Motståndare";
		var secondPlayerName = (eventRepository.getHostId().equals(HostId.CLIENT_TWO)) ? "Du" : "Motståndare";
		JLabel firstPlayer = new JLabel(firstPlayerName);
		JLabel secondPlayer = new JLabel(secondPlayerName);
		for (int i = 1; i < playerOneLabels.size(); i++) {
			centerPanel.add(new JLabel("Runda " + i, SwingConstants.CENTER));
		}

		leftPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		rightPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		scorePanel.setLayout(new BorderLayout());
		scorePanel.add(leftPanel, BorderLayout.WEST);
		scorePanel.add(centerPanel, BorderLayout.CENTER);
		scorePanel.add(rightPanel, BorderLayout.EAST);
		scorePanel.add(topPanel, BorderLayout.NORTH);
		scorePanel.add(bottomPanel, BorderLayout.SOUTH);

		bottomPanel.add(new JLabel(String.valueOf(getTotalPoints(playerOneHashmap))), BorderLayout.WEST);
		bottomPanel.add(new JLabel(String.valueOf(getTotalPoints(playerTwoHashmap))), BorderLayout.EAST);
		bottomPanel.add(new JLabel("Totalpoäng"), BorderLayout.CENTER);

		leftPanel.setLayout(new GridLayout(playerOneLabels.size(), 1));
		rightPanel.setLayout(new GridLayout(playerTwoLabels.size(), 1));
		playerOneLabels.forEach((label) -> leftPanel.add(label));
		playerTwoLabels.forEach((label) -> rightPanel.add(label));
		return scorePanel;
	}


	private static List<JLabel> createLabels(HashMap<Integer,Integer> pointsMap) {
		return pointsMap.values().stream()
				.map((points) -> new JLabel(String.valueOf(points)))
				.toList();
	}

	private static int getTotalPoints(HashMap<Integer,Integer> pointsMap) {
		return pointsMap.values().stream().mapToInt(Integer::intValue).sum();
	}

}
