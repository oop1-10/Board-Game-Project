import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class mini9 implements ActionListener {
    static Random rn = new Random();
    static JButton guess = new JButton("Guess!");
    static JTextArea guessNum = new JTextArea();
    static JTextArea guessText = new JTextArea();
    static JFrame minigameWindow = new JFrame();
    int randNum = rn.nextInt(1, 10);

    mini9() {
        guess.setFocusable(false);
        guess.setBounds(100, 300, 100 ,40);
        guess.addActionListener(this);
        guess.setLayout(null);

        guessNum.setLayout(null);
        guessNum.setBounds(100, 150, 100, 40);

        guessText.setLayout(null);
        guessText.setText("Guess a number between 1-10.");
        guessText.setEditable(false);
        guessText.setBounds(100, 50, 100, 30);

        minigameWindow.add(guess);
        minigameWindow.add(guessNum);
        minigameWindow.add(guessText);

        minigameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        minigameWindow.setSize(400,400);
        minigameWindow.setLayout(null);
        minigameWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guess) {
            int posChange;
            Random rn = new Random();
            try {
                if (Integer.parseInt(guessNum.getText()) == randNum) {
                    posChange = rn.nextInt(1,4);
                    MainUI.updateBoard(MainUI.currentPlayer, posChange, indicatePlayers.playerInfo);
                    minigameWindow.dispose();
                    MainUI again = new MainUI();
                } else if (Integer.parseInt(guessNum.getText()) != randNum) {
                    posChange = rn.nextInt(-3, -1);
                    MainUI.updateBoard(MainUI.currentPlayer, posChange, indicatePlayers.playerInfo);
                    minigameWindow.dispose();
                    MainUI again = new MainUI();
                }
            } catch (NumberFormatException x) {
                guess.setText("That is not valid!");
            }
        }
    }
}
