package se.nackademin.client.presentation;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LobbyPanel extends JPanel {
	private JPanel namePanel = new JPanel();
	int player1TotalPoints = 0;
	int player2TotalPoints = 0;
	Font font = new Font("", Font.PLAIN, 20);
	JPanel totalScorePanel = new JPanel();
	JPanel categoryAndPointPanel = new JPanel();
	JLabel nameLabel1 = new JLabel("Du");
	JLabel nameLabel2 = new JLabel("Motståndare");
	JLabel totalScoreLabel = new JLabel("Totalpoäng", SwingConstants.CENTER);
	JButton playButton = new JButton("Ny Runda");
	JLabel totalScoreCounter1 = new JLabel(String.valueOf(player1TotalPoints));
	JLabel totalScoreCounter2 = new JLabel(String.valueOf(player2TotalPoints));
	JPanel PlaybuttonPanel = new JPanel();

	public LobbyPanel() {
		setLayout(new BorderLayout());
		add(namePanel, BorderLayout.NORTH);
		add(categoryAndPointPanel, BorderLayout.CENTER);
		PlaybuttonPanel.add(playButton);
		add(PlaybuttonPanel, BorderLayout.SOUTH);
		namePanel.setLayout(new BorderLayout());
		namePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		namePanel.add(nameLabel1, BorderLayout.WEST);
		nameLabel1.setFont(font);
		namePanel.add(nameLabel2, BorderLayout.EAST);
		nameLabel2.setFont(font);
		categoryAndPointPanel.setLayout(new GridLayout(4, 1));
//		pointPanel1.add(new JLabel(String.valueOf(player1Round1Points)), BorderLayout.WEST);
//		pointPanel1.add(new JLabel(String.valueOf(player2Round1Points)), BorderLayout.EAST);
//		pointPanel1.add(new JLabel("category", SwingConstants.CENTER), BorderLayout.CENTER);
//		pointPanel1.setBorder(new EmptyBorder(10, 30, 10, 30));
//		categoryAndPointPanel.add(pointPanel1);
//		pointPanel2.add(new JLabel(String.valueOf(player1Round2Points)), BorderLayout.WEST);
//		pointPanel2.add(new JLabel(String.valueOf(player2Round2Points)), BorderLayout.EAST);
//		pointPanel2.add(new JLabel("category", SwingConstants.CENTER), BorderLayout.CENTER);
//		pointPanel2.setBorder(new EmptyBorder(10, 30, 10, 30));
//		categoryAndPointPanel.add(pointPanel2);
//		pointPanel3.add(new JLabel(String.valueOf(player1Round3Points)), BorderLayout.WEST);
//		pointPanel3.add(new JLabel(String.valueOf(player2Round3Points)), BorderLayout.EAST);
//		pointPanel3.add(new JLabel("category", SwingConstants.CENTER), BorderLayout.CENTER);
//		pointPanel3.setBorder(new EmptyBorder(10, 30, 10, 30));
//		categoryAndPointPanel.add(pointPanel3);
		totalScorePanel.setLayout(new BorderLayout());
		totalScorePanel.add(totalScoreLabel, BorderLayout.CENTER);
		totalScorePanel.add(totalScoreCounter1, BorderLayout.EAST);
		totalScorePanel.add(totalScoreCounter2, BorderLayout.WEST);
		totalScorePanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		categoryAndPointPanel.add(totalScorePanel);
		revalidate();
		repaint();
	}
	public void addPointPanel(JPanel pointPanel, int player1RoundPoints, int player2RoundPoints, String categoryName) {
		pointPanel.setLayout(new BorderLayout());
		pointPanel.add(new JLabel(String.valueOf(player1RoundPoints)), BorderLayout.WEST);
		pointPanel.add(new JLabel(String.valueOf(player2RoundPoints)), BorderLayout.EAST);
		pointPanel.add(new JLabel(categoryName, SwingConstants.CENTER), BorderLayout.CENTER);
		pointPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
		categoryAndPointPanel.add(pointPanel);
	}
	public JButton getPlayButton() {
		return playButton;
	}
	public void setPlayButtonListener(ActionListener actionListener) {
		playButton.addActionListener(actionListener);
	}
	public void setPlayer1TotalPoints(int points) {
		this.player1TotalPoints = points;
	}
	public void setPlayer2TotalPoints(int points) {
		this.player2TotalPoints = points;
	}
	public JLabel getTotalScoreCounter1() {
		return totalScoreCounter1;
	}
	public JLabel getTotalScoreCounter2() {
		return totalScoreCounter2;
	}
	public void setPlayerSum(int player, int sum) {
		if (player == 1) {
			totalScoreCounter1.setText(String.valueOf(sum));
		} else if (player == 2) {
			totalScoreCounter2.setText(String.valueOf(sum));
		}

	}
	public JPanel getPlaybuttonPanel () {
		return getPlaybuttonPanel();
	}
}
