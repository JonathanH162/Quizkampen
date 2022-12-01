package se.nackademin.client.presentation;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {
    private final JLabel playerOnePoints;
    private final JLabel playerTwoPoints;
    private final JLabel category;

    public RoundPanel() {
        setLayout(new GridLayout(1,3));
        playerOnePoints = new JLabel("");
        playerTwoPoints = new JLabel("");
        category = new JLabel("");

        add(playerOnePoints);
        add(category);
        add(playerTwoPoints);

    }

    public void setRoundPoints(int p1, int p2, String cat) {
        playerOnePoints.setText(String.valueOf(p1));
        playerTwoPoints.setText(String.valueOf(p2));
        category.setText(cat);
        revalidate();
        repaint();
    }
}
