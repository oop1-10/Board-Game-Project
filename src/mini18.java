import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class mini18 implements ActionListener {
    static int interval;
    static Timer timer;
    static JFrame minigameWindow = new JFrame();
    public static StringBuilder memoryString = new StringBuilder();
    JButton start = new JButton("Start");
    static JButton confirm = new JButton("Confirm");
    static JTextArea userEntry = new JTextArea();
    static JLabel text = new JLabel();
    static JLabel enterText = new JLabel("Enter the string here: ");


    // memory game
    mini18() {
        Random rn = new Random();
        for (int i = 0; i < 7; i++) {
            char memoryChar = (char) rn.nextInt(97, 122);
            memoryString.append(memoryChar);
        }

        start.setFocusable(false);
        start.setBounds(110, 210, 100, 40);
        start.addActionListener(this);
        start.setLayout(null);
        start.setVisible(true);

        confirm.setFocusable(false);
        confirm.setBounds(110, 210, 100, 40);
        confirm.addActionListener(this);
        confirm.setLayout(null);
        confirm.setVisible(false);

        text.setBounds(75, 45, 200, 150);
        text.setLayout(null);
        text.setText("<html>This is a memory game,<br>you have 10 seconds to memorize this string of characters after you press start. </br>After the 10 seconds expires,</br> type in the string in the text box and click confirm.</br>If you don't click confirm in time,</br>your entry will be saved and assessed.</html>");

        userEntry.setBounds(110, 160, 100, 30);
        userEntry.setVisible(false);
        userEntry.setLayout(null);

        enterText.setVisible(false);
        enterText.setBounds(80, 60, 200, 150);
        enterText.setLayout(null);

        minigameWindow.add(start);
        minigameWindow.add(confirm);
        minigameWindow.add(text);
        minigameWindow.add(userEntry);
        minigameWindow.add(enterText);

        minigameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        minigameWindow.setSize(400,400);
        minigameWindow.setLayout(null);
        minigameWindow.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Random rn = new Random();
        if (e.getSource()==start) {
            interval = 10;
            text.setText("<html>Memorize the string: " + memoryString + " Time left: " + interval);
            start.setVisible(false);
            int delay = 1000;
            int period = 1000;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    text.setText("<html>Memorize the string: " + memoryString + " Time left: " + setInterval() + "</html>");
                }
            }, delay, period);
        } else {
            int posChange;
            String userEntried = userEntry.getText();
            if (Objects.equals(userEntried, String.valueOf(memoryString))) {
                posChange = rn.nextInt(1, 4);
            } else {
                posChange = rn.nextInt(-4, -1);
            }
            MainUI.events[0] = true;
            minigameWindow.dispose();
            MainUI.frame.setVisible(true);
            MainUI.updateBoard(MainUI.pastPlayer, posChange, indicatePlayers.playerInfo);
        }
    }

    private static int setInterval() {
        if (interval == 1) {
            timer.cancel();
            userEntry.setVisible(true);
            confirm.setVisible(true);
            text.setVisible(false);
            enterText.setVisible(true);
        }
        return --interval;
    }
}