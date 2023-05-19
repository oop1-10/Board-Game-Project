import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class indicatePlayers implements ActionListener {
    public Font defaultFont = new Font(null, Font.PLAIN, 12);
    JFrame frame = new JFrame("Player Indication");
    String[] playerNumbers = {"2", "3", "4"};

    indicatePlayers() {
        JComboBox<String> playerAmount = new JComboBox<>(playerNumbers);
        playerAmount.getSelectedIndex();
        playerAmount.addActionListener(this);
        playerAmount.setBounds(100,50,90,20);

        JTextArea text = new JTextArea();
        text.setText("Players: ");
        text.setLayout(null);
        text.setBounds(40,50,50,30);
        text.setFont(defaultFont);
        text.setEditable(false);

        frame.add(text);
        frame.add(playerAmount);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,250);
        frame.setLayout(null);
        frame.setVisible(true);
    }

public int playerAmount;
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox x = (JComboBox)e.getSource();
        String playerNum = (String) x.getSelectedItem();
        assert playerNum != null;
        playerAmount = Integer.parseInt(playerNum);
        frame.dispose();
        MainUI openUI = new MainUI();
    }
}
