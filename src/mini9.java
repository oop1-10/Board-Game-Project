import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class mini9 implements ActionListener {
    static Random rn = new Random();
    static JButton guess = new JButton("Guess!");
    static JTextArea guessNum = new JTextArea();
    static JLabel guessText = new JLabel();
    static JFrame minigameWindow = new JFrame();
    int randNum = rn.nextInt(1, 10);
    int attempts = 0;

    mini9() {
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
            int posChange;
            Random rn = new Random();
            if (attempts < 3) {
                try {
                    if (Integer.parseInt(guessNum.getText()) == randNum && Integer.parseInt(guessNum.getText()) <= 10) {
                        posChange = rn.nextInt(1, 4);
                        MainUI.updateBoard(MainUI.currentPlayer, posChange, indicatePlayers.playerInfo);
                        minigameWindow.dispose();
                        MainUI again = new MainUI();
                    } else if (Integer.parseInt(guessNum.getText()) != randNum && Integer.parseInt(guessNum.getText()) <= 10) {
                        attempts++;
                    } else {
                        guess.setText("That is not valid!");
                    }
                } catch (NumberFormatException x) {
                    guess.setText("That is not valid!");
                }
            } else {
                posChange = rn.nextInt(-3, -1);
                MainUI.updateBoard(MainUI.currentPlayer, posChange, indicatePlayers.playerInfo);
                minigameWindow.dispose();
                MainUI again = new MainUI();
            }
        }
    }
}
