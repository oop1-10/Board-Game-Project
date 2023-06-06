import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class mini18 implements ActionListener {
    public static int posChange;
    static JFrame minigameWindow = new JFrame();
    public static StringBuilder memoryString = new StringBuilder();
    JButton start = new JButton("Start");
    JButton confirm = new JButton("Confirm");
    JTextArea userEntry = new JTextArea();
    JLabel text = new JLabel();
    JLabel memory = new JLabel();


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

        confirm.setFocusable(false);
        confirm.setBounds(110, 210, 100, 40);
        confirm.addActionListener(this);
        confirm.setLayout(null);
        confirm.setVisible(false);

        memory.setText(String.valueOf(memoryString));
        memory.setVisible(false);
        memory.setBounds(75, 75, 100, 30);

        text.setBounds(75, 75, 150, 70);
        text.setText("<html>This is a memory game,</br>you have 10 seconds to memorize this string of characters after you press start.</br>After the 10 seconds expires,</br> type in the string in the text box and click confirm.</br>If you don't click confirm in time,</br>your entry will be saved and assessed.</html>");

        userEntry.setBounds(60, 150, 100, 30);
        userEntry.setVisible(false);

        minigameWindow.add(start);
        minigameWindow.add(confirm);
        minigameWindow.add(memory);
        minigameWindow.add(text);
        minigameWindow.add(userEntry);

        minigameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        minigameWindow.setSize(300,300);
        minigameWindow.setLayout(null);
        minigameWindow.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Random rn = new Random();
        if (e.getSource()==start) {

            try {
                text.setText("Memorize the string below: ");
                start.setVisible(false);
                memory.setVisible(true);
                userEntry.setVisible(true);
                wait(1000);
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            confirm.setVisible(true);
            memory.setVisible(false);
            text.setText("Enter the string below: ");
        } else {
            String userEntried = userEntry.getText();
            if (userEntried == String.valueOf(memoryString)) {
                posChange = rn.nextInt(1, 4);
            } else {
                posChange = rn.nextInt(-4, -1);
            }
            MainUI.event = true;
            minigameWindow.dispose();
            MainUI.frame.setVisible(true);
            MainUI.updateBoard(MainUI.pastPlayer, posChange, indicatePlayers.playerInfo);
        }
    }
}