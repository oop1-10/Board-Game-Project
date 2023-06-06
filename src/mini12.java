import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class mini12 implements ActionListener {
    static JFrame minigameWindow = new JFrame("50/50");
    JLabel text = new JLabel("<html>This is a 50/50 minigame.</br> You must pick between 2 choices, left or right.</br> If you guess correctly, you will move forward. Otherwise, you will move backwards.</html>");
    JButton right = new JButton("Right");
    JButton left = new JButton("Left");
    // hit or miss minigame
    mini12() {
        text.setLayout(null);
        text.setVisible(true);
        text.setBounds(60, 30, 150, 120);

        right.setVisible(true);
        right.setBounds(75, 180, 70, 30);
        right.addActionListener(this);
        right.setFocusable(false);

        left.setVisible(true);
        left.setBounds(155, 180, 70, 30);
        left.addActionListener(this);
        left.setFocusable(false);

        minigameWindow.add(text);
        minigameWindow.add(right);
        minigameWindow.add(left);

        minigameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        minigameWindow.setSize(300,300);
        minigameWindow.setLayout(null);
        minigameWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int posChange;
        Random rn = new Random();
        int leftOrRight = rn.nextInt(0,1);
        if (e.getSource()==left) {
            if (leftOrRight == 0) {
                posChange = rn.nextInt(1, 4);
            } else {
                posChange = rn.nextInt(-4, -1);
            }
        } else {
            if (leftOrRight == 1) {
                posChange = rn.nextInt(1, 4);
            } else {
                posChange = rn.nextInt(-4, -1);
            }
        }
        MainUI.event = true;
        MainUI.updateBoard(MainUI.pastPlayer, posChange, indicatePlayers.playerInfo);
        minigameWindow.dispose();
        MainUI.frame.setVisible(true);
    }
}