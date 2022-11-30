package se.nackademin.client.presentation;

import se.nackademin.core.repositories.eventrepository.models.HostId;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class ScorePanel extends JPanel {
    private final RoundPanel round1;
    private final RoundPanel round2;
    private final RoundPanel round3;

    public ScorePanel() {
        setLayout(new GridLayout(3, 1));
        round1 = new RoundPanel();
        round2 = new RoundPanel();
        round3 = new RoundPanel();

        add(round1);
        add(round2);
        add(round3);
    }
    public void setRound(int roundNumber, int p1, int p2, String cat) {
        switch (roundNumber) {
            case 1 -> round1.setRoundPoints(p1, p2, cat);
            case 2 -> round2.setRoundPoints(p1, p2, cat);
            case 3 -> round3.setRoundPoints(p1, p2, cat);
        }
        revalidate();
        repaint();
    }
    public void display(HashMap<HostId, List<Integer>> tempMap, HostId playerID) {
        var thisPlayerPoints = tempMap.get(playerID);
        HostId otherPlayerID = HostId.EMPTY;
        for (HostId key : tempMap.keySet()) {
            if (key != playerID) {
                otherPlayerID = key;
            }
        }
        var otherPlayerPoints = tempMap.get(otherPlayerID);
        var thisPlayerPointsLastRound = thisPlayerPoints.get(thisPlayerPoints.size()-1);
        var otherPlayerPointsLastRound = otherPlayerPoints.get(otherPlayerPoints.size()-1);

    }
}
