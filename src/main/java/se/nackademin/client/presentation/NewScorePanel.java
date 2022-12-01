package se.nackademin.client.presentation;

import se.nackademin.core.EventLog;
import se.nackademin.core.repositories.eventrepository.models.HostId;

import javax.swing.*;
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
		// scorePanel.addLabelsToPanel(playerOneLabels);
		// scorePanel.addLabelsToPanel(playerTwoLabels);
		// scorePanel.setLayout(new GridLayout(5,2));

		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		left
		scorePanel.setLayout(new BorderLayout());
		scorePanel.add(leftPanel, BorderLayout.WEST);
		scorePanel.add(centerPanel, BorderLayout.CENTER);
		scorePanel.add(rightPanel, BorderLayout.EAST);

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

	private void addLabelsToPanel(List<JLabel> labels) {
		for (JLabel label : labels) {
			add(label);
		}
	}
}
