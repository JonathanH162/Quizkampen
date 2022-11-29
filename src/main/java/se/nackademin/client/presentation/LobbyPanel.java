package se.nackademin.client.presentation;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends JPanel {
	private JPanel namePanel = new JPanel();
	int player1RoundPoints = 0;
	int player2RoundPoints = 0;
	int player1TotalPoints = 0;
	int player2TotalPoints = 0;
	Font font = new Font("", Font.PLAIN, 20);
	JPanel totalScorePanel = new JPanel();
	JPanel pointPanel1 = new JPanel();
	JPanel pointPanel2 = new JPanel();
	JPanel pointPanel3 = new JPanel();
	JPanel categoryAndPointPanel = new JPanel();
	JLabel nameLabel1 = new JLabel("Spelare 1");
	JLabel nameLabel2 = new JLabel("Spelare 2");
	JLabel totalScoreLabel = new JLabel("Totalpoäng", SwingConstants.CENTER);
	JButton playButton = new JButton("Ny Runda");
	JLabel totalScoreCounter1 = new JLabel(String.valueOf(player1TotalPoints));
	JLabel totalScoreCounter2 = new JLabel(String.valueOf(player2TotalPoints));

	public LobbyPanel() {
		setLayout(new BorderLayout());
		add(namePanel, BorderLayout.NORTH);
		add(categoryAndPointPanel, BorderLayout.CENTER);
		add(playButton, BorderLayout.SOUTH);
		namePanel.setLayout(new BorderLayout());
		namePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		pointPanel1.setLayout(new BorderLayout());
		pointPanel2.setLayout(new BorderLayout());
		pointPanel3.setLayout(new BorderLayout());
		namePanel.add(nameLabel1, BorderLayout.WEST);
		nameLabel1.setFont(font);
		namePanel.add(nameLabel2, BorderLayout.EAST);
		nameLabel2.setFont(font);
		categoryAndPointPanel.setLayout(new GridLayout(4, 1));
		categoryAndPointPanel.add(addPointPanel(pointPanel1));
		categoryAndPointPanel.add(addPointPanel(pointPanel2));
		categoryAndPointPanel.add(addPointPanel(pointPanel3));
		totalScorePanel.setLayout(new BorderLayout());
		totalScorePanel.add(totalScoreLabel, BorderLayout.CENTER);
		totalScorePanel.add(totalScoreCounter1, BorderLayout.EAST);
		totalScorePanel.add(totalScoreCounter2, BorderLayout.WEST);
		totalScorePanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		categoryAndPointPanel.add(totalScorePanel);
	}
	public JPanel addPointPanel(JPanel pointPanel) {
		pointPanel.add(new JLabel(String.valueOf(player1RoundPoints)), BorderLayout.WEST);
		pointPanel.add(new JLabel(String.valueOf(player2RoundPoints)), BorderLayout.EAST);
		pointPanel.add(new JLabel("category", SwingConstants.CENTER), BorderLayout.CENTER);
		pointPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		return pointPanel;
	}
	public JButton getPlayButton() {
		return playButton;
	}
	public void setPlayButtonListener(ActionListener actionListener) {
		playButton.addActionListener(actionListener);
	}
}
