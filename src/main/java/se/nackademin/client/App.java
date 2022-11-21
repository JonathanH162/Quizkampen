package se.nackademin.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame implements ActionListener {
    int player1RoundPoints = 0;
    int player2RoundPoints = 0;
    int player1TotalPoints = 0;
    int player2TotalPoints = 0;
    String category = "Category";
    Font font = new Font("",Font.PLAIN,20);
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
    JLabel totalScoreCounter1 = new JLabel(String.valueOf(player1TotalPoints));
    JLabel totalScoreCounter2 = new JLabel(String.valueOf(player2TotalPoints));
    JPanel answerButtonPanel = new JPanel();
    JLabel questionLabel = new JLabel("Question", SwingConstants.CENTER);
    JButton answerButton1 = new JButton("Answer 1");
    JButton answerButton2 = new JButton("Answer 2");
    JButton answerButton3 = new JButton("Answer 3");
    JButton answerButton4 = new JButton("Answer 4");
    JPanel categoryButtonPanel = new JPanel();
    JLabel categoryLabel = new JLabel("Choose a Category!", SwingConstants.CENTER);
    JButton categoryButton1 = new JButton("Category 1");
    JButton categoryButton2 = new JButton("Category 2");
    JButton categoryButton3 = new JButton("Category 3");
    JButton categoryButton4 = new JButton("Category 4");
    JButton categoryButton5 = new JButton("Category 5");
    JButton categoryButton6 = new JButton("Category 6");



    App() {
        this.add(mainPanel);
        appScoreBoard();
    }
    public void appScoreBoard () {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(namePanel, BorderLayout.NORTH);
        mainPanel.add(categoryAndPointPanel, BorderLayout.CENTER);
        mainPanel.add(playButton, BorderLayout.SOUTH);
        playButton.addActionListener(this);
        namePanel.setLayout(new BorderLayout());
        namePanel.setBorder(new EmptyBorder(10,10,10,10));
        pointPanel1.setLayout(new BorderLayout());
        pointPanel2.setLayout(new BorderLayout());
        pointPanel3.setLayout(new BorderLayout());
        namePanel.add(nameLabel1, BorderLayout.WEST);
        nameLabel1.setFont(font);
        namePanel.add(nameLabel2, BorderLayout.EAST);
        nameLabel2.setFont(font);
        categoryAndPointPanel.setLayout(new GridLayout(4,1));
        categoryAndPointPanel.add(addPointPanel(pointPanel1));
        categoryAndPointPanel.add(addPointPanel(pointPanel2));
        categoryAndPointPanel.add(addPointPanel(pointPanel3));
        totalScorePanel.setLayout(new BorderLayout());
        totalScorePanel.add(totalScoreLabel, BorderLayout.CENTER);
        totalScorePanel.add(totalScoreCounter1, BorderLayout.EAST);
        totalScorePanel.add(totalScoreCounter2, BorderLayout.WEST);
        totalScorePanel.setBorder(new EmptyBorder(10,30,10,30));
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
        pointPanel.setBorder(new EmptyBorder(10,30,10,30));
        return pointPanel;
    }
    public void appQuestions () {
        questionLabel.setFont(font);
        mainPanel.add(questionLabel, BorderLayout.NORTH);
        mainPanel.add(answerButtonPanel, BorderLayout.CENTER);
        answerButtonPanel.setLayout(new FlowLayout());
        answerButtonPanel.add(answerButton1);
        answerButton1.addActionListener(this);
        answerButton1.setPreferredSize(new Dimension(100,100));
        answerButtonPanel.add(answerButton2);
        answerButton2.addActionListener(this);
        answerButton2.setPreferredSize(new Dimension(100,100));
        answerButtonPanel.add(answerButton3);
        answerButton3.addActionListener(this);
        answerButton3.setPreferredSize(new Dimension(100,100));
        answerButtonPanel.add(answerButton4);
        answerButton4.addActionListener(this);
        answerButton4.setPreferredSize(new Dimension(100,100));

        setVisible(true);
    }
    public void appCategories () {
        categoryLabel.setFont(font);
        mainPanel.add(categoryLabel, BorderLayout.NORTH);
        mainPanel.add(categoryButtonPanel, BorderLayout.CENTER);
        categoryButtonPanel.setLayout(new FlowLayout());
        categoryButtonPanel.add(categoryButton1);
        categoryButton1.addActionListener(this);
        categoryButton1.setPreferredSize(new Dimension(100,100));
        categoryButtonPanel.add(categoryButton2);
        categoryButton2.addActionListener(this);
        categoryButton2.setPreferredSize(new Dimension(100,100));
        categoryButtonPanel.add(categoryButton3);
        categoryButton3.addActionListener(this);
        categoryButton3.setPreferredSize(new Dimension(100,100));
        categoryButtonPanel.add(categoryButton4);
        categoryButton4.addActionListener(this);
        categoryButton4.setPreferredSize(new Dimension(100,100));
        categoryButtonPanel.add(categoryButton5);
        categoryButton5.addActionListener(this);
        categoryButton5.setPreferredSize(new Dimension(100,100));
        categoryButtonPanel.add(categoryButton6);
        categoryButton6.addActionListener(this);
        categoryButton6.setPreferredSize(new Dimension(100,100));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            mainPanel.remove(namePanel);
            mainPanel.remove(categoryAndPointPanel);
            mainPanel.remove(playButton);
            appCategories();
            repaint();
            revalidate();
        }
        else if (e.getSource() == categoryButton1 || e.getSource() == categoryButton2 || e.getSource() == categoryButton3
                || e.getSource() == categoryButton4 || e.getSource() == categoryButton5 || e.getSource() == categoryButton6) {
            mainPanel.remove(categoryLabel);
            mainPanel.remove(categoryButtonPanel);
            appQuestions();
            repaint();
            revalidate();
        }
        else if (e.getSource() == answerButton1 ||e.getSource() == answerButton2 ||e.getSource() == answerButton3 ||e.getSource() == answerButton4) {
            mainPanel.remove(questionLabel);
            mainPanel.remove(answerButtonPanel);
            appScoreBoard();
            repaint();
            revalidate();
        }
    }

    public static class Main {
        public static void main(String[] args) {
            App app = new App();
        }
    }
}