package se.nackademin.client.presentation;

import se.nackademin.core.EventLog;
import se.nackademin.core.repositories.eventrepository.EventRepository;
import se.nackademin.core.repositories.eventrepository.models.HostId;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewScorePanel extends JPanel {

	private NewScorePanel(){}

	public static NewScorePanel create(EventLog eventLog, EventRepository eventRepository) {
		var scorePanel = new NewScorePanel();
		var playerOneHashmap = eventLog.getPointsForAllRoundsSoFar(HostId.CLIENT_ONE);
		var playerTwoHashmap = eventLog.getPointsForAllRoundsSoFar(HostId.CLIENT_TWO);
		var playerOneLabels = createLabels(playerOneHashmap);
		var playerTwoLabels = createLabels(playerTwoHashmap);
		var playerOneTotalPoints = getTotalPoints(playerOneHashmap);
		var playerTwoTotalPoints = getTotalPoints(playerTwoHashmap);

		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		centerPanel.setLayout(new GridLayout(playerOneLabels.size(),1));
		topPanel.setLayout(new BorderLayout());
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		topPanel.setBorder(new EmptyBorder(10, 30, 10, 30));


		var firstPlayerName = (eventRepository.getHostId().equals(HostId.CLIENT_ONE)) ? "Du" : "Motståndare";
		var secondPlayerName = (eventRepository.getHostId().equals(HostId.CLIENT_TWO)) ? "Du" : "Motståndare";




		if (eventLog.gameIsFinished()) {

			if (playerOneTotalPoints == playerTwoTotalPoints){
				firstPlayerName = "OAVGJORT";
				secondPlayerName = "OAVGJORT";

			}
			if (playerOneTotalPoints > playerTwoTotalPoints){
				firstPlayerName = "VINNARE";
				secondPlayerName = "FÖRLORARE";

			}
			if (playerOneTotalPoints < playerTwoTotalPoints) {
				firstPlayerName = "FÖRLORARE";
				secondPlayerName = "VINNARE";
			}
		}

		JLabel firstPlayer = new JLabel(firstPlayerName);
		JLabel secondPlayer = new JLabel(secondPlayerName);



		for (int i = 1; i < playerOneLabels.size() + 1; i++) {
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


		bottomPanel.add(new JLabel(String.valueOf(playerOneTotalPoints)), BorderLayout.WEST);
		bottomPanel.add(new JLabel(String.valueOf(playerTwoTotalPoints)), BorderLayout.EAST);
		bottomPanel.add(new JLabel("Totalpoäng", SwingConstants.CENTER), BorderLayout.CENTER);


		leftPanel.setLayout(new GridLayout(playerOneLabels.size(), 1));
		rightPanel.setLayout(new GridLayout(playerTwoLabels.size(), 1));
		topPanel.add(firstPlayer, BorderLayout.WEST);
		topPanel.add(secondPlayer, BorderLayout.EAST);

		playerOneLabels.forEach(leftPanel::add);
		playerTwoLabels.forEach(rightPanel::add);

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
