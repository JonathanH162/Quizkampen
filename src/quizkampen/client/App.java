package quizkampen.client;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    JPanel mainPanel = new JPanel();
    JPanel player1Panel = new JPanel();
    JPanel player2Panel = new JPanel();
    JLabel nameLabel1 = new JLabel("Spelare 1");
    JLabel nameLabel2 = new JLabel("Spelare 2");
    JButton playButton = new JButton("Next Round");
    int player1Points = 0;
    int player2Points = 0;

    JLabel pointLabel1 = new JLabel(String.valueOf(player1Points), SwingConstants.CENTER);
    JLabel pointLabel2 = new JLabel(String.valueOf(player2Points), SwingConstants.CENTER);


    App() {
        this.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(player1Panel, BorderLayout.EAST);
        mainPanel.add(player2Panel, BorderLayout.WEST);
        mainPanel.add(playButton, BorderLayout.SOUTH);
        player1Panel.setLayout(new GridLayout(2,1));
        player2Panel.setLayout(new GridLayout(2,1));
        player1Panel.add(nameLabel1);
        player1Panel.add(pointLabel1);
        player2Panel.add(nameLabel2);
        player2Panel.add(pointLabel2);


        setSize(300,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static class Main {
        public static void main(String[] args) {
            App app = new App();
        }
    }
}
