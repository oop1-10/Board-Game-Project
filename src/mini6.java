import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class mini6 implements ActionListener {
    static Random rn = new Random();
    static JButton guess = new JButton("Guess!");
    static JTextArea guessNum = new JTextArea();
    static JLabel guessText = new JLabel();
    static JFrame minigameWindow = new JFrame();
    int randNum = rn.nextInt(1, 10);
    public static int attempts = 0;
    public static int posChange;

    mini6() {
        guess.setFocusable(false);
        guess.setBounds(100, 200, 100 ,40);
        guess.addActionListener(this);
        guess.setLayout(null);

        guessNum.setLayout(null);
        guessNum.setBounds(100, 120, 70, 20);

        guessText.setLayout(null);
        guessText.setText("<html>Guess a number<br/>between 1-10.<br/>You have 3 attempts.</html>");
        guessText.setFocusable(false);
        guessText.setBounds(70, 50, 140, 45);

        minigameWindow.add(guess);
        minigameWindow.add(guessNum);
        minigameWindow.add(guessText);

        minigameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        minigameWindow.setSize(300,300);
        minigameWindow.setLayout(null);
        minigameWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guess) {
            Random rn = new Random();
            if (attempts < 2) {
                try {
                    if (Integer.parseInt(guessNum.getText()) == randNum && Integer.parseInt(guessNum.getText()) <= 10) {
                        MainUI.event = true;
                        posChange = rn.nextInt(1, 4);
                        minigameWindow.dispose();
                        MainUI.frame.setVisible(true);
                        MainUI.updateBoard(MainUI.currentPlayer, posChange, indicatePlayers.playerInfo);
                    } else if (Integer.parseInt(guessNum.getText()) != randNum && Integer.parseInt(guessNum.getText()) <= 10) {
                        attempts++;
                        guessText.setText("<html>Guess a number<br/>between 1-10.<br/>You have " + (3-attempts) + " attempts.</html>");
                    } else {
                        guess.setText("That is not valid!");
                    }
                } catch (NumberFormatException x) {
                    guess.setText("That is not valid!");
                }
            } else {
                MainUI.event = true;
                posChange = rn.nextInt(-4, -1);
                minigameWindow.dispose();
                MainUI.frame.setVisible(true);
                MainUI.updateBoard(MainUI.pastPlayer, posChange, indicatePlayers.playerInfo);
            }
        }
    }
}