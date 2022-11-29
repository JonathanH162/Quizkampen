package se.nackademin.server.domain;

import se.nackademin.core.utils.ConfigProperties;

public class ScoreTable {

    private ConfigProperties property;
    private boolean[][] score;

    private static int row;
    private static int column;

    public ScoreTable(ConfigProperties property) {
        row = 0;
        column = 0;
        this.property = property;
        this.score = new boolean[this.property.getNumberOfRound()][this.property.getNumberOfQuestion()];
    }

    public void print() {
        for (boolean[] row : score) {
            for (boolean col : row) {
                System.out.print(String.format("%5s", (col) ? 1 : 0));
            }
            System.out.println();
        }
    }

    public void setScore(boolean result) {
        score[row][column] = result;

        if (column < property.getNumberOfQuestion() - 1) {
            column++;
        } else if (row < property.getNumberOfRound() - 1) {
            row++;
            column = 0;
        } else {
            throw new ArrayIndexOutOfBoundsException("You have put all result in score table");
        }
    }

    public int calculate() {
        int sumOfScore = 0;
        for (int row = 0; row < property.getNumberOfRound(); row++) {
            for (int col = 0; col < property.getNumberOfQuestion(); col++) {
                if (score[row][col] == true) {
                    ++sumOfScore;
                }
            }
        }
        return sumOfScore;
    }

    public static void main(String[] args) {
        ScoreTable st = new ScoreTable(new ConfigProperties());
    }
}
