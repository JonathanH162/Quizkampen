package quizkampen.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame implements ActionListener {
    int player1RoundPoints = 0;
    int player2RoundPoints = 0;
    int player1TotalPoints = 0;
    int player2TotalPoints = 0;
    String category = "Category";
    JPanel mainPanel = new JPanel();
    JPanel namePanel = new JPanel();
    JPanel totalScorePanel = new JPanel();
    JPanel pointPanel1 = new JPanel();
    JPanel pointPanel2 = new JPanel();
    JPanel pointPanel3 = new JPanel();
    JPanel categoryAndPointPanel = new JPanel();
    JLabel nameLabel1 = new JLabel("Player 1");
    JLabel nameLabel2 = new JLabel("Player 2");
    JLabel totalScoreLabel = new JLabel("Total Score", SwingConstants.CENTER);
    JButton playButton = new JButton("Next Round");
    JLabel totalScorecounter1 = new JLabel(String.valueOf(player1TotalPoints));
    JLabel totalScorecounter2 = new JLabel(String.valueOf(player2TotalPoints));



    App() {
        this.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(namePanel, BorderLayout.NORTH);
        mainPanel.add(categoryAndPointPanel, BorderLayout.CENTER);
        mainPanel.add(playButton, BorderLayout.SOUTH);
        playButton.addActionListener(this);
        namePanel.setLayout(new BorderLayout());
        pointPanel1.setLayout(new BorderLayout());
        pointPanel2.setLayout(new BorderLayout());
        pointPanel3.setLayout(new BorderLayout());
        namePanel.add(nameLabel1, BorderLayout.WEST);
        namePanel.add(nameLabel2, BorderLayout.EAST);
        categoryAndPointPanel.setLayout(new GridLayout(4,1));
        categoryAndPointPanel.add(addPointPanel(pointPanel1));
        categoryAndPointPanel.add(addPointPanel(pointPanel2));
        categoryAndPointPanel.add(addPointPanel(pointPanel3));
        totalScorePanel.setLayout(new BorderLayout());
        totalScorePanel.add(totalScoreLabel, BorderLayout.CENTER);
        totalScorePanel.add(totalScorecounter1, BorderLayout.EAST);
        totalScorePanel.add(totalScorecounter2, BorderLayout.WEST);
        categoryAndPointPanel.add(totalScorePanel);

        setTitle("Quizkampen");
        setSize(300,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public JPanel addPointPanel(JPanel pointPanel) {
        pointPanel.add(new JLabel(String.valueOf(player1RoundPoints)),BorderLayout.WEST);
        pointPanel.add(new JLabel(String.valueOf(player2RoundPoints)),BorderLayout.EAST);
        pointPanel.add(new JLabel(category, SwingConstants.CENTER),BorderLayout.CENTER);
        return pointPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {

        }
    }

    public static class Main {
        public static void main(String[] args) {
            App app = new App();
        }
    }
}